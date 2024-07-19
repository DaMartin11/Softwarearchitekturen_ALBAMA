package com.ALBAMA.productservice.productservice.core.service.imple;

import com.ALBAMA.productservice.productservice.core.model.Product;
import com.ALBAMA.productservice.productservice.core.service.interfaces.IProductRepository;
import com.ALBAMA.productservice.productservice.port.exception.EmptySearchResultException;
import com.ALBAMA.productservice.productservice.port.exception.ProductAlreadyExistsException;
import com.ALBAMA.productservice.productservice.port.exception.ProductNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTests {

    @Mock
    private IProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product product;

    @BeforeEach
    public void setUp() {
        product = new Product(UUID.randomUUID(), "9780141396316", "The Tragedy of Macbeth", "1st",
                "William Shakespeare", new Date(), "Penguin Classics", "The Tragedy of Macbeth is a play by William Shakespeare about regicide and its aftermath.",
                "English", 144, "https://images-na.ssl-images-amazon.com/images/I/41RZGv1WcwL._SX331_BO1,204,203,200_.jpg", 12.99f, 50);
    }

    @Test
    public void testCreateProductCorrectInput() throws ProductAlreadyExistsException {
        when(productRepository.findByIsbn13(product.getIsbn13())).thenReturn(null);
        when(productRepository.save(product)).thenReturn(product);

        Product createdProduct = productService.createProduct(product);

        assertEquals(product.getId(), createdProduct.getId());
        assertEquals(product.getIsbn13(), createdProduct.getIsbn13());
        verify(productRepository).findByIsbn13(product.getIsbn13());
        verify(productRepository).save(product);
    }

    @Test
    public void testCreateProductWithExistingIsbn13() {
        when(productRepository.findByIsbn13(product.getIsbn13())).thenReturn(product);

        assertThrows(ProductAlreadyExistsException.class, () -> productService.createProduct(product));
    }

    @Test
    public void testGetProductWithExistingId() throws ProductNotFoundException {
        UUID uuid = product.getId();
        when(productRepository.findById(uuid)).thenReturn(Optional.of(product));

        Product foundProduct = productService.getProduct(uuid);

        assertEquals(product, foundProduct);
    }



    @Test
    public void testGetProductsWithProducts() {
        List<Product> products = Arrays.asList(product, product, product);
        when(productRepository.findAll()).thenReturn(products);

        List<Product> result = productService.getProducts();

        assertEquals(products, result);
    }

    @Test
    public void testUpdateProductWithExistingId() {
        when(productRepository.existsById(product.getId())).thenReturn(true);

        productService.updateProduct(product);

        verify(productRepository).deleteById(product.getId());
        verify(productRepository).save(product);
    }

    @Test
    public void testRemoveProductWithExistingId() {
        UUID uuid = product.getId();

        productService.removeProduct(uuid);

        verify(productRepository).deleteById(uuid);
    }

    @Test
    public void testAddStockWithExistingProduct() throws ProductNotFoundException {
        when(productRepository.existsById(product.getId())).thenReturn(true);
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));

        productService.addStock(product.getId(), 50);

        assertEquals(100, product.getStock());
        verify(productRepository).save(product);
    }

    @Test
    public void testAddStockWithNonExistingProduct() {
        UUID uuid = UUID.randomUUID();
        when(productRepository.existsById(uuid)).thenReturn(false);

        assertThrows(ProductNotFoundException.class, () -> productService.addStock(uuid, 50));
    }

    @Test
    public void testGetStock() throws ProductNotFoundException {
        when(productRepository.existsById(product.getId())).thenReturn(true);
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));

        int stock = productService.getStock(product.getId());

        assertEquals(50, stock);
    }

    @Test
    public void testGetStockThrowsProductNotFoundException() {
        UUID productId = UUID.randomUUID();
        when(productRepository.existsById(productId)).thenReturn(false);

        assertThrows(ProductNotFoundException.class, () -> productService.getStock(productId));
    }

    @Test
    public void testSearchProductByName() throws EmptySearchResultException {
        List<Product> products = Collections.singletonList(product);
        when(productRepository.findByTitleContainingIgnoreCase("Macbeth")).thenReturn(products);

        List<Product> result = productService.searchProduct("Macbeth");

        assertEquals(1, result.size());
        assertEquals(product, result.get(0));
    }

    @Test
    public void testSearchProductThrowsEmptySearchResultException() {
        when(productRepository.findByTitleContainingIgnoreCase("Romeo and Juliet")).thenReturn(new ArrayList<>());

        assertThrows(EmptySearchResultException.class, () -> productService.searchProduct("Romeo and Juliet"));
    }
}

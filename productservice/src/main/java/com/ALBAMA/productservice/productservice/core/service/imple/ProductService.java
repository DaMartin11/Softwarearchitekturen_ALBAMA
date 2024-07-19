package com.ALBAMA.productservice.productservice.core.service.imple;

import com.ALBAMA.productservice.productservice.core.model.Product;
import com.ALBAMA.productservice.productservice.core.service.interfaces.IProductRepository;
import com.ALBAMA.productservice.productservice.core.service.interfaces.IProductService;
import com.ALBAMA.productservice.productservice.port.exception.EmptySearchResultException;
import com.ALBAMA.productservice.productservice.port.exception.ProductAlreadyExistsException;
import com.ALBAMA.productservice.productservice.port.exception.ProductNotFoundException;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class ProductService implements IProductService {

    @Autowired
    @Setter
    private IProductRepository productRepository;


    @Override
    public Product createProduct(Product product) throws ProductAlreadyExistsException {
        if (productRepository.findByIsbn13(product.getIsbn13()) == null) {
            return productRepository.save(product);
        }
        throw new ProductAlreadyExistsException();    }

    @Override
    public Product getProduct(UUID uuid) {
        return productRepository.findById(uuid).orElse(null);
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public void updateProduct(Product product) {
        if (productRepository.existsById(product.getId())) {
            productRepository.deleteById(product.getId());
            productRepository.save(product);
        }

    }

    @Override
    public void removeProduct(UUID id) {
        productRepository.deleteById(id);

    }

    @Override
    public void addStock(UUID id, int quantity) throws ProductNotFoundException {
        if (productRepository.existsById(id)) {
            Product tempProduct = productRepository.findById(id).get();
            int tempStock = tempProduct.getStock();
            tempProduct.setStock(tempStock+quantity);
            productRepository.save(tempProduct);
            return;
        }
        throw new ProductNotFoundException();

    }

    @Override
    public int getStock(UUID id) throws ProductNotFoundException {
        if (productRepository.existsById(id)) {
            Product tempProduct = productRepository.findById(id).get();
            return tempProduct.getStock();
        }
        throw new ProductNotFoundException();
    }

    @Override
    public List<Product> searchProduct(String query) throws EmptySearchResultException {
        if (!productRepository.findByTitleContainingIgnoreCase(query).isEmpty()) {
            return productRepository.findByTitleContainingIgnoreCase(query);
        } else if (!productRepository.findByIsbn13ContainingIgnoreCase(query).isEmpty()) {
            return productRepository.findByIsbn13ContainingIgnoreCase(query);
        }
        throw new EmptySearchResultException();    }
}

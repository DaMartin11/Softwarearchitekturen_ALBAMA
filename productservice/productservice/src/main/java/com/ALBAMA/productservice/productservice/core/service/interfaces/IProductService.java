package com.ALBAMA.productservice.productservice.core.service.interfaces;

import com.ALBAMA.productservice.productservice.core.model.Product;
import com.ALBAMA.productservice.productservice.port.exception.EmptySearchResultException;
import com.ALBAMA.productservice.productservice.port.exception.ProductAlreadyExistsException;
import com.ALBAMA.productservice.productservice.port.exception.ProductNotFoundException;

import java.util.List;
import java.util.UUID;

public interface IProductService {


    public Product createProduct(Product product) throws ProductAlreadyExistsException;

    public Product getProduct(UUID id);

    public List<Product> getProducts();

    public void updateProduct(Product product);

    public void removeProduct(UUID id);

    public void addStock(UUID id, int quantity) throws ProductNotFoundException;

    public int getStock(UUID id) throws ProductNotFoundException;

    public List<Product> searchProduct(String query) throws EmptySearchResultException;



}

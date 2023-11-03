package com.demo.aws.ddb;

import com.demo.aws.ddb.entity.Product;

import java.util.List;

public interface ProductService {
    Product saveProduct(Product product);

    List<Product> listProducts();

    Product getProductById(String id);

    void deleteProduct(String id);
}

package com.demo.aws.ddb;

import com.amazonaws.services.dlm.model.ResourceNotFoundException;
import com.demo.aws.ddb.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> listProducts() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public Product getProductById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product Not Found :" + id));
    }

    @Override
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }
}

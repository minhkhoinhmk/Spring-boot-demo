package com.demo.aws.ddb;

import com.demo.aws.ddb.entity.Product;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface ProductRepository extends CrudRepository<Product, String> {
}

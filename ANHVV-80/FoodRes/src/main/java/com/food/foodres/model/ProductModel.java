package com.food.foodres.model;

import com.food.foodres.entity.Product;

import java.util.List;

public interface ProductModel {
    Product save(Product obj);

    List<Product> findAll();

    Product findById(int id);

    Product update(int id, Product updateObj);

    boolean delete(int id);
}

package com.food.foodres.model;

import com.food.foodres.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MySqlProductModelTest {
    private ProductModel productModel;
    @BeforeEach
    void setUp() {
        productModel = new MySqlProductModel();
    }

    @Test
    void save() {
        Product product = new Product();
        product.setCategoryId(1);
        product.setName("BurgerBear1");
        product.setPrice(100000);
        product.setDescription("Lorem ipsum");
        product.setDetail("Lorem ipsum");
        product.setThumbnail("Lorem ipsum");
        productModel.save(product);
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}
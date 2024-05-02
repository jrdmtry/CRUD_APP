package com.example.springboot.repositories;

import com.example.springboot.entities.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class ProductRepository {
    private List<Product> products;

    @PostConstruct
    public void init() {
        products = new ArrayList<>();
        products.add(new Product(1L, "Bread", 40));
        products.add(new Product(2L, "Milk", 90));
        products.add(new Product(3L, "Cheese", 200));
    }

    public List<Product> findAll() {
        return products;
    }

    public Product findByTitle(String title) {
        return products.stream().filter(p -> p.getTitle().equals(title)).findFirst().get();
    }

    public Product findById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst().get();
    }


    public void save(Product product) {
        if (product.getId() == null) {
            Long newId = products.stream().mapToLong(Product::getId).max().getAsLong() + 1;
            product.setId(newId);
            products.add(product);
            return;
        }
        Iterator<Product> iter = products.iterator();
        while (iter.hasNext()) {
            Product p = iter.next();
            if (p.getId().equals(product.getId())) {
                p.setPrice(product.getPrice());
                p.setTitle(product.getTitle());
                return;
            }

        }


    }

    public void deleteById(Long id) {
        Iterator<Product> iter = products.iterator();
        while (iter.hasNext()) {
            Product product = iter.next();
            if (product.getId().equals(id)) {
                iter.remove();
                return;
            }

        }

    }

}
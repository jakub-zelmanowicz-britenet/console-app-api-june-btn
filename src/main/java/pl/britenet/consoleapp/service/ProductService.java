package pl.britenet.consoleapp.service;

import pl.britenet.consoleapp.obj.model.Product;

import java.util.*;

public class ProductService {

    private final Map<Integer, Product> productMap;

    public ProductService() {
        this.productMap = new HashMap<>();
    }

    public Collection<Product> getAllProducts() {
        return this.productMap.values();
    }

    public Optional<Product> getProduct(int id) {
        return Optional.of(this.productMap.get(id));
    }

    public void insertProduct(Product product) {
        this.productMap.put(product.getId(), product);
    }

    public void updateProduct(Product product) {
        if (this.productMap.containsKey(product.getId())) {
            this.productMap.replace(product.getId(), product);
        }
        else {
            throw new IllegalStateException("No such element for update.");
        }
    }

    public void deleteProduct(Product product) {
        if (this.productMap.containsKey(product.getId())) {
            this.productMap.remove(product.getId());
        }
        else {
            throw new IllegalStateException("No such element for delete.");
        }
    }
}

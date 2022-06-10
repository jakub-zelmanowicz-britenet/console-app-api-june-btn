package pl.britenet.consoleapp.service;

import pl.britenet.consoleapp.obj.model.Product;

import java.util.*;

public class ProductService {

    private final DatabaseService databaseService;
    private final Map<Integer, Product> productMap;

    public ProductService(DatabaseService databaseService) {
        this.databaseService = databaseService;
        this.productMap = new HashMap<>();
    }

    public Collection<Product> getAllProducts() {
        return this.productMap.values();
    }

    public Optional<Product> getProduct(int id) {
        return Optional.of(this.productMap.get(id));
    }

    public void insertProduct(Product product) {
        this.databaseService.performDML(
                String.format("INSERT INTO product (name, price) VALUES ('%s', '%d')",
                        product.getName(),
                        (int) product.getPrice())
        );
    }

    public void updateProduct(Product product) {
        this.databaseService.performDML(
                String.format("UPDATE product SET name='%s', price='%f' WHERE id='%d'",
                        product.getName(),
                        product.getPrice(),
                        product.getId())
        );
    }

    public void deleteProduct(Product product) {
        this.databaseService.performDML(
                String.format("DELETE FROM product WHERE id='%d'",
                        product.getId())
        );
    }
}

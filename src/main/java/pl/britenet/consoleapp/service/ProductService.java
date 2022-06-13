package pl.britenet.consoleapp.service;

import pl.britenet.consoleapp.obj.builder.ProductBuilder;
import pl.britenet.consoleapp.obj.model.Product;

import java.sql.SQLException;
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
        return this.databaseService.performSQL(
                String.format("SELECT * FROM product WHERE id='%d'", id),
                resultSet -> {
                    try {
                        if (resultSet.next()) {

                            String name = resultSet.getString("name");
                            double price = resultSet.getDouble("price");

                            return new ProductBuilder(id)
                                    .setName(name)
                                    .setPrice(price)
                                    .getProduct();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    return null;
                }
        );
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

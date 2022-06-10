package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.builder.ProductBuilder;
import pl.britenet.consoleapp.obj.model.Product;
import pl.britenet.consoleapp.service.ProductService;

import java.util.Scanner;

public class InsertProductCommand extends Command {

    private final ProductService productService;

    public InsertProductCommand(ProductService productService) {
        super(Constants.COMMAND_INSERT_PRODUCT);
        this.productService = productService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Wprowadź nazwę produktu:");
        String name = scanner.nextLine();
        scanner.nextLine();
        System.out.println("Wprowadź opis produktu:");
        String description = scanner.nextLine();
        System.out.println("Wprowadź cenę produktu:");
        double price = scanner.nextDouble();

        Product product = new ProductBuilder()
                .setName(name)
                .setDescription(description)
                .setPrice(price)
                .getProduct();

        this.productService.insertProduct(product);
        System.out.println("Produkt został utworzony!");
    }
}

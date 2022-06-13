package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.model.Product;
import pl.britenet.consoleapp.service.ProductService;

import java.util.Scanner;

public class GetProductCommand extends Command {

    private final ProductService productService;

    public GetProductCommand(ProductService productService) {
        super(Constants.COMMAND_GET_PRODUCT);
        this.productService = productService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wprowadz ID produktu:");
        int id = scanner.nextInt();

        Product product = productService.getProduct(id).orElseThrow();
        System.out.println(product.getName());
    }
}

package pl.britenet.consoleapp;

import pl.britenet.consoleapp.obj.command.Command;
import pl.britenet.consoleapp.obj.command.GetProductCommand;
import pl.britenet.consoleapp.obj.command.HelpCommand;
import pl.britenet.consoleapp.obj.command.InsertProductCommand;
import pl.britenet.consoleapp.service.CommandService;
import pl.britenet.consoleapp.service.DatabaseService;
import pl.britenet.consoleapp.service.ProductService;
import pl.britenet.consoleapp.service.UserService;

import java.util.Optional;
import java.util.Scanner;

public class Main {

    public static boolean IS_ON = true;

    public static void main(String[] args) {
        DatabaseService databaseService = new DatabaseService();

        ProductService productService = new ProductService(databaseService);
        UserService userService = new UserService(databaseService);

        CommandService commandService = new CommandService();
        commandService.registerCommand(new HelpCommand(commandService));
        commandService.registerCommand(new InsertProductCommand(productService));
        commandService.registerCommand(new GetProductCommand(productService));

        Scanner scanner = new Scanner(System.in);
        while (IS_ON) {

            System.out.println("Podaj komende:");
            String commandName = scanner.nextLine();
            Optional<Command> optionalCommand = commandService.findCommand(commandName);

            if (optionalCommand.isPresent()) {
                optionalCommand.get().execute();
            }
            else {
                System.out.println("Nieznana komenda");
            }

        }
    }

}

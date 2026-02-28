package com.jorgelog123.inventario.app;


import com.jorgelog123.inventario.domain.InventoryItem;
import com.jorgelog123.inventario.domain.Product;
import com.jorgelog123.inventario.service.InventoryService;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        InventoryService service = new InventoryService();
        Scanner scanner = new Scanner(System.in);

        int option = -1;

        do {
            printMenu();

            // Leer opción de manera segura (si el user mete letras, no se rompe)
            try {
                option = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // consume token inválido
                continue;       // vuelve al menú
            }

            try {
                switch (option) {
                    case 1:
                        handleAddProduct(scanner, service);
                        break;
                    case 2:
                        handleStockIn(scanner, service);
                        break;
                    case 3:
                        handleStockOut(scanner, service);
                        break;
                    case 4:
                        handleListLowStock(service);
                        break;
                    case 0:
                        System.out.println("Bye.");
                        break;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (InputMismatchException e) {
                // Por si se meten letras donde hay números dentro de los handlers
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // consume token inválido
            }

        } while (option != 0);

        scanner.close();
    }

    private static void handleAddProduct(Scanner scanner, InventoryService service) {

        System.out.print("SKU: ");
        String sku = scanner.next();

        System.out.print("Name (no spaces): ");
        String name = scanner.next();

        System.out.print("Price: ");
        double price = scanner.nextDouble();

        System.out.print("Initial quantity: ");
        int initialQty = scanner.nextInt();

        System.out.print("Min stock: ");
        int minStock = scanner.nextInt();

        Product product = new Product(sku, name, price);
        service.addProduct(product, initialQty, minStock);

        System.out.println("OK. Product added.");
    }

    private static void handleStockIn(Scanner scanner, InventoryService service) {

        System.out.print("SKU: ");
        String sku = scanner.next();

        System.out.print("Amount to add: ");
        int amount = scanner.nextInt();

        service.stockIn(sku, amount);
        int newStock = service.getStock(sku);

        System.out.println("OK. New stock: " + newStock);
    }

    private static void handleStockOut(Scanner scanner, InventoryService service) {

        System.out.print("SKU: ");
        String sku = scanner.next();

        System.out.print("Amount to remove: ");
        int amount = scanner.nextInt();

        service.stockOut(sku, amount);
        int newStock = service.getStock(sku);

        System.out.println("OK. New stock: " + newStock);
    }

    private static void handleListLowStock(InventoryService service) {
        System.out.println("\n=== LOW STOCK ITEMS ===");

        List<InventoryItem> low = service.listLowStock();

        if (low.isEmpty()) {
            System.out.println("No low stock items.");
        } else {
            for (InventoryItem it : low) {
                System.out.println(it); // usa toString()
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n=== INVENTORY MENU ===");
        System.out.println("1) Add product");
        System.out.println("2) Stock in");
        System.out.println("3) Stock out");
        System.out.println("4) List low stock");
        System.out.println("0) Exit");
        System.out.print("Choose: ");
    }
}






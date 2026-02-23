package com.jorgelog123.inventario.app;


import com.jorgelog123.inventario.domain.InventoryItem;
import com.jorgelog123.inventario.domain.Product;
import com.jorgelog123.inventario.service.InventoryService;

public class Main {
    public static void main(String[] args) {


        // 1) Crear producto
        Product p = new Product("TEC_011", "Mouse", 3500.0);
        System.out.println("Producto: " + p);

        // 2) Crear item de inventario con stock inicial.

        InventoryItem item = new InventoryItem(p, 10, 5);
        System.out.println("Stock Inicial: " + item.getQuantity());
        System.out.println("¿El stock es bajo? " + item.isLowStock());

        // 3) Caso válido: bajar stock
        item.decrease(5);
        System.out.println("Se saco de Stock " + item.getQuantity() + "unidades de este producto");
        System.out.println("¿Bajo stock? " + item.isLowStock()); // esperado: true

        // 4) Caso inválido: intentar bajar más de lo disponible
        try {
            item.decrease(999);
            System.out.println("Esto no deberia imprimirse/");
        } catch (IllegalArgumentException e) {
            System.out.println("Error inesperado " + e.getMessage());
        }


        InventoryService service = new InventoryService();

        // 1) Crear y agregar producto al inventario
        Product mouse = new Product("TEC_011", "Mouse", 3500.0);
        service.addProduct(mouse, 10, 3);

        // 2) Consultar stock inicial
        System.out.println("Stock inicial TEC_011: " + service.getStock("TEC_011")); // esperado: 10

        // 3) Entrada de stock (stock in)
        service.stockIn("TEC_011", 5);
        System.out.println("Stock luego de stockIn(5): " + service.getStock("TEC_011")); // esperado: 15

        // 4) Salida de stock (stock out)
        service.stockOut("TEC_011", 8);
        System.out.println("Stock luego de stockOut(8): " + service.getStock("TEC_011")); // esperado: 7

        // 5) Caso inválido: SKU inexistente
        try {
            service.stockIn("NOPE_999", 1);
        } catch (IllegalArgumentException e) {
            System.out.println("Error esperado (SKU no existe): " + e.getMessage());
        }

        // 6) Caso inválido: salida mayor al stock disponible
        try {
            service.stockOut("TEC_011", 999);
        } catch (IllegalArgumentException e) {
            System.out.println("Error esperado (stock insuficiente): " + e.getMessage());
        }

        // 7) Caso inválido: duplicado de SKU
        try {
            service.addProduct(new Product("TEC_011", "Otro mouse", 4000.0), 1, 1);
        } catch (IllegalArgumentException e) {
            System.out.println("Error esperado (SKU duplicado): " + e.getMessage());
        }
        // 8) Caso inválido: sku en blanco (esto prueba la validación dentro de getItemOrThrow)
        try {
            service.getStock("   ");
        } catch (IllegalArgumentException e) {
            System.out.println("Error esperado (sku blank): " + e.getMessage());
        }

        // 9) Caso inválido: sku null (también prueba getItemOrThrow)
        try {
            service.stockIn(null, 1);
        } catch (IllegalArgumentException e) {
            System.out.println("Error esperado (sku null): " + e.getMessage());
        }

    }
}

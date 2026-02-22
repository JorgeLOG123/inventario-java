package com.jorgelog123.inventario.app;


import com.jorgelog123.inventario.domain.InventoryItem;
import com.jorgelog123.inventario.domain.Product;

public class Main {
    public static void main(String[] args) {


        // 1) Crear producto
        Product p = new Product("TEC_011", "Mouse", 3500.0);
        System.out.println("Producto: " + p);

        // 2) Crear item de inventario con stock inicial.

        InventoryItem item = new InventoryItem(p,10,5);
        System.out.println("Stock Inicial: " + item.getQuantity());
        System.out.println("¿El stock es bajo? " + item.isLowStock());

        // 3) Caso válido: bajar stock
        item.decrease(5);
        System.out.println("Se saco de Stock " + item.getQuantity() + "unidades de este producto");
        System.out.println("¿Bajo stock? " + item.isLowStock()); // esperado: true

        // 4) Caso inválido: intentar bajar más de lo disponible
        try{
            item.decrease(999);
            System.out.println("Esto no deberia imprimirse/");
        }catch (IllegalArgumentException e){
            System.out.println("Error inesperado " + e.getMessage());
        }

    }
}

package com.jorgelog123.inventario.service;

import com.jorgelog123.inventario.domain.InventoryItem;
import com.jorgelog123.inventario.domain.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class InventoryService {

    private final HashMap<String, InventoryItem> items = new HashMap<>();


    public InventoryService() {
    }

    public void addProduct(Product product, int initialQty, int minStock) {


        if (product == null) {
            throw new IllegalArgumentException("product cannot be null");
        }
        String sku = product.getSku();

        if (sku == null || sku.isBlank()) {
            throw new IllegalArgumentException("sku cannot be Null/Blank");
        }

        if (items.containsKey(sku)) {
            throw new IllegalArgumentException("SKU already exists: " + sku);
        }

        InventoryItem item = new InventoryItem(product, initialQty, minStock);
        items.put(sku, item);


    }

    public void stockIn(String sku, int amount) {
        getItemOrThrow(sku).increase(amount);
    }

    public void stockOut(String sku, int amount) {
        getItemOrThrow(sku).decrease(amount);

    }

    public int getStock(String sku) {
        return getItemOrThrow(sku).getQuantity();

    }


    private InventoryItem getItemOrThrow(String sku) {

        if (sku == null || sku.isBlank()) {
            throw new IllegalArgumentException("sku cannot be null/blank");
        }
        InventoryItem item = items.get(sku);
        if (item == null) {
            throw new IllegalArgumentException("SKU not found " + sku);
        }
        return item;
    }

    public List<InventoryItem> listLowStock() {
        // TODO 1: crear lista resultados
        List<InventoryItem> result = new ArrayList<>();
        // TODO 2: recorrer items.values()
        for (InventoryItem item : items.values()) {
            if (item.isLowStock()) {
                result.add(item);
            }
        }
        return result;
    }









}
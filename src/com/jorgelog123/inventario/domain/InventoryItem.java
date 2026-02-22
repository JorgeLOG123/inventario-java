package com.jorgelog123.inventario.domain;

public class InventoryItem {

    private Product product;
    private  int quantity;
    private  int minStock;

    public InventoryItem(Product product, int quantity, int minStock){

        if(product == null){
            throw new IllegalArgumentException("product cannot be null");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("quantity cannot be negative: " + quantity);
        }
        if (minStock < 0) {
            throw new IllegalArgumentException("minStock cannot be negative: " + minStock);
        }


        this.product = product;
        this.quantity = quantity;
        this.minStock = minStock;


    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getMinStock() {
        return minStock;
    }

    public void increase(int amount){
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be > 0: " + amount);
        }
        quantity += amount;
    }


    public void decrease(int amount){
        if(amount <= 0){
            throw new IllegalArgumentException("amount must be > 0: " + amount);
        }
        if(amount > quantity){
            throw new IllegalArgumentException("el monto no debe exceder la cantidad dispoible");
        }
        quantity -= amount;
    }
    public boolean isLowStock(){
        return quantity <= minStock;

    }

}

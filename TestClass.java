package com.inventory;

public class TestClass {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();

        // Load products from the database
        inventory.loadProductsFromDatabase();

        // Display initial inventory
        System.out.println("Initial Inventory:");
        inventory.displayAllProducts();

        // Perform database operations with validation
        Product newProduct = new Product(6, "Notebook", 100, 50, "Stationery");
        inventory.addProductToDatabase(newProduct);  // Add new product to DB

        inventory.updateProductInDatabase(1, "Laptop Pro", 8, 48000, "Electronics");  // Update product

        inventory.deleteProductFromDatabase(2);  // Delete product

        // Display updated inventory
        System.out.println("\nUpdated Inventory:");
        inventory.displayAllProducts();
    }
}


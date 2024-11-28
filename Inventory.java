package com.inventory;

import java.sql.*;
import java.util.ArrayList;

public class Inventory {
    private ArrayList<Product> products;
    private Connection connection;

    public Inventory() {
        this.products = new ArrayList<>();
        try {
            this.connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadProductsFromDatabase() {
        String query = "SELECT * FROM products";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                String category = rs.getString("category");
                Product product = new Product(id, name, quantity, price, category);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addProductToDatabase(Product product) {
        if (!Validator.validateProduct(product)) {
            System.out.println("Invalid product data. Unable to add to the database.");
            return;
        }

        String query = "INSERT INTO products (id, name, quantity, price, category) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, product.getId());
            stmt.setString(2, product.getName());
            stmt.setInt(3, product.getQuantity());
            stmt.setDouble(4, product.getPrice());
            stmt.setString(5, product.getCategory());
            stmt.executeUpdate();
            System.out.println("Product added successfully: " + product.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProductInDatabase(int id, String name, int quantity, double price, String category) {
        if (!Validator.isValidProductId(id) || !Validator.isValidProductName(name) ||
                !Validator.isValidQuantity(quantity) || !Validator.isValidPrice(price) ||
                !Validator.isValidCategory(category)) {
            System.out.println("Invalid data for updating the product.");
            return;
        }

        String query = "UPDATE products SET name = ?, quantity = ?, price = ?, category = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setInt(2, quantity);
            stmt.setDouble(3, price);
            stmt.setString(4, category);
            stmt.setInt(5, id);
            stmt.executeUpdate();
            System.out.println("Product updated successfully: ID " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProductFromDatabase(int id) {
        if (!Validator.isValidProductId(id)) {
            System.out.println("Invalid product ID. Unable to delete.");
            return;
        }

        String query = "DELETE FROM products WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Product deleted successfully: ID " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayAllProducts() {
        for (Product product : products) {
            product.displayProduct();
        }
    }
}


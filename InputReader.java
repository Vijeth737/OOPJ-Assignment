package com.inventory;

import java.io.*;
import java.util.*;

public class InputReader {
    public static void loadProductsFromFile(String filePath, Inventory inventory) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                int quantity = Integer.parseInt(parts[2]);
                double price = Double.parseDouble(parts[3]);
                String category = parts[4];

                Product product = new Product(id, name, quantity, price, category);
                inventory.addProductToDatabase(product);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


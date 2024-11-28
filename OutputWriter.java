package com.inventory;

import java.io.*;
import java.util.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

public class OutputWriter {
    @SuppressWarnings("unchecked")
    public static void writeInventoryToFile(Inventory inventory, String outputFilePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            writer.write("ID,Name,Quantity,Price,Category\n");

            // Access the private 'products' field using reflection
            Field productsField = Inventory.class.getDeclaredField("products");
            productsField.setAccessible(true);  // Allow access to private field
            List<Product> products = (List<Product>) productsField.get(inventory);

            for (Product product : products) {
                writer.write(product.getId() + "," + product.getName() + "," +
                        product.getQuantity() + "," + product.getPrice() + "," +
                        product.getCategory() + "\n");
            }

            System.out.println("Inventory data written to " + outputFilePath);
        } catch (IOException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}



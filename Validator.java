package com.inventory;

public class Validator {
    public static boolean isValidProductId(int id) {
        return id > 0;
    }

    public static boolean isValidProductName(String name) {
        return name != null && !name.trim().isEmpty() && name.length() <= 100;
    }

    public static boolean isValidQuantity(int quantity) {
        return quantity >= 0;
    }

    public static boolean isValidPrice(double price) {
        return price > 0;
    }

    public static boolean isValidCategory(String category) {
        return category != null && !category.trim().isEmpty() &&
                (category.equalsIgnoreCase("Electronics") ||
                        category.equalsIgnoreCase("Furniture") ||
                        category.equalsIgnoreCase("PersonalCare") ||
                        category.equalsIgnoreCase("Stationery"));
    }

    public static boolean validateProduct(Product product) {
        return isValidProductId(product.getId()) &&
                isValidProductName(product.getName()) &&
                isValidQuantity(product.getQuantity()) &&
                isValidPrice(product.getPrice()) &&
                isValidCategory(product.getCategory());
    }
}


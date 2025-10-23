package com.example.warehouse;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represents a product category. This class implements the Flyweight design pattern,
 * ensuring that only one instance exists for each unique category name
 * after normalization (e.g., "food", "Food", and "FOOD" all map to "Food").
 */
public class Category {

    private final String name;
    //Map to store and retrieve Category instances.
    private static final Map<String, Category> categories = new HashMap<>();

    /**
     * Private constructor for the Flyweight pattern. Instances can only be created
     * internally via the Code.of factory method.
     *
     * @param name The name of the category.
     */
    private Category(String name) {
        this.name = name;
    }

    /**
     * Factory method to retrieve a Category instance based on the input name.
     * The input name is validated and then normalized (first letter capitalized, rest lowercased)
     * before checking the cache.
     *
     * @param name The desired category name (case-insensitive for lookup).
     * @return A unique, cached instance of the Category.
     * @throws IllegalArgumentException if the provided name is null or blank.
     */
    public static Category of(String name) {

        if (name == null) throw new IllegalArgumentException("Category name can't be null");
        if (name.isBlank()) throw new IllegalArgumentException("Category name can't be blank");

        String normalized = name.trim().substring(0, 1).toUpperCase() + name.trim().substring(1).toLowerCase();

        return categories.computeIfAbsent(normalized, Category::new);
    }

    /**
     * Retrieves the name of the category.
     *
     * @return The normalized name of the category (e.g., "Food").
     */

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Category category)) return false;
        return Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return name;
    }
}

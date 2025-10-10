package com.example.warehouse;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Category {
    private final String name;
    private static final Map<String, Category> categories = new HashMap<>();

    private Category(String name) {
        this.name = name;
    }
// factory method

    public static Category of(String name) {
        if (name == null) throw new IllegalArgumentException("Category name can't be null");
        if (name.isBlank()) throw new IllegalArgumentException("Category name can't be blank");
        if (name.isEmpty()) throw new IllegalArgumentException("Category name can't be blank");

        //Tar banan och gör om den till Banan - normaliserar namnet för att kunna matcha namnet i (flyweight/cache) syfte
        String normalized = name.trim().substring(0, 1).toUpperCase() + name.trim().substring(1).toLowerCase();

        if (categories.containsKey(normalized)) {
            return categories.get(normalized); //Återanvänd det som finns
        } else {
            Category category = new Category(normalized);//skapa ny om ej funnen
            categories.put(normalized, category);
            return category;
        }
    }



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

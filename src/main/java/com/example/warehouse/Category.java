package com.example.warehouse;

import java.util.Objects;

public class Category {
    private final String name;

    private Category(String name) {
        this.name = name;
    }
// factory method

    public void factory (String name) {

    }

    public void isValid(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Category name can't be null ");
        } else if (name.isEmpty()) {
            throw new IllegalArgumentException("Category name can't be empty ");
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

package com.example.warehouse;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

/**
 * An abstract base class for all products managed by the warehouse.
 * It provides core properties (ID, name, category, price) and enforces
 * rules, such as non-negative pricing.
 */
public abstract class Product {

    private final UUID id;
    private final String name;
    private final Category category;
    private BigDecimal price; // Price is not final (can be changed later)

    /**
     * protected Constructor - Constructs a new Product instance. A unique UUID is generated automatically.
     * * @param name The product's name.
     *
     * @param category The product's category.
     * @param price    The initial price of the product.
     * @throws IllegalArgumentException if the provided price is negative.
     */
    protected Product(UUID id, String name, Category category, BigDecimal price) {

        if (price.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("Price cannot be negative.");
        this.id = id; // Gives a random id-number (128bit)
        this.name = name;
        this.category = category;
        this.price = price;
    }

    /**
     * Retrieves the unique identifier (UUID) for this product.
     *
     * @return The UUID of the product.
     */
    public UUID uuid() {
        return id;
    }

    /**
     * Retrieves the name of the product.
     *
     * @return The product's name.
     */
    public String name() {
        return name;
    }

    /**
     * Retrieves the category of the product.
     *
     * @return The product's Category.
     */
    public Category category() {
        return category;
    }

    /**
     * Retrieves the current price of the product.
     *
     * @return The price as a BigDecimal.
     */
    public BigDecimal price() {
        return price;
    }

    /**
     * Updates the product's price.
     *
     * @param newPrice The new price to set.
     * @throws IllegalArgumentException if the provided new price is negative.
     */
    public void price(BigDecimal newPrice) {
        this.price = newPrice;
        if (newPrice.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("Price cannot be negative.");

    }

    /**
     * Defines the abstract method for returning specific details about the product.
     * Subclasses must implement this to provide their own unique description.
     *
     * @return A String description of the product.
     */
    public abstract String productDetails();

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Product product)) return false;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, category, price);
    }
}

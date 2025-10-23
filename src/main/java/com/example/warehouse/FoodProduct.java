package com.example.warehouse;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Represents a food product in the warehouse.
 * A FoodProduct implements both  Perishable (has an expiration date) and
 * Shippable (has a weight and a defined shipping cost calculation).
 */
public class FoodProduct extends Product implements Perishable, Shippable {

    private final LocalDate expirationDate;
    private final BigDecimal weight;

    /**
     * Constructs a new FoodProduct.
     *
     * @param id             The unique identifier for the product
     * @param name           The name of the food product.
     * @param category       The product's category.
     * @param price          The initial price of the product.
     * @param expirationDate The date on which the product expires.
     * @param weight         The weight of the product.
     * @throws IllegalArgumentException if the provided weight is negative.
     */
    public FoodProduct(UUID id, String name, Category category, BigDecimal price, LocalDate expirationDate, BigDecimal weight) {
        if (weight.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("Weight cannot be negative.");
        // Calls the abstract Product constructor.
        super(id, name, category, price);

        this.expirationDate = expirationDate;
        this.weight = weight;
    }

    /**
     * Provides a summary of the food product, forced from the superclass.
     * * @return A formatted String containing the product name and its expiration date.
     */
    @Override
    public String productDetails() {
        return String.format("Food: %s, Expires: %s", name(), expirationDate);
    }

    // ----- Implementation of the interface Shippable ----

    /**
     * Calculates the shipping cost based on the product's weight.
     * Uses weight * 50, rounded to two decimals.
     * * @return The calculated shipping cost.
     */
    @Override
    public BigDecimal calculateShippingCost() {

        return weight.multiply(new BigDecimal(50)).setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Retrieves the weight of the product as a double.
     * * @return The product's weight.
     */
    @Override
    public Double weight() {

        return this.weight.doubleValue();
    }
    // ----Implementation of the Interface Perishable ----

    /**
     * Retrieves the specific expiration date for this food product.
     * * @return The product's expiration date.
     */
    @Override
    public LocalDate expirationDate() {

        return this.expirationDate;
    }
}

package com.example.warehouse;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

/**
 * Represents an electronic item in the warehouse.
 * An ElectronicsProduct implements Shippable (it has a weight and calculated shipping cost)
 * and includes a specific warranty period.
 */
public class ElectronicsProduct extends Product implements Shippable {

    private final int warrantyMonths;
    private final BigDecimal weight;

    /**
     * Constructs a new ElectronicsProduct.
     * * @param id The unique identifier for the product.
     *
     * @param name           The name of the electronic product.
     * @param category       The product's category.
     * @param price          The initial price of the product.
     * @param warrantyMonths The length of the product warranty in months.
     * @param weight         The weight of the product.
     * @throws IllegalArgumentException if the warranty months or weight is negative.
     */
    public ElectronicsProduct(UUID id, String name, Category category, BigDecimal price, int warrantyMonths, BigDecimal weight) {
        super(id, name, category, price);
        if (warrantyMonths < 0) throw new IllegalArgumentException("Warranty months cannot be negative.");
        if (weight.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("Weight cannot be negative.");
        this.warrantyMonths = warrantyMonths;
        this.weight = weight;
    }

    /**
     * Provides a summary of the electronics product.
     * * @return A String containing the product name and its warranty period.
     */
    @Override
    public String productDetails() {
        return String.format("Electronics: %s, Warranty: %s months", name(), warrantyMonths);
    }

    // --- Implementation of Shippable Interface ---

    /**
     * Calculates the shipping cost for electronics, applying a surcharge for heavy items.
     * Base cost is 79. An extra of 49 is added if the weight is over 5 units(kg).
     * * @return The calculated shipping cost, rounded up to two decimals.
     */
    @Override
    public BigDecimal calculateShippingCost() {

        BigDecimal baseShippingCost = BigDecimal.valueOf(79);
        BigDecimal extraShippingCost = BigDecimal.valueOf(49);

        if (this.weight.compareTo(BigDecimal.valueOf(5)) > 0) {
            baseShippingCost = baseShippingCost.add(extraShippingCost);
        }
        return baseShippingCost.setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Retrieves the weight of the product as a double.
     *
     * @return The product's weight.
     */
    @Override
    public Double weight() {
        // Converts the BigDecimal weight to a double for the interface contract.
        return this.weight.doubleValue();
    }
}

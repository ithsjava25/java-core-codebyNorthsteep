package com.example.warehouse;

import java.math.BigDecimal;
import java.util.UUID;

public class ElectronicsProduct extends Product implements Shippable{
//    Implements Shippable.
//    Fields: int warrantyMonths, BigDecimal weight (kg).
//    Validation: negative warranty -> IllegalArgumentException("Warranty months cannot be negative.").
//    productDetails() should look like: "Electronics: Laptop, Warranty: 24 months".
//    Shipping rule: base 79, add 49 if weight > 5.0 kg.
    private final int warrantyMonths;
    private final BigDecimal weight;
    public ElectronicsProduct(UUID id, String name, Category category, BigDecimal price, int warrantyMonths, BigDecimal weight) {
        super(name, category, price);
        this.warrantyMonths = warrantyMonths;
        this.weight = weight;
    }

    @Override
    public String productDetails() {
        return "";
    }

    @Override
    public BigDecimal calculateShippingCost() {
        return null;
    }

    @Override
    public Double weight() {
        return 0.0;
    }
}

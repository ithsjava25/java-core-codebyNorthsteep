package com.example.warehouse;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

import static java.lang.String.format;

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
        //validering om pris negativt warrantyMonths
        this.warrantyMonths = warrantyMonths;
        this.weight = weight;
    }

    @Override
    public String productDetails() {
        return String.format("Electronics: %s, Warranty: %s months", name(), warrantyMonths);
    }

    @Override
    public BigDecimal calculateShippingCost() {
        //Baspris 79, om vikten är över 5kg baspris + 49
        //if (weight > 5.0)
        //konvertera tillbaka weight som en BigDecimal
        //HALF_UP
        //this.weight.add(BigDecimal.valueOf(weight()));
        BigDecimal baseShippingCost = BigDecimal.valueOf(79);
        BigDecimal extraShippingCost = BigDecimal.valueOf(49);

        if (this.weight.compareTo(new BigDecimal("5.0")) > 0) {
            baseShippingCost = baseShippingCost.add(extraShippingCost);
        }
      return baseShippingCost.setScale(2, RoundingMode.HALF_UP);


    }

    @Override
    public Double weight() {
        return this.weight.doubleValue();
        //i calculateShipping - konvertera tillbaka till BigDecimal
    }
}

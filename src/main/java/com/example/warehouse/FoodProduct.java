package com.example.warehouse;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

//todo Rad 293 i basictest
public class FoodProduct extends Product implements Perishable, Shippable{

    private final LocalDate expirationDate;
    private final BigDecimal weight;

    public FoodProduct(String name, BigDecimal price,Category category, LocalDate expirationDate, BigDecimal weight) {
        super(name, category, price);
        this.expirationDate = expirationDate;
        this.weight = weight;
    }
    @Override
    protected String productDetails() {
        return String.format("Food: s% Expires: s%", name(), expirationDate.toString());
    }

   @Override
    public BigDecimal calculateShippingCost() {
       return weight.multiply(new BigDecimal("50")).setScale(2, RoundingMode.HALF_UP);
    }
    @Override
    public Double weight() {
        //Validations: negative price -> IllegalArgumentException("Price cannot be negative."); negative weight -> IllegalArgumentException("Weight cannot be negative.").
        //Shipping rule: cost = weight * 50.
        return weight.doubleValue();
    }
    @Override
    public LocalDate expirationDate() {
        return  expirationDate;
    }
    @Override
    public boolean isExpired() {
        return false;
    }

}

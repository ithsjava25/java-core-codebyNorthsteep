package com.example.warehouse;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

//todo Rad 293 i basictest
public class FoodProduct extends Product implements Perishable, Shippable{

    private final LocalDate expirationDate;
    private final BigDecimal weight;

    public FoodProduct(UUID id, String name,Category category,BigDecimal price, LocalDate expirationDate, BigDecimal weight) {
        super(name, category, price);
        //Validering för pris och/eller vikt med negativt värde
        this.expirationDate = expirationDate;
        this.weight = weight;
    }
    @Override
    public String productDetails() {
        return String.format("Food: %s, Expires: %s", name(), expirationDate);
    }

   @Override
    public BigDecimal calculateShippingCost() {
       //Validations: negative price -> IllegalArgumentException("Price cannot be negative."); negative weight -> IllegalArgumentException("Weight cannot be negative.").
       //Shipping rule: cost = weight * 50.
       BigDecimal cost = weight.multiply(new  BigDecimal(50)).setScale(2, RoundingMode.HALF_UP);

       return cost;
    }
    @Override
    public Double weight() {

        return this.weight.doubleValue();
    }
    @Override
    public LocalDate expirationDate() {
        //returnera this för att referera till denna specifika instans i FoodProdukt
        return  this.expirationDate;
    }
}

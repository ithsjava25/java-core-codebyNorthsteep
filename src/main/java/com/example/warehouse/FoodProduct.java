package com.example.warehouse;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FoodProduct extends Product implements Perishable, Shippable{

    public LocalDate expirationDate;
    public BigDecimal weight;


    //Validations: negative price -> IllegalArgumentException("Price cannot be negative."); negative weight -> IllegalArgumentException("Weight cannot be negative.").
    //Shipping rule: cost = weight * 50.



    @Override
    String productDetails() {
        return "Food: " + super.getName() + "Expires:" + expirationDate;
    }

    @Override
    public BigDecimal setPrice(BigDecimal newPrice) {
        return super.setPrice(newPrice);
    }

    @Override
    public LocalDate expirationDate() {
        return null;
    }
}

package com.example.warehouse;

import java.math.BigDecimal;
import java.util.UUID;

public abstract class Product {
    //"Final" så att de ej kan ändras, endast BigDecimal ska kunna ändras för prisändring.
    private final UUID id;
    private final String name;
    private final Category category;
    private BigDecimal price;

    protected Product(String name, Category category, BigDecimal price) {
        //jämför mot En BigDecimal med värdet 0
        if(price.compareTo(BigDecimal.ZERO) < 0)  throw new IllegalArgumentException("Price cannot be negative.");
        this.id = UUID.randomUUID(); // Ger ett random id-nummer (128bit)
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public UUID uuid() {
    return id;
}
public String name() {
    return name;
}
public Category category() {
    return category;
}
public BigDecimal price() {
    return price;
}
public void price(BigDecimal newPrice) {
    if(price.compareTo(BigDecimal.ZERO) < 0)  throw new IllegalArgumentException("Price cannot be negative.");
    this.price = newPrice;
}

public abstract String productDetails();
}

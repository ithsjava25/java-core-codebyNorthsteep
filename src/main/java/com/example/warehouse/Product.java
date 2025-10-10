package com.example.warehouse;

import java.math.BigDecimal;
import java.util.UUID;

public abstract class Product {
    private UUID id;
    private String name;
    private Category category;
    private BigDecimal price;

public UUID getId() {
    return id;
}
public String getName() {
    return name;
}
public Category getCategory() {
    return category;
}
public BigDecimal setPrice(BigDecimal newPrice) {
    return price;
}

abstract String productDetails();
}

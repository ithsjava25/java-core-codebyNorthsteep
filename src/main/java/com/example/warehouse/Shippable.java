package com.example.warehouse;

import java.math.BigDecimal;

public interface Shippable {
    //Alla klasser som implementeras av interfacet måste använda dess metoder


    BigDecimal calculateShippingCost();

    Double weight();
}

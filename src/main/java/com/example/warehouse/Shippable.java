package com.example.warehouse;

import java.math.BigDecimal;

public interface Shippable {
    //Alla klasser som implementeras av interfacet måste använda dess metoder

    //Metod som beräknar fraktkostnad
    BigDecimal calculateShippingCost();
    //Metod som returnerar vikt i kg
    Double weight();
}

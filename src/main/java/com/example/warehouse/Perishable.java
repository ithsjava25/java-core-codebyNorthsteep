package com.example.warehouse;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface Perishable {
//Ska representera produkter med utgånget datum
    //Alla klasser som implementeras av interfacet måste använda dess metoder

    //expose expirationDate() and a default isExpired() based on LocalDate.now().
    //En metod för att få utgångsdatum på produkt
    LocalDate expirationDate();
    default boolean isExpired() {
        //returnerar true om produkten gått ut
        return expirationDate().isBefore(LocalDate.now());
    }


}

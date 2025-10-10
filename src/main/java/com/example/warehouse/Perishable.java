package com.example.warehouse;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface Perishable {
//Ska representera produkter med utgånget datum

    //expose expirationDate() and a default isExpired() based on LocalDate.now().
    LocalDate expirationDate();
    LocalDate isExpired =  LocalDate.now();


}

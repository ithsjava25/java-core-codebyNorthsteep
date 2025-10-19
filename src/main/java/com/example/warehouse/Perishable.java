package com.example.warehouse;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Defines a contract for products that have an expiration date, such as food or certain chemicals.
 * Items implementing this interface can be checked for expiration.
 */
public interface Perishable {

    LocalDate expirationDate();

    /**
     * Checks if the item has expired based on the current date.
     *
     * @return true if the expiration date has passed and false if not.
     */
    default boolean isExpired() {

        LocalDate idag = LocalDate.now();
        // Checks if the recorded expiration date is before today's date.
        return expirationDate().isBefore(idag);
    }


}

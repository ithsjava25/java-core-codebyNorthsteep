package com.example.warehouse;

import java.math.BigDecimal;
/**
 * Defines a contract for any product or item that can be shipped.
 * Items implementing this interface must provide methods to calculate shipping cost
 * and retrieve the item's weight.
 */
public interface Shippable {
    /**
     * Calculates the total shipping cost for the item.
     * May vary due to 'extra weight' implementations.
     * @return  The calculated shipping cost as a BigDecimal.
     */
    BigDecimal calculateShippingCost();

    /**
     * Retrieves the weight of the item to be shipped.
     * @return The weight of the item in a standard unit (e.g., kilograms or pounds) as a Double.
     */
    Double weight();
}

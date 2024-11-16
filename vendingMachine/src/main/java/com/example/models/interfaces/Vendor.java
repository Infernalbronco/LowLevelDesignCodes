package com.example.models.interfaces;

import java.util.Optional;

/**
 * This class simulates each slots in the vending machine
 */
public interface Vendor {

    /**
     * Returns first item in the slot
     * @return {@Consumable}
     */
    public Optional<Consumable> vend();

    /**
     * Adds consumable at the end of the slot
     * @param consumable
     */
    public void add(Consumable consumable);

    /**
     * Returns the price of item at the beginning of the slot
     * @return price of item
     * @throws Exception
     */
    public Integer getFirstItemPrice() throws Exception;
    
}

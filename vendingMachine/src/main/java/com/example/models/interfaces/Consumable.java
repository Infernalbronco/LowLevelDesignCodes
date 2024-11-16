package com.example.models.interfaces;

/**
 * An interface for items stored in vending machine
 */
public interface Consumable {

    /**
     * Simulates consuming of the item
     */
    public void consume();

    /*
     * Gets the price of the item
     */
    public int getPrice();

    /**
     * Prints the price of the item
     */
    public void printPrice();

    /**
     * Get the name of the item
     * @return
     */
    public String getName();
    
}

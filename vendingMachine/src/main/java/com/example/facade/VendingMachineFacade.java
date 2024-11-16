package com.example.facade;

import com.example.models.interfaces.Consumable;

/**
 * facade of the vending machine visible to user 
 */
public interface VendingMachineFacade {

    /**
     * Returns first consumable from the slotNumber
     * @param slotNumber : Id of slot from which first item is needed
     * @return {@Consumable}
     */
    public Consumable vend(int slotNumber);

    /**
     * Simluates the adding money by user
     * @param amount : Amount of money added
     * @param paymentType : payment method
     */
    public void addMoney(int amount, String paymentType);
    
}

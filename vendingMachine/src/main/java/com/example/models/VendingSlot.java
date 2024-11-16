package com.example.models;

import java.util.Deque;
import java.util.Optional;

import com.example.models.interfaces.Consumable;
import com.example.models.interfaces.Vendor;

import lombok.AllArgsConstructor;

/**
 * Implementation of Vendor
 */
@AllArgsConstructor
public class VendingSlot implements Vendor{

    private final Deque<Consumable> products;

    @Override
    public Optional<Consumable> vend() {
        return Optional.ofNullable(products.pollFirst());
    }

    @Override
    public void add(Consumable consumable) {
        products.offerLast(consumable);
    }

    @Override
    public Integer getFirstItemPrice() throws Exception {

        Optional<Consumable> consumableOptional =  Optional.ofNullable(products.peekFirst());

        if (!consumableOptional.isPresent()) {
            throw new Exception("Cannot vend item. Try again!!");
        }

        return consumableOptional.get().getPrice();
    }

    
    
}

package com.example.factory;

import com.example.models.Drink;
import com.example.models.Edible;
import com.example.models.interfaces.Consumable;

/**
 * Factory for initializing different types of consumbales depending on type
 */
public class ConsumableFactory {

    public enum ConsumableType { DRINK, EDIBLE }

    public Consumable create(String name, int price, ConsumableType type) {
        if (type.equals(ConsumableType.DRINK)) {
            return new Drink(name, price);
        }

        return new Edible(name, price);
    }
    
}

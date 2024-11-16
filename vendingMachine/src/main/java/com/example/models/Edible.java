package com.example.models;

import com.example.models.interfaces.Consumable;

import lombok.AllArgsConstructor;

/**
 * Simulates edible items
 */
@AllArgsConstructor
public final class Edible implements Consumable {

    private String name;
    private int price;

    @Override
    public void consume() {
        System.out.println("Eating " + name);
    }

    @Override
    public int getPrice() {
        return this.price;

    }

    @Override
    public void printPrice() {
        System.out.println("Drink " + this.name + " is of price: " + this.price);
    }

    @Override
    public String getName() {
        return this.name;
    }
    
}

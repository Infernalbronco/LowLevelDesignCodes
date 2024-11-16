package com.example.businesslogic.payment;

import com.example.commands.Command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Command that does the payment processing
 */
@Getter
@Setter
@AllArgsConstructor
public abstract class Payment implements Command {
    
    private int toPay;
    private int amount;

    /**
     * Does the payment processing
     */
    @Override
    public void execute() {
        int change = amount - toPay;
        System.out.println("Here is you change: " + change);
    }

    public abstract void acknowlegdePayment();

}

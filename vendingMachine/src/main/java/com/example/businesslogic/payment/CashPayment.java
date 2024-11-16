package com.example.businesslogic.payment;

/**
 * Command that does the payment processing for cash payments
 */
public class CashPayment extends Payment{

    public CashPayment(int toPay, int amount) {
        super(toPay, amount);
    }

    @Override
    public void acknowlegdePayment() {
        System.out.println("Customer has added cash of amount: " + this.getAmount() + " with item of cost: " + this.getToPay());
    }
    
}

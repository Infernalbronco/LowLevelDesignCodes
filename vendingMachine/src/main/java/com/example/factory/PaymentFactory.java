package com.example.factory;

import com.example.businesslogic.payment.CashPayment;
import com.example.businesslogic.payment.Payment;

/**
 * Factory for initializing different types of payments depending on payment method
 */
public final class PaymentFactory {

    public enum PaymentType { CASH }

    /**
     * Creates a payment command on basis of payment type
     * @param toPay : Price of the item that user should be charged.
     * @param amount : Money added to vending machine by customer.
     * @param type : payment method.
     * @return Paument command
     * @throws Exception
     */
    public Payment create(int toPay, int amount, PaymentType type) throws Exception {
        Payment payment = null; 

        switch(type) {
            case CASH:
                payment = new CashPayment(toPay, amount);
                break;
            default:
                throw new Exception("Unsupported payment type");

        }

        payment.acknowlegdePayment();

        return payment;
    }
    
}

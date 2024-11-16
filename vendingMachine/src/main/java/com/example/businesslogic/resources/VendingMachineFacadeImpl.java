package com.example.businesslogic.resources;

import com.example.businesslogic.payment.Payment;
import com.example.facade.VendingMachineFacade;
import com.example.models.VendingMachine;
import com.example.models.interfaces.Consumable;
import com.example.factory.PaymentFactory;
import com.example.factory.PaymentFactory.PaymentType;

/*
 *  Implenetation of facade of the vending machine visible to user 
 */
public final class VendingMachineFacadeImpl implements VendingMachineFacade{

    private final VendingMachine vendingMachine;
    private final PaymentFactory paymentFactory;
    private int amount;
    private PaymentType type;

    public VendingMachineFacadeImpl(VendingMachine vendingMachine, PaymentFactory paymentFactory) {
        this.vendingMachine = vendingMachine;
        this.paymentFactory = paymentFactory;
    }

    /*
     * Returns the first item in a given slot number.
     */
    @Override
    public Consumable vend(int slotNumber) {

        try {
            if (!vendingMachine.checkIfPaymentPossible(this.amount, slotNumber)) {
                resetMachine();
                System.out.println("Insufficient money added!!");
                return null;
            }

            Consumable item = vendingMachine.getItem(slotNumber);

            makePayment(item.getPrice());
            
            System.out.println("Here is your item: " + item.getName());

            resetMachine();

            return item;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            resetMachine();
            return null;
        }
    }

    /**
     * Simluates the adding money by user
     */
    @Override
    public void addMoney(int amount, String type) {
        this.amount += amount;
        this.type = PaymentType.valueOf(type);
        
    }

    /*
     * Initiates payment process.
     */
    private void makePayment(int toPay) throws Exception {
        Payment payment = null;

        try {
            payment = paymentFactory.create(toPay, amount, type);
        } catch (Exception ex) {
            throw ex;
        }

        vendingMachine.makeTransaction(payment);
    }

    private void resetMachine() {
        this.amount = 0;
        this.type = null;
    }
    
}

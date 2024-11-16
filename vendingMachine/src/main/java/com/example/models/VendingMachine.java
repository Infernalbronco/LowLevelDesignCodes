package com.example.models;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import com.example.businesslogic.payment.Payment;
import com.example.businesslogic.payment.PaymentInvoker;
import com.example.models.interfaces.Consumable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/*
 * Vending machine class.
 * List<VendingSlots> : List of slots in vending machine containing items
 * PaymentInvoker : Invokes commands for payment.
 */
@Getter
@Setter
@AllArgsConstructor
public class VendingMachine {

    List<VendingSlot> slots;
    PaymentInvoker paymentInvoker;

    public Consumable getItem(int slotNumber) throws Exception {
        Optional<Consumable> consumableOptional =  slots.get(slotNumber).vend();

        if (!consumableOptional.isPresent()) {
            throw new Exception("Cannot vend item. Try again!!");
        }

        return consumableOptional.get();

    }

    public void init(int numberOfSlots) {
        for (int i = 0 ; i < numberOfSlots ; i++) {
            this.slots.add(new VendingSlot(new LinkedList<>()));
        }
    }

    /**
     * Executes pay command to calculate the change amount.
     * @param payment : Payment command that executes the payment processing.
     */
    public void makeTransaction(Payment payment) {
        this.paymentInvoker.setPaymentMethod(payment);
        this.paymentInvoker.pay();
    }

    /**
     * Adding items to inventory
     * @param consumable : Consumable iten
     * @param slotNumber : tells the solt in which to add the item
     */
    public void addItem(Consumable consumable, int slotNumber) {
        slots.get(slotNumber).add(consumable);
    }

    /**
     * Checks if user can get the item in given slot number and with amount of money added.
     * @param amount : Money added to vending machine by user
     * @param slotNumber : Slot number whose first item is needed.
     * @return boolean
     * @throws Exception
     */
    public boolean checkIfPaymentPossible(int amount, int slotNumber) throws Exception {
        int toPay = slots.get(slotNumber).getFirstItemPrice();

        return ( amount - toPay < 0 ) ? false : true;

    }
    
}

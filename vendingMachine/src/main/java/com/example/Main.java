package com.example;

import java.util.LinkedList;

import org.junit.Assert;

import com.example.businesslogic.payment.PaymentInvoker;
import com.example.businesslogic.resources.VendingMachineFacadeImpl;
import com.example.facade.VendingMachineFacade;
import com.example.factory.PaymentFactory;
import com.example.models.Drink;
import com.example.models.Edible;
import com.example.models.VendingMachine;
import com.example.models.interfaces.Consumable;

public class Main {
    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine(
            new LinkedList<>(), 
            new PaymentInvoker());

        vendingMachine.init(3);
        vendingMachine.addItem(new Edible("kurkure", 20), 0);
        vendingMachine.addItem(new Edible("kurkure", 20), 0);
        vendingMachine.addItem(new Edible("kurkure", 20), 0);

        vendingMachine.addItem(new Edible("lays", 20), 1);
        vendingMachine.addItem(new Edible("lays", 20), 1);
        vendingMachine.addItem(new Edible("lays", 20), 1);

        vendingMachine.addItem(new Drink("Pepsi", 45), 2);
        vendingMachine.addItem(new Drink("Pepsi", 45), 2);
        vendingMachine.addItem(new Drink("Pepsi", 45), 2);

        /*
         * Facade of the vending machine that is visible to user.
         */
        VendingMachineFacade vendingMachineFacade = new VendingMachineFacadeImpl(vendingMachine, new PaymentFactory());

        vendingMachineFacade.vend(1);
        vendingMachineFacade.addMoney(19, "CASH");
        vendingMachineFacade.vend(0);

        vendingMachineFacade.addMoney(20, "CASH");
        Consumable consumable = vendingMachineFacade.vend(0);

        Assert.assertNotNull(consumable);

        vendingMachineFacade.addMoney(20, "CASH");
        consumable = vendingMachineFacade.vend(0);

        Assert.assertNotNull(consumable);
        vendingMachineFacade.addMoney(18, "CASH");
        consumable = vendingMachineFacade.vend(0);

        Assert.assertNull(consumable);

        vendingMachineFacade.addMoney(20, "CASH");
        consumable = vendingMachineFacade.vend(0);

        Assert.assertNotNull(consumable);

        vendingMachineFacade.addMoney(20, "CASH");
        consumable = vendingMachineFacade.vend(0);

        Assert.assertNull(consumable);

        vendingMachineFacade.addMoney(10, "CASH");
        vendingMachineFacade.addMoney(15, "CASH");
        consumable = vendingMachineFacade.vend(1);

        Assert.assertNotNull(consumable);

    }
}
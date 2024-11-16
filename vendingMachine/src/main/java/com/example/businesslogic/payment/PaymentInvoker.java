package com.example.businesslogic.payment;

import com.example.commands.Command;

import lombok.NoArgsConstructor;

/**
 * Class that executes the payment command provided
 */
@NoArgsConstructor
public class PaymentInvoker {

    private Command payment;

    public void setPaymentMethod(Command paymentCommand) {
        this.payment = paymentCommand;
    }

    public void pay() {
        this.payment.execute();
    }
    
}

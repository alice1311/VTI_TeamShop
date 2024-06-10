package com.vti.finalexam.form;

public class PaymentMethodCreating {
    private String name;
    private String description_payment;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription_payment() {
        return description_payment;
    }

    public void setDescription_payment(String description_payment) {
        this.description_payment = description_payment;
    }

    public PaymentMethodCreating() {
    }

    public PaymentMethodCreating(String name, String description_payment) {
        this.name = name;
        this.description_payment = description_payment;
    }
}

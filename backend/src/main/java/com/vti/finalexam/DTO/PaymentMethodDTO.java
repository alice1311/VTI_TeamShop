package com.vti.finalexam.DTO;

import com.vti.finalexam.entity.Order;

import javax.persistence.*;
import java.util.List;

public class PaymentMethodDTO {
    private int id;
    private String name;
    private String description_payment;

    public String getName() {
        return name;
    }

    public PaymentMethodDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public PaymentMethodDTO(int id,String name, String description_payment) {
        this.id =id;
        this.name = name;
        this.description_payment = description_payment;
    }
}

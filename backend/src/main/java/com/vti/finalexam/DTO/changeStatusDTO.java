package com.vti.finalexam.DTO;

import com.vti.finalexam.entity.Order;

public class changeStatusDTO {
    private int customer_id;
    private Order.OderStatus oderStatus;

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public Order.OderStatus getOderStatus() {
        return oderStatus;
    }

    public void setOderStatus(Order.OderStatus oderStatus) {
        this.oderStatus = oderStatus;
    }

    public changeStatusDTO() {
    }

    public changeStatusDTO(int customer_id, Order.OderStatus oderStatus) {
        this.customer_id = customer_id;
        this.oderStatus = oderStatus;
    }
}

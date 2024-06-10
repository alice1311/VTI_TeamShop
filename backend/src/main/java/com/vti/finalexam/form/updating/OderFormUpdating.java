package com.vti.finalexam.form.updating;

import com.vti.finalexam.entity.Order;

import java.util.Date;

public class OderFormUpdating {


    private Date oder_date;

    private Order.OderStatus oderStatus;

    private int employee_id;

    private int payment_method_id;

    public OderFormUpdating( Date oder_date, Order.OderStatus oderStatus, int employee_id, int payment_method_id) {

        this.oder_date = oder_date;
        this.oderStatus = oderStatus;
        this.employee_id = employee_id;
        this.payment_method_id = payment_method_id;
    }



    public Date getOder_date() {
        return oder_date;
    }

    public void setOder_date(Date oder_date) {
        this.oder_date = oder_date;
    }

    public Order.OderStatus getOderStatus() {
        return oderStatus;
    }

    public void setOderStatus(Order.OderStatus oderStatus) {
        this.oderStatus = oderStatus;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public int getPayment_method_id() {
        return payment_method_id;
    }

    public void setPayment_method_id(int payment_method_id) {
        this.payment_method_id = payment_method_id;
    }
}

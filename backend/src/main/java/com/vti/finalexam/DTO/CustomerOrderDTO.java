package com.vti.finalexam.DTO;

import com.vti.finalexam.entity.Order;

import java.util.Date;

public class CustomerOrderDTO {
    private int idOrder;
    private Order.OderStatus oderStatus;
    private String checkFeedback;
    private Date orderDate;
    private String paymentName;
    private int totalQuantity;
    private float totalAmount;
    private String img_url;
    private String color;
    private int subQuantity;
    private String productName;

    public int getIdOrder() {
        return idOrder;
    }

    public String getCheckFeedback() {
        return checkFeedback;
    }

    public void setCheckFeedback(String checkFeedback) {
        this.checkFeedback = checkFeedback;
    }

    public CustomerOrderDTO(int idOrder, Order.OderStatus oderStatus, String checkFeedback, Date orderDate, String paymentName, int totalQuantity, float totalAmount, String img_url, String color, int subQuantity, String productName) {
        this.idOrder = idOrder;
        this.oderStatus = oderStatus;
        this.checkFeedback = checkFeedback;
        this.orderDate = orderDate;
        this.paymentName = paymentName;
        this.totalQuantity = totalQuantity;
        this.totalAmount = totalAmount;
        this.img_url = img_url;
        this.color = color;
        this.subQuantity = subQuantity;
        this.productName = productName;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public Order.OderStatus getOderStatus() {
        return oderStatus;
    }

    public void setOderStatus(Order.OderStatus oderStatus) {
        this.oderStatus = oderStatus;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSubQuantity() {
        return subQuantity;
    }

    public void setSubQuantity(int subQuantity) {
        this.subQuantity = subQuantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public CustomerOrderDTO() {
    }
}

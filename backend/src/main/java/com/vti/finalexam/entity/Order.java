package com.vti.finalexam.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "`Order`")
public class Order implements Serializable {

    @Column(name = "orderId")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "totalAmount", nullable = false)
    private float total_amount;

    @Column(name = "shipping_address")
    private String address;

    @Column(name = "recipient_phone")
    private String phone;

    @Column(name = "orderDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date oder_date;

    @Column(name = "oderStatus")
    @Enumerated(EnumType.STRING)
    private OderStatus oderStatus;

    public Order(Date oderDate, int customerId, PaymentMethod paymentMethod) {
    }

    public Order(float total_amount, String address, String phone, Date oder_date, OderStatus oderStatus, Customer customer, PaymentMethod payment_method) {
        this.total_amount = total_amount;
        this.address = address;
        this.phone = phone;
        this.oder_date = oder_date;
        this.oderStatus = oderStatus;
        this.customer = customer;
        this.payment_method = payment_method;
    }

    public Order(String address, String phone, Date oder_date, OderStatus oderStatus, Customer customer, PaymentMethod payment_method) {
        this.address = address;
        this.phone = phone;
        this.oder_date = oder_date;
        this.oderStatus = oderStatus;
        this.customer = customer;
        this.payment_method = payment_method;
    }

    public Order(String address, String phone, Date oder_date, OderStatus oderStatus, Customer customer) {
        this.address = address;
        this.phone = phone;
        this.oder_date = oder_date;
        this.oderStatus = oderStatus;
        this.customer = customer;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public enum OderStatus {
        ADDED_TO_CARD, TO_PAY, TO_RECEIVE, COMPLETED, CANCELED, FEEDBACK_COMPLETED;
    }

    public Order(float total_amount, Date oder_date, OderStatus oderStatus, Customer customer) {
        this.total_amount = total_amount;
        this.oder_date = oder_date;
        this.oderStatus = oderStatus;
        this.customer = customer;
    }

    public Order(Date oder_date, OderStatus oderStatus, Customer customer) {
        this.oder_date = oder_date;
        this.oderStatus = oderStatus;
        this.customer = customer;
    }

    public Order(Date oder_date, OderStatus oderStatus, Customer customer, PaymentMethod payment_method) {
        this.oder_date = oder_date;
        this.oderStatus = oderStatus;
        this.customer = customer;
        this.payment_method = payment_method;
    }

    @ManyToOne
    @JoinColumn(name="customerId", nullable = true)
    private Customer  customer;

    @ManyToOne
    @JoinColumn(name="employeeId", nullable = true)
    private Employee  employee;

    @ManyToOne
    @JoinColumn(name="paymentMethodId", nullable = true)
    private PaymentMethod payment_method;
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(float total_amount) {
        this.total_amount = total_amount;
    }

    public Date getOder_date() {
        return oder_date;
    }

    public void setOder_date(Date oder_date) {
        this.oder_date = oder_date;
    }

    public OderStatus getOderStatus() {
        return oderStatus;
    }

    public void setOderStatus(OderStatus oderStatus) {
        this.oderStatus = oderStatus;
    }

    public Account getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Account getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public PaymentMethod getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(PaymentMethod payment_method) {
        this.payment_method = payment_method;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}

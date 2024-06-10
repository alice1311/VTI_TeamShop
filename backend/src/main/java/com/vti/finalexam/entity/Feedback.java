package com.vti.finalexam.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Feedback")
public class Feedback implements Serializable {
    @Column(name = "feedbackID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "`comment`", length = 255, nullable = false)
    private String comment;

    @Column(name = "feedbackDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date feedback_date;

    @Column(name = "rating", nullable = false)
    @Enumerated(EnumType.STRING)
    private RATING rating;


    public enum RATING {
        VERY_BAD,
        BAD,
        AVERAGE,
        GOOD,
        EXCELLENT;

    }
    @ManyToOne
    @JoinColumn(name = "customerId", nullable = true)
    private Customer account_customer;

    @ManyToOne
    @JoinColumn(name = "productId", nullable = true)
    private Product product_feedback;

    public Feedback() {
    }

    public Feedback(String comment, Date feedback_date, RATING rating, Customer account_customer, Product product_feedback) {
        this.comment = comment;
        this.feedback_date = feedback_date;
        this.rating = rating;
        this.account_customer = account_customer;
        this.product_feedback = product_feedback;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getFeedback_date() {
        return feedback_date;
    }

    public void setFeedback_date(Date feedback_date) {
        this.feedback_date = feedback_date;
    }

    public RATING getRating() {
        return rating;
    }

    public void setRating(RATING rating) {
        this.rating = rating;
    }

    public Account getAccount_customer() {
        return account_customer;
    }

    public void setAccount_customer(Customer account_customer) {
        this.account_customer = account_customer;
    }

    public Product getProduct_feedback() {
        return product_feedback;
    }

    public void setProduct_feedback(Product product_feedback) {
        this.product_feedback = product_feedback;
    }




}

package com.vti.finalexam.form;

import com.vti.finalexam.entity.Feedback;

import java.util.Date;

public class FeedbackCreating {
    private String comment;
    private Feedback.RATING rating;
    private int customer_id;
    private int product_id;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    public Feedback.RATING getRating() {
        return rating;
    }

    public void setRating(Feedback.RATING rating) {
        this.rating = rating;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public FeedbackCreating(String comment, Feedback.RATING rating, int customer_id, int product_id) {
        this.comment = comment;
        this.rating = rating;
        this.customer_id = customer_id;
        this.product_id = product_id;
    }

    public FeedbackCreating() {
    }
}

package com.vti.finalexam.DTO;

import com.vti.finalexam.entity.Customer;
import com.vti.finalexam.entity.Feedback;
import com.vti.finalexam.entity.Product;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

public class FeedbackDTO {
    private int id;
    private String comment;
    private Date feedback_date;
    private Feedback.RATING rating;
    private int customer_id;
    private int product_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FeedbackDTO(int id, String comment, Date feedback_date, Feedback.RATING rating, int customer_id, int product_id) {
        this.id =id;
        this.comment = comment;
        this.feedback_date = feedback_date;
        this.rating = rating;
        this.customer_id = customer_id;
        this.product_id = product_id;
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
}

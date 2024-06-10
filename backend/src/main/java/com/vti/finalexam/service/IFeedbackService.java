package com.vti.finalexam.service;

import com.vti.finalexam.entity.Feedback;
import com.vti.finalexam.entity.Product;
import com.vti.finalexam.form.FeedbackCreating;
import com.vti.finalexam.form.ProductFormCreating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IFeedbackService {
    public Page<Feedback> getAllFeedbacks(Pageable pageable, String search);
    public void createFeedback(FeedbackCreating feedbackCreating);

    public Feedback getFeedbackById(int id);

    public  void deleteFeedback(int id);

    void deleteFeedbacks(List<Integer> ids);
}

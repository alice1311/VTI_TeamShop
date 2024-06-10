package com.vti.finalexam.service;

import com.vti.finalexam.entity.Employee;
import com.vti.finalexam.entity.PaymentMethod;
import com.vti.finalexam.form.AccountFormCreating;
import com.vti.finalexam.form.PaymentMethodCreating;
import com.vti.finalexam.form.updating.AccountFormUpdating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;
import java.util.List;

public interface IPaymentMethodService {
    public Page<PaymentMethod> getAllPaymentMethods(Pageable pageable, String search);
    public List<PaymentMethod> getPaymentMethods();
    public PaymentMethod getPaymentMethodByName(String name);
    public void createPaymentMethod(PaymentMethodCreating formCreating);
    public  void updatePaymentMethod(int id, PaymentMethodCreating paymentMethodCreating);
    public PaymentMethod getPaymentMethodById(int id);
    public  void deletePaymentMethod(int id);
    void deletePaymentMethods(List<Integer> ids);
}

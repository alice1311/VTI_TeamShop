package com.vti.finalexam.service;

import com.vti.finalexam.entity.*;
import com.vti.finalexam.form.PaymentMethodCreating;
import com.vti.finalexam.repository.IPaymentMethodRepository;
import com.vti.finalexam.service.IPaymentMethodService;
import com.vti.finalexam.specification.FeedbackSpecification;
import com.vti.finalexam.specification.PaymentMethodSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Service 
public class PaymentMethodService implements IPaymentMethodService {

    @Autowired
    private IPaymentMethodRepository repository;
    @Override
    public Page<PaymentMethod> getAllPaymentMethods(Pageable pageable, String search) {
        Specification<PaymentMethod> where = null;
        if(!StringUtils.isEmpty(search)){
            PaymentMethodSpecification specification = new PaymentMethodSpecification("name","LIKE", search);
            where = Specification.where(specification);
        }
        return repository.findAll(Specification.where(where), pageable);
    }

    @Override
    public List<PaymentMethod> getPaymentMethods() {
        return repository.findAll();
    }

    @Override
    public PaymentMethod getPaymentMethodByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public void createPaymentMethod(PaymentMethodCreating formCreating) {
        PaymentMethod paymentMethod = new PaymentMethod(formCreating.getName(), formCreating.getDescription_payment());
        repository.save(paymentMethod);
    }

    @Override
    public void updatePaymentMethod(int id, PaymentMethodCreating paymentMethodCreating) {
        PaymentMethod paymentMethod = repository.getPaymentMethodById(id);
        paymentMethod.setName(paymentMethodCreating.getName());
        paymentMethod.setDescription_payment(paymentMethodCreating.getDescription_payment());
        repository.save(paymentMethod);
    }

    @Override
    public PaymentMethod getPaymentMethodById(int id) {
        return repository.getPaymentMethodById(id);
    }

    @Override
    public void deletePaymentMethod(int id) {
        repository.deleteById(id);
    }

    @Override
    public void deletePaymentMethods(List<Integer> ids) {
        repository.deleteByIds(ids);
    }
}

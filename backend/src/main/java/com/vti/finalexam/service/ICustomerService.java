package com.vti.finalexam.service;

import com.vti.finalexam.entity.Account;
import com.vti.finalexam.entity.Customer;
import com.vti.finalexam.form.AccountFormCreating;
import com.vti.finalexam.form.updating.AccountFormUpdating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;
import java.util.List;

public interface ICustomerService {
    public Page<Customer> getAllCustomers(Pageable pageable, String search);
    public Customer getCustomerByUsername(String username);
    public void createCustomer(AccountFormCreating accountFormCreating) throws ParseException;
    public  void updateCustomer(int id, AccountFormUpdating accountFormUpdating) throws ParseException;
    public Customer getCustomerById(int id);
    public  void deleteCustomer(int id);
    void deleteCustomers(List<Integer> ids);

}

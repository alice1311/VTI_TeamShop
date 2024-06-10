package com.vti.finalexam.service;

import com.vti.finalexam.entity.Account;
import com.vti.finalexam.entity.Customer;
import com.vti.finalexam.entity.Order;
import com.vti.finalexam.form.AccountFormCreating;
import com.vti.finalexam.form.updating.AccountFormUpdating;
import com.vti.finalexam.repository.IAccountRepository;
import com.vti.finalexam.repository.ICustomerRepository;
import com.vti.finalexam.repository.IOderRepository;
import com.vti.finalexam.specification.AccountSpecification;
import com.vti.finalexam.specification.CustomerSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import static java.lang.System.in;

@Service
public class CustomerService implements ICustomerService{
    @Autowired
    private ICustomerRepository repository;

    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private IOderRepository oderRepository;
    @Override
    public Page<Customer> getAllCustomers(Pageable pageable, String search) {
        Specification<Customer> where = null;
        if(!StringUtils.isEmpty(search)){
           CustomerSpecification customerSpecification = new CustomerSpecification("name","LIKE", search);
            where = Specification.where(customerSpecification);
        }
        return repository.findAll(Specification.where(where), pageable);
    }

    @Override
    public Customer getCustomerByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public void createCustomer(AccountFormCreating accountFormCreating) throws ParseException {
        Date createdate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = dateFormat.parse(accountFormCreating.getBirthday());
        String password = new BCryptPasswordEncoder().encode((CharSequence) accountFormCreating.getPassword());
        Customer customer = new Customer(accountFormCreating.getUsername(), accountFormCreating.getPhone(), password, accountFormCreating.getFirstName(), accountFormCreating.getLastName(), accountFormCreating.getAddress(), birthday,accountFormCreating.getEmail(), Account.Role.CUSTOMER,accountFormCreating.getGender(), createdate);
        Date creating_date = new Date();
        Order order = new Order(
                creating_date,
                Order.OderStatus.ADDED_TO_CARD,
                customer
        );
        repository.save(customer);
        oderRepository.save(order);

    }

    @Override
    public void updateCustomer(int id, AccountFormUpdating accountFormUpdating) throws ParseException {
        Customer customer = repository.getCustomerById(id);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = dateFormat.parse(accountFormUpdating.getBirthday());
        String password = new BCryptPasswordEncoder().encode((CharSequence) accountFormUpdating.getPassword());
        customer.setPassword(password);
        customer.setFirstName(accountFormUpdating.getFirstName());
        customer.setLastName(accountFormUpdating.getLastName());
        customer.setAddress(accountFormUpdating.getAddress());
        customer.setBirthday(birthday);
        customer.setEmail(accountFormUpdating.getEmail());
        repository.save(customer);
    }

    @Override
    public Customer getCustomerById(int id) {
        return repository.getCustomerById(id);
    }

    @Override
    public void deleteCustomer(int id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteCustomers(List<Integer> ids) {
        for(Integer id : ids){
            repository.deleteById(id);
        }
    }
}

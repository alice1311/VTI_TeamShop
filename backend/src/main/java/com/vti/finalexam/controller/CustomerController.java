package com.vti.finalexam.controller;

import com.vti.finalexam.DTO.AccountDTO;
import com.vti.finalexam.DTO.CustomerDTO;
import com.vti.finalexam.DTO.EmployeeDTO;
import com.vti.finalexam.entity.Account;
import com.vti.finalexam.entity.Customer;
import com.vti.finalexam.entity.Employee;
import com.vti.finalexam.form.AccountFormCreating;
import com.vti.finalexam.form.updating.AccountFormUpdating;
import com.vti.finalexam.service.IAccountService;
import com.vti.finalexam.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.function.Function;

@RestController
@RequestMapping(value = "api/v1/customers")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {
    @Autowired
    private ICustomerService service;

    @GetMapping()
    public ResponseEntity<?> getAllCustomers(Pageable pageable, @RequestParam String search){
        Page<Customer> entities = service.getAllCustomers(pageable, search);
        Page<CustomerDTO> dtos = entities.map(new Function<Customer, CustomerDTO>() {
            @Override
            public CustomerDTO apply(Customer customer) {
                CustomerDTO dto = new CustomerDTO(customer.getId(), customer.getUsername(), customer.getAddress(), customer.getBirthday(), customer.getEmail(),  customer.getGender(), customer.getCreatedDate());
                return dto;
            }
        });
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
     @PostMapping(value = "/register")
    public ResponseEntity<?> createCustomer(@RequestBody AccountFormCreating formCreating) throws ParseException {
        service.createCustomer(formCreating);
        return new ResponseEntity<String>("Create successfully", HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable(name = "id") int id, @RequestBody AccountFormUpdating accountFormUpdating) throws ParseException {
        service.updateCustomer(id, accountFormUpdating);
        return new ResponseEntity<String>("Update successfull!", HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable(name = "id") int id){
        service.deleteCustomer(id);
        return new ResponseEntity<String>("Delete successfull!", HttpStatus.OK);
    }
    @DeleteMapping()
    public void deleteCustomers(@RequestParam(name="ids") List<Integer> ids){
        service.deleteCustomers(ids);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable(name = "id") int id){
        Customer customer = service.getCustomerById(id);
        CustomerDTO dto = new CustomerDTO(customer.getId(), customer.getUsername(), customer.getAddress(), customer.getBirthday(), customer.getEmail(),  customer.getGender(), customer.getCreatedDate());
        return new ResponseEntity<CustomerDTO>(dto, HttpStatus.OK);
    }



}

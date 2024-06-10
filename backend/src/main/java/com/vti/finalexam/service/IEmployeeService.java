package com.vti.finalexam.service;

import com.vti.finalexam.entity.Customer;
import com.vti.finalexam.entity.Employee;
import com.vti.finalexam.form.AccountFormCreating;
import com.vti.finalexam.form.updating.AccountFormUpdating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;
import java.util.List;

public interface IEmployeeService {
    public Page<Employee> getAllEmployees(Pageable pageable, String search);
    public Employee getEmployeeByUsername(String username);
    public void createEmployee(AccountFormCreating accountFormCreating) throws ParseException;
    public  void updateEmployee(int id, AccountFormUpdating accountFormUpdating) throws ParseException;
    public Employee getEmployeeById(int id);
    public  void deleteEmployee(int id);
    void deleteEmployees(List<Integer> ids);
}

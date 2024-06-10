package com.vti.finalexam.service;

import com.vti.finalexam.entity.Account;
import com.vti.finalexam.entity.Customer;
import com.vti.finalexam.entity.Employee;
import com.vti.finalexam.form.AccountFormCreating;
import com.vti.finalexam.form.updating.AccountFormUpdating;
import com.vti.finalexam.repository.ICustomerRepository;
import com.vti.finalexam.repository.IEmployeeRepository;
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
import java.util.Date;
import java.util.List;
@Service
public class EmployeeService implements IEmployeeService{
    @Autowired
    private IEmployeeRepository repository;
    @Override
    public Page<Employee> getAllEmployees(Pageable pageable, String search) {
        Specification<Customer> where = null;
        if(!StringUtils.isEmpty(search)){
            CustomerSpecification customerSpecification = new CustomerSpecification("name","LIKE", search);
            where = Specification.where(customerSpecification);
        }
        return repository.findAll(Specification.where(where), pageable);
    }

    @Override
    public Employee getEmployeeByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public void createEmployee(AccountFormCreating accountFormCreating) throws ParseException {
        Date createdate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = dateFormat.parse(accountFormCreating.getBirthday());
        String password = new BCryptPasswordEncoder().encode((CharSequence) accountFormCreating.getPassword());
        Employee employee = new Employee(accountFormCreating.getUsername(), accountFormCreating.getPhone(), password, accountFormCreating.getFirstName(), accountFormCreating.getLastName(), accountFormCreating.getAddress(), birthday,accountFormCreating.getEmail(), Account.Role.EMPLOYEE,accountFormCreating.getGender(), createdate);
        repository.save(employee);
    }

    @Override
    public void updateEmployee(int id, AccountFormUpdating accountFormUpdating) throws ParseException {
        Employee employee = repository.getEmployeeById(id);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = dateFormat.parse(accountFormUpdating.getBirthday());
        String password = new BCryptPasswordEncoder().encode((CharSequence) accountFormUpdating.getPassword());
        employee.setPassword(password);
        employee.setFirstName(accountFormUpdating.getFirstName());
        employee.setLastName(accountFormUpdating.getLastName());
        employee.setAddress(accountFormUpdating.getAddress());
        employee.setBirthday(birthday);
        employee.setEmail(accountFormUpdating.getEmail());
        repository.save(employee);
    }

    @Override
    public Employee getEmployeeById(int id) {
        return repository.getEmployeeById(id);
    }

    @Override
    public void deleteEmployee(int id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteEmployees(List<Integer> ids) {
        for(Integer id : ids){
            repository.deleteById(id);
        }
    }
}

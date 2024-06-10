package com.vti.finalexam.controller;

import com.vti.finalexam.DTO.CustomerDTO;
import com.vti.finalexam.DTO.EmployeeDTO;
import com.vti.finalexam.DTO.OrderDTO;
import com.vti.finalexam.entity.Customer;
import com.vti.finalexam.entity.Employee;
import com.vti.finalexam.entity.Order;
import com.vti.finalexam.form.AccountFormCreating;
import com.vti.finalexam.form.updating.AccountFormUpdating;
import com.vti.finalexam.service.ICustomerService;
import com.vti.finalexam.service.IEmployeeService;
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
@RequestMapping(value = "api/v1/employees")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {
    @Autowired
    private IEmployeeService service;

    @GetMapping()
    public ResponseEntity<?> getAllEmployees(Pageable pageable, @RequestParam String search){
        Page<Employee> entities = service.getAllEmployees(pageable, search);
        Page<EmployeeDTO> dtos = entities.map(new Function<Employee, EmployeeDTO>() {
            @Override
            public EmployeeDTO apply(Employee employee) {
                EmployeeDTO dto = new EmployeeDTO(employee.getId(), employee.getUsername(), employee.getAddress(), employee.getBirthday(), employee.getEmail(), employee.getCreatedDate(), employee.getGender());
                return dto;
            }

        });
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
    @PostMapping(value = "/create")
    public ResponseEntity<?> createEmployee(@RequestBody AccountFormCreating formCreating) throws ParseException {
        service.createEmployee(formCreating);
        return new ResponseEntity<String>("Create successfully", HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable(name = "id") int id, @RequestBody AccountFormUpdating accountFormUpdating) throws ParseException {
        service.updateEmployee(id, accountFormUpdating);
        return new ResponseEntity<String>("Update successfull!", HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable(name = "id") int id){
        service.deleteEmployee(id);
        return new ResponseEntity<String>("Delete successfull!", HttpStatus.OK);
    }
    @DeleteMapping()
    public void deleteCustomers(@RequestParam(name="ids") List<Integer> ids){
        service.deleteEmployees(ids);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable(name = "id") int id){
        Employee employee = service.getEmployeeById(id);
        EmployeeDTO dto = new EmployeeDTO(employee.getId(), employee.getUsername(), employee.getAddress(), employee.getBirthday(), employee.getEmail(), employee.getCreatedDate(), employee.getGender());
        return new ResponseEntity<EmployeeDTO>(dto, HttpStatus.OK);
    }
}

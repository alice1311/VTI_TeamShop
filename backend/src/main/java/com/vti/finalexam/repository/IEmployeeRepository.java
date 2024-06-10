package com.vti.finalexam.repository;

import com.vti.finalexam.entity.Customer;
import com.vti.finalexam.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {
    public Employee findByUsername(String username);

    <T> Page<Employee> findAll(Specification<T> where, Pageable pageable);
    public boolean existsByUsername(String username);

    public Employee getEmployeeById(int id);

    public Employee deleteById(int id);
}

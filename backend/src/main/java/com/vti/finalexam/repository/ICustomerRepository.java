package com.vti.finalexam.repository;

import com.vti.finalexam.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepository extends JpaRepository<Customer, Integer> {
    public Customer findByUsername(String username);

    <T> Page<Customer> findAll(Specification<T> where, Pageable pageable);
    public boolean existsByUsername(String username);

    public Customer getCustomerById(int id);

    public Customer deleteById(int id);


}

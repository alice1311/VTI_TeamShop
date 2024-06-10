package com.vti.finalexam.repository;

import com.vti.finalexam.entity.Admin;
import com.vti.finalexam.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdminRepository extends JpaRepository<Admin, Integer> {
    public Admin findByUsername(String username);

    <T> Page<Admin> findAll(Specification<T> where, Pageable pageable);
    public boolean existsByUsername(String username);

    public Admin getAdminById(int id);

    public Admin deleteById(int id);
}

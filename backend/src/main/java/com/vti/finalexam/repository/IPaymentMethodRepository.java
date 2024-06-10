package com.vti.finalexam.repository;

import com.vti.finalexam.entity.Customer;
import com.vti.finalexam.entity.Employee;
import com.vti.finalexam.entity.PaymentMethod;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IPaymentMethodRepository extends JpaRepository<PaymentMethod, Integer> {
    public PaymentMethod findByName(String name);
    <T> Page<PaymentMethod> findAll(Specification<T> where, Pageable pageable);
//    public boolean existsByName(String name);

    public PaymentMethod getPaymentMethodById(int id);
    public List<PaymentMethod> findAll();

    public PaymentMethod deleteById(int id);
    @Modifying
    @Transactional
    @Query("DELETE FROM PaymentMethod WHERE id IN(:ids)")
    public void deleteByIds(@Param("ids") List<Integer> ids);
}

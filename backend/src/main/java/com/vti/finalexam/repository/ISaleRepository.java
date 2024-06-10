package com.vti.finalexam.repository;

import com.vti.finalexam.entity.Customer;
import com.vti.finalexam.entity.ProductType;
import com.vti.finalexam.entity.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ISaleRepository extends JpaRepository<Sale, Integer> {
//    public Sale findBySale_info(String sale_info);
    Sale findById(int typeId);
    public void deleteById(int id);
    Sale getSaleById(int id);
    @Modifying
    @Transactional
    @Query("DELETE FROM Sale WHERE id IN(:ids)")
    public void deleteByIds(@Param("ids") List<Integer> ids);

    <T> Page<Sale> findAll(Specification<T> where, Pageable pageable);
}

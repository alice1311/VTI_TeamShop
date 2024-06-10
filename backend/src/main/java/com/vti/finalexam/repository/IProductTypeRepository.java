package com.vti.finalexam.repository;

import com.vti.finalexam.entity.Product;
import com.vti.finalexam.entity.ProductType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IProductTypeRepository extends JpaRepository<ProductType, Integer> {
    public ProductType findByName(String name);
    public boolean existsByName(String name);

    ProductType findById(int typeId);

    public void deleteById(int id);
    ProductType getProductTypeById(int id);
    @Modifying
    @Transactional
    @Query("DELETE FROM ProductType WHERE id IN(:ids)")
    public void deleteByIds(@Param("ids") List<Integer> ids);

    <T> Page<ProductType> findAll(Specification<T> where, Pageable pageable);

    @Override
    List<ProductType> findAll();
}

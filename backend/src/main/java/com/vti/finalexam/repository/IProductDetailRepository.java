package com.vti.finalexam.repository;

import com.vti.finalexam.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IProductDetailRepository extends JpaRepository<ProductDetail, Integer>, JpaSpecificationExecutor<ProductDetail> {
    public ProductDetail getDetailById(int id);
    public void deleteById(int id);
    @Modifying
    @Transactional
    @Query("DELETE FROM ProductDetail WHERE id IN(:ids)")
    public void deleteByIds(@Param("ids") List<Integer> ids);
}

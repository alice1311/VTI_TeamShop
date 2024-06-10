package com.vti.finalexam.repository;

import com.vti.finalexam.entity.Feedback;
import com.vti.finalexam.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IFeedbackRepository extends JpaRepository<Feedback, Integer> {

    public Feedback getFeedbackById(int id);


    public void deleteById(int id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Feedback WHERE id IN(:ids)")
    public void deleteByIds(@Param("ids") List<Integer> ids);

    <T> Page<Feedback> findAll(Specification<T> where, Pageable pageable);
}

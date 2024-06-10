package com.vti.finalexam.repository;

import com.vti.finalexam.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IAccountRepository extends JpaRepository<Account, Integer> {
   public Account findByUsername(String username);

    <T> Page<Account> findAll(Specification<T> where, Pageable pageable);
    public boolean existsByUsername(String username);

    public Account getAccountById(int id);

    public void deleteById(int id);

        @Modifying
        @Transactional
        @Query("DELETE FROM Account WHERE id IN(:ids)")
        public void deleteByIds(@Param("ids") List<Integer> ids);



}

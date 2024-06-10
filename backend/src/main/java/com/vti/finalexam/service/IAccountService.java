package com.vti.finalexam.service;

import com.vti.finalexam.entity.Account;
import com.vti.finalexam.entity.Product;
import com.vti.finalexam.form.AccountFormCreating;
import com.vti.finalexam.form.ProductFormCreating;
import com.vti.finalexam.form.updating.AccountFormUpdating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.text.ParseException;
import java.util.List;

public interface IAccountService extends UserDetailsService {
    public Page<Account> getAllAccounts(Pageable pageable, String search);

    public Account getAccountByUsernam(String username);
//    public void createAccount(AccountFormCreating accountFormCreating);
    public  void updateAccount(int id, AccountFormUpdating accountFormUpdating) throws ParseException;
    public Account getAccountById(int id);
    public Account getAccountByName(String username);
    public  void deleteAccount(int id);
    void deleteAccounts(List<Integer> ids);
    public Page<Account> getAllAccountsByRole(Pageable pageable, String role);


}

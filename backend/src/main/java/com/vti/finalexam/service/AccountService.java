package com.vti.finalexam.service;
import com.vti.finalexam.form.updating.AccountFormUpdating;
import com.vti.finalexam.specification.AccountSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import com.vti.finalexam.entity.Account;
import com.vti.finalexam.form.AccountFormCreating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.vti.finalexam.repository.IAccountRepository;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Service
public class AccountService implements IAccountService {

    @Autowired
    private IAccountRepository repository;
    @Override
    public Page<Account> getAllAccounts(Pageable pageable, String search) {
        Specification<Account> where = null;
        if(!StringUtils.isEmpty(search)){
           AccountSpecification accountSpecification = new AccountSpecification("name","LIKE", search);
            where = Specification.where(accountSpecification);
        }
        return repository.findAll(Specification.where(where), pageable);
    }


    @Override
    public Account getAccountByUsernam(String username) {
        return repository.findByUsername(username);
    }

//    @Override
//    public void createAccount(AccountFormCreating form) {
//        //convert
//        String password = new BCryptPasswordEncoder().encode((CharSequence) form.getPassword());
//        Account account = new Account(form.getUsername(), password, form.getFirstName(), form.getLastName(), form.getAddress(), form.getRole());
//        repository.save(account);
//
//    }

    @Override
    public void updateAccount(int id, AccountFormUpdating accountFormUpdating) throws ParseException {
        Account account = repository.getAccountById(id);
        String password = new BCryptPasswordEncoder().encode((CharSequence) accountFormUpdating.getPassword());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = dateFormat.parse(accountFormUpdating.getBirthday());
        account.setPassword(password);
        account.setFirstName(accountFormUpdating.getFirstName());
        account.setLastName(accountFormUpdating.getLastName());
        account.setAddress(accountFormUpdating.getAddress());
        account.setBirthday(birthday);
        account.setEmail(accountFormUpdating.getEmail());
        repository.save(account);
    }

    @Override
    public Account getAccountById(int id) {
        return repository.getAccountById(id);
    }

    @Override
    public Account getAccountByName(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public void deleteAccount(int id) {
         repository.deleteById(id);
    }

    @Override
    public void deleteAccounts(List<Integer> ids) {
        repository.deleteByIds(ids);
    }

    @Override
    public Page<Account> getAllAccountsByRole(Pageable pageable, String role) {
        Specification<Account> where = null;
        if(!StringUtils.isEmpty(role)){
            AccountSpecification accountSpecification = new AccountSpecification("Role","=", role);
            where = Specification.where(accountSpecification);
        }
        return repository.findAll(Specification.where(where), pageable);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = repository.findByUsername(username);
        if (account == null){
            throw new UsernameNotFoundException(username);
        }
//        new BCryptPasswordEncoder().encode("123456");
        return new User(account.getUsername(), account.getPassword(), AuthorityUtils.createAuthorityList(account.getRole().toString()));
    }


}

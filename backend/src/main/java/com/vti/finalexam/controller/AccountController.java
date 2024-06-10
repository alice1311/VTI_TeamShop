package com.vti.finalexam.controller;

import com.vti.finalexam.DTO.AccountDTO;
import com.vti.finalexam.entity.Account;
import com.vti.finalexam.form.AccountFormCreating;
import com.vti.finalexam.form.ProductFormCreating;
import com.vti.finalexam.form.updating.AccountFormUpdating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.vti.finalexam.service.AccountService;
import com.vti.finalexam.service.IAccountService;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@RestController
@RequestMapping(value = "api/v1/accounts")
@CrossOrigin(origins = "http://localhost:3000")
public class AccountController {
    @Autowired
    private IAccountService service;

    @GetMapping()
    public ResponseEntity<?> getAllAccounts(Pageable pageable, @RequestParam String search){
        Page<Account> entities = service.getAllAccounts(pageable, search);
        Page<AccountDTO> dtos = entities.map(new Function<Account, AccountDTO>() {
            @Override
            public AccountDTO apply(Account account) {
                AccountDTO dto = new AccountDTO(account.getUsername(), account.getLastName(),account.getCreatedDate(), account.getRole());
                return dto;
            }
        });
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<?> createAccount(@RequestBody AccountFormCreating formCreating){
//        service.createAccount(formCreating);
//        return new ResponseEntity<String>("Create successfully", HttpStatus.CREATED);
        return null;
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateAccount(@PathVariable(name = "id") int id, @RequestBody AccountFormUpdating accountFormUpdating) throws ParseException {
        service.updateAccount(id, accountFormUpdating);
        return new ResponseEntity<String>("Update successfull!", HttpStatus.OK);
    }
    @DeleteMapping()
    public void deleteAccounts(@RequestParam(name="ids") List<Integer> ids){
        service.deleteAccounts(ids);
    }
}

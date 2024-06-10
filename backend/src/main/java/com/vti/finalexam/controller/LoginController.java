package com.vti.finalexam.controller;

import com.vti.finalexam.DTO.LoginInfoDTO;
import com.vti.finalexam.entity.Account;
import com.vti.finalexam.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(value = "api/v1/login")
@CrossOrigin("http://localhost:3000")
public class LoginController {
    @Autowired
    private IAccountService accountService;

    @GetMapping()
    public ResponseEntity<?> login(Principal principal){
        String username = principal.getName();
        Account entity = accountService.getAccountByUsernam(username);
        String fullName = entity.getFirstName() +" "+entity.getLastName();
        LoginInfoDTO dto = new LoginInfoDTO((entity.getId()), fullName, entity.getEmail(), entity.getRole());
                return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}

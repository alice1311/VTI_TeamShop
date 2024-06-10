package com.vti.finalexam.controller;

import com.vti.finalexam.DTO.AdminDTO;
import com.vti.finalexam.DTO.CustomerDTO;
import com.vti.finalexam.entity.Admin;
import com.vti.finalexam.entity.Customer;
import com.vti.finalexam.form.AccountFormCreating;
import com.vti.finalexam.form.updating.AccountFormUpdating;
import com.vti.finalexam.service.IAdminService;
import com.vti.finalexam.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.function.Function;

@RestController
@RequestMapping(value = "api/v1/admins")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {
    @Autowired
    private IAdminService service;

    @GetMapping()
    public ResponseEntity<?> getAllAdmins(Pageable pageable, @RequestParam String search){
        Page<Admin> entities = service.getAllAdmins(pageable, search);
        Page<AdminDTO> dtos = entities.map(new Function<Admin, AdminDTO>() {
            @Override
            public AdminDTO apply(Admin admin) {
                AdminDTO dto = new AdminDTO(admin.getId(),admin.getUsername(), admin.getAddress(), admin.getBirthday(), admin.getEmail(), admin.getCreatedDate(), admin.getGender());
                return dto;
            }


        });
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<?> createAdmin(@RequestBody AccountFormCreating formCreating) throws ParseException {
        service.createAdmin(formCreating);
        return new ResponseEntity<String>("Create successfully", HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateAdmin(@PathVariable(name = "id") int id, @RequestBody AccountFormUpdating accountFormUpdating) throws ParseException {
        service.updateAdmin(id, accountFormUpdating);
        return new ResponseEntity<String>("Update successfull!", HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable(name = "id") int id){
        service.deleteAdmin(id);
        return new ResponseEntity<String>("Delete successfull!", HttpStatus.OK);
    }
    @DeleteMapping()
    public void deleteAdmins(@RequestParam(name="ids") List<Integer> ids){
        service.deleteAdmins(ids);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getAdminById(@PathVariable(name = "id") int id){
        Admin admin = service.getAdminById(id);
        AdminDTO dto = new AdminDTO(admin.getId(), admin.getUsername(), admin.getAddress(), admin.getBirthday(), admin.getEmail(), admin.getCreatedDate(), admin.getGender());
        return new ResponseEntity<AdminDTO>(dto, HttpStatus.OK);
    }
}


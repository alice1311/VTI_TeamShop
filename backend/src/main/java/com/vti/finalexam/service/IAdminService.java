package com.vti.finalexam.service;

import com.vti.finalexam.entity.Account;
import com.vti.finalexam.entity.Admin;
import com.vti.finalexam.entity.Customer;
import com.vti.finalexam.entity.Employee;
import com.vti.finalexam.form.AccountFormCreating;
import com.vti.finalexam.form.updating.AccountFormUpdating;
import com.vti.finalexam.repository.IEmployeeRepository;
import com.vti.finalexam.specification.CustomerSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public interface IAdminService {

    public Page<Admin> getAllAdmins(Pageable pageable, String search);
    public Admin getAdminByUsername(String username);
    public void createAdmin(AccountFormCreating accountFormCreating) throws ParseException;
    public  void updateAdmin(int id, AccountFormUpdating accountFormUpdating) throws ParseException;
    public Admin getAdminById(int id);
    public  void deleteAdmin(int id);
    void deleteAdmins(List<Integer> ids);
}

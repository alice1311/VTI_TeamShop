package com.vti.finalexam.service;

import com.vti.finalexam.entity.Account;
import com.vti.finalexam.entity.Admin;
import com.vti.finalexam.entity.Customer;
import com.vti.finalexam.form.AccountFormCreating;
import com.vti.finalexam.form.updating.AccountFormUpdating;
import com.vti.finalexam.repository.IAdminRepository;
import com.vti.finalexam.repository.ICustomerRepository;
import com.vti.finalexam.specification.AdminSpecification;
import com.vti.finalexam.specification.CustomerSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Service
public class AdminService implements IAdminService{
    @Autowired
    private IAdminRepository repository;
    @Override
    public Page<Admin> getAllAdmins(Pageable pageable, String search) {
        Specification<Admin> where = null;
        if(!StringUtils.isEmpty(search)){
            AdminSpecification adminSpecification = new AdminSpecification("name","LIKE", search);
            where = Specification.where(adminSpecification);
        }
        return repository.findAll(Specification.where(where), pageable);
    }

    @Override
    public Admin getAdminByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public void createAdmin(AccountFormCreating accountFormCreating) throws ParseException {
        Date createdate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = dateFormat.parse(accountFormCreating.getBirthday());
        String password = new BCryptPasswordEncoder().encode((CharSequence) accountFormCreating.getPassword());
        Admin admin = new Admin(accountFormCreating.getUsername(), accountFormCreating.getPhone(), password, accountFormCreating.getFirstName(), accountFormCreating.getLastName(), accountFormCreating.getAddress(), birthday,accountFormCreating.getEmail(),Account.Role.ADMIN,accountFormCreating.getGender(), createdate);

        repository.save(admin);
    }

    @Override
    public void updateAdmin(int id, AccountFormUpdating accountFormUpdating) throws ParseException {
        Admin admin = repository.getAdminById(id);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = dateFormat.parse(accountFormUpdating.getBirthday());
        String password = new BCryptPasswordEncoder().encode((CharSequence) accountFormUpdating.getPassword());
        admin.setPassword(password);
        admin.setFirstName(accountFormUpdating.getFirstName());
        admin.setLastName(accountFormUpdating.getLastName());
        admin.setAddress(accountFormUpdating.getAddress());
        admin.setBirthday(birthday);
        admin.setEmail(accountFormUpdating.getEmail());
        repository.save(admin);
    }

    @Override
    public Admin getAdminById(int id) {
        return repository.getAdminById(id);
    }

    @Override
    public void deleteAdmin(int id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAdmins(List<Integer> ids) {
        for(Integer id : ids){
            repository.deleteById(id);
        }
    }
}

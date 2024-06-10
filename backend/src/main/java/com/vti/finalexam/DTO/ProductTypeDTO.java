package com.vti.finalexam.DTO;

import com.vti.finalexam.entity.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

public class ProductTypeDTO {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ProductTypeDTO() {
    }

    public ProductTypeDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

}

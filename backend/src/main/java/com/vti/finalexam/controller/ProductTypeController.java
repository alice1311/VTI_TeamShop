package com.vti.finalexam.controller;

import com.vti.finalexam.DTO.AccountDTO;
import com.vti.finalexam.DTO.ProductDTO;
import com.vti.finalexam.DTO.ProductDetailDTO;
import com.vti.finalexam.DTO.ProductTypeDTO;
import com.vti.finalexam.entity.Account;
import com.vti.finalexam.entity.Product;
import com.vti.finalexam.entity.ProductType;
import com.vti.finalexam.form.ProductFormCreating;
import com.vti.finalexam.form.ProductTypeFormCreating;
import com.vti.finalexam.service.IProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@RestController
@RequestMapping(value = "api/v1/productTypes")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductTypeController {
    @Autowired
    private IProductTypeService service;

    @GetMapping(value="/all")
    public ResponseEntity<?> getAllProductTypes(Pageable pageable, @RequestParam String search){
        Page<ProductType> entitiesPage = service.getAllProductTypes(pageable, search);
        Page<ProductTypeDTO> dtosPage = entitiesPage.map(new Function<ProductType, ProductTypeDTO>(){
            public ProductTypeDTO apply(ProductType productType){
                ProductTypeDTO dto = new ProductTypeDTO(productType.getId(), productType.getName());
                return dto;
            }
        });
        return new ResponseEntity<>(dtosPage, HttpStatus.OK);
    }
    @GetMapping(value="/full")
    public ResponseEntity<?> getFullProductTypes(){
        List<ProductType> entities = service.getAllProductTypes();
        ArrayList<ProductTypeDTO> productTypeDTOS = new ArrayList<>();
        for(ProductType productType: entities){
            ProductTypeDTO productTypeDTO = new ProductTypeDTO(productType.getId(), productType.getName());
            productTypeDTOS.add(productTypeDTO);
        }
        return new ResponseEntity<>(productTypeDTOS, HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> createProductType(@RequestBody ProductTypeFormCreating formCreating){
        service.createProductType(formCreating);
        return new ResponseEntity<String>("Create successfully", HttpStatus.CREATED);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteProductType(@PathVariable(name = "id") int id){
        service.deleteProductType(id);
        return new ResponseEntity<String>("Delete successfull!", HttpStatus.OK);
    }


    @PutMapping(value = "update/{id}")
    public ResponseEntity<?> updateProductType(@PathVariable(name = "id") int id, @RequestBody ProductTypeFormCreating formCreating){
        service.updateProductType(id, formCreating);
        return new ResponseEntity<String>("Update successfull!", HttpStatus.OK);
    }

    @DeleteMapping()
    public void deleteProductTypes(@RequestParam(name="ids") List<Integer> ids){
        service.deleteProductTypes(ids);
    }

    @GetMapping(value = "delete/{id}")
    public ResponseEntity<?> getProductTypeById(@PathVariable(name = "id") int id){
        ProductType productType= service.getProductTypeById(id);
        ProductTypeDTO productTypeDTO = new ProductTypeDTO(
                productType.getId(),
                productType.getName()
        );
        return new ResponseEntity<ProductTypeDTO>(productTypeDTO, HttpStatus.OK);
    }
}


package com.vti.finalexam.controller;

import com.vti.finalexam.DTO.ProductTypeDTO;
import com.vti.finalexam.DTO.SaleDTO;
import com.vti.finalexam.entity.ProductType;
import com.vti.finalexam.entity.Sale;
import com.vti.finalexam.form.ProductTypeFormCreating;
import com.vti.finalexam.form.SaleFormCreating;
import com.vti.finalexam.service.IProductTypeService;
import com.vti.finalexam.service.ISaleService;
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
@RequestMapping(value = "api/v1/sales")
@CrossOrigin(origins = "http://localhost:3000")
public class SaleController {
    @Autowired
    private ISaleService service;

    @GetMapping()
    public ResponseEntity<?> getAllSaleTypes(Pageable pageable, @RequestParam String search){
        Page<Sale> entitiesPage = service.getAllSales(pageable, search);
        Page<SaleDTO> dtosPage = entitiesPage.map(new Function<Sale, SaleDTO>(){
            @Override
            public SaleDTO apply(Sale sale) {
                SaleDTO dto = new SaleDTO(sale.getId(), sale.getSale_info(),sale.getPercent_sale(),sale.getStart_date().toString(),sale.getEnd_date().toString());
                return dto;
            }

        });
        return new ResponseEntity<>(dtosPage, HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> createSale(@RequestBody SaleFormCreating formCreating) throws ParseException {
        service.createSale(formCreating);
        return new ResponseEntity<String>("Create successfully", HttpStatus.CREATED);
    }
    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<?> deleteSale(@PathVariable(name = "id") int id){
        service.deleteSale(id);
        return new ResponseEntity<String>("Delete successfull!", HttpStatus.OK);
    }


    @PutMapping(value = "update/{id}")
    public ResponseEntity<?> updateSale(@PathVariable(name = "id") int id, @RequestBody SaleFormCreating formCreating) throws ParseException {
        service.updateSale(id, formCreating);
        return new ResponseEntity<String>("Update successfull!", HttpStatus.OK);
    }

    @DeleteMapping()
    public void deleteSales(@RequestParam(name="ids") List<Integer> ids){
        service.deleteSales(ids);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getSaleById(@PathVariable(name = "id") int id){
        Sale sale= service.getSaleById(id);
        SaleDTO saleDTO = new SaleDTO(
                sale.getId(),
                sale.getSale_info(),
                sale.getPercent_sale(),
                sale.getStart_date().toString(),
                sale.getEnd_date().toString()
        );
        return new ResponseEntity<SaleDTO>(saleDTO, HttpStatus.OK);
    }
}

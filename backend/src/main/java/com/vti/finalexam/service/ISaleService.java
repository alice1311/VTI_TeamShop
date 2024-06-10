package com.vti.finalexam.service;

import com.vti.finalexam.entity.ProductType;
import com.vti.finalexam.entity.Sale;
import com.vti.finalexam.form.ProductTypeFormCreating;
import com.vti.finalexam.form.SaleFormCreating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;
import java.util.List;

public interface ISaleService {

    public Page<Sale> getAllSales(Pageable pageable, String search);
    public void createSale(SaleFormCreating saleFormCreating) throws ParseException;

    public  void updateSale(int id, SaleFormCreating saleFormCreating) throws ParseException;
    public Sale getSaleById(int id);

    void deleteSale(int id);
    void deleteSales(List<Integer> ids);
}

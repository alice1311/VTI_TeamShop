package com.vti.finalexam.service;

import com.vti.finalexam.entity.ProductType;
import com.vti.finalexam.form.ProductTypeFormCreating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductTypeService {
    public Page<ProductType> getAllProductTypes(Pageable pageable, String search);
    public List<ProductType> getAllProductTypes();
    public void createProductType(ProductTypeFormCreating productTypeFormCreating);

    public  void updateProductType(int id, ProductTypeFormCreating productTypeFormCreating);
    public ProductType getProductTypeById(int id);

    void deleteProductType(int id);
    void deleteProductTypes(List<Integer> ids);
}

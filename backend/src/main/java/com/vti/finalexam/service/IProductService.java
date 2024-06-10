package com.vti.finalexam.service;

import com.vti.finalexam.entity.Account;
import com.vti.finalexam.entity.Product;
import com.vti.finalexam.form.AccountFormCreating;
import com.vti.finalexam.form.ProductFormCreating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IProductService {
    public Page<Product> getAllProducts(Pageable pageable, String search);
    public void createProduct(ProductFormCreating productFormCreating);
    public  void updateProduct(int id, ProductFormCreating productFormCreating);

    public List<Product> getFullProduct();
     public Product getProductById(int id);

//    public boolean doesProductExist(String name, String color, String size);
    public  void deleteProduct(int id);

    void deleteProducts(List<Integer> ids);
}

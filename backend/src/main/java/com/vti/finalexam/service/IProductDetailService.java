package com.vti.finalexam.service;

import com.vti.finalexam.entity.Product;
import com.vti.finalexam.entity.ProductDetail;
import com.vti.finalexam.form.ProductDetailFormCreating;
import com.vti.finalexam.form.ProductFormCreating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductDetailService {
    public Page<ProductDetail> getAllProductDetails(Pageable pageable, String search);
    public void createProductDetail(ProductDetailFormCreating formCreating);
    public  void updateProductDetail(int id, ProductDetailFormCreating formCreating);

    public ProductDetail getProductDetailById(int id);

    //    public boolean doesProductExist(String name, String color, String size);
    public  void deleteProductDetail(int id);

    void deleteProductDetails(List<Integer> ids);
}

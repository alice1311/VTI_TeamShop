package com.vti.finalexam.service;

import com.vti.finalexam.entity.Product;
import com.vti.finalexam.entity.ProductDetail;
import com.vti.finalexam.entity.ProductType;
import com.vti.finalexam.form.ProductDetailFormCreating;
import com.vti.finalexam.repository.IProductDetailRepository;
import com.vti.finalexam.repository.IProductRepository;
import com.vti.finalexam.repository.IProductTypeRepository;
import com.vti.finalexam.specification.ProductDetailSpecification;
import com.vti.finalexam.specification.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class ProductDetailService implements IProductDetailService{
    @Autowired
    private IProductDetailRepository repository;
    @Autowired
    private IProductRepository productRepository;
    @Override
    public Page<ProductDetail> getAllProductDetails(Pageable pageable, String search) {
        Specification<ProductDetail> where = null;
        if(!StringUtils.isEmpty(search)){
            ProductDetailSpecification searchSpecification = new ProductDetailSpecification("name","LIKE", search);
            where = Specification.where(searchSpecification);
        }
        return repository.findAll(Specification.where(where), pageable);
    }

    @Override
    public void createProductDetail(ProductDetailFormCreating formCreating) {
        Product product = productRepository.getProductById(formCreating.getProduct_id());
        ProductDetail productDetail = new ProductDetail(
                formCreating.getQuantity(),
                formCreating.getImg_url(),
                formCreating.getColor(),
                formCreating.getSize(),
                product
        );
        repository.save(productDetail);
    }

    @Override
    public void updateProductDetail(int id, ProductDetailFormCreating formCreating) {
        ProductDetail productDetail = repository.getDetailById(id);
        productDetail.setQuantity(formCreating.getQuantity());
        productDetail.setImg_url(formCreating.getImg_url());
        Product product = productDetail.getProduct_detail();
        List<ProductDetail> productDetails = product.getProductDetails();
        for(ProductDetail productDetail1 : productDetails){
            if(productDetail1.getColor().equals(formCreating.getColor()) ){
                productDetail1.setImg_url(formCreating.getImg_url());
                repository.save(productDetail1);
            }

        }
        productDetail.setColor(formCreating.getColor());
        productDetail.setSize(formCreating.getSize());
        repository.save(productDetail);
    }

    @Override
    public ProductDetail getProductDetailById(int id) {
        return repository.getDetailById(id);
    }

    @Override
    public void deleteProductDetail(int id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteProductDetails(List<Integer> ids) {
        repository.deleteByIds(ids);
    }
}

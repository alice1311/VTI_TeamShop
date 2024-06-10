package com.vti.finalexam.service;

import com.vti.finalexam.entity.ProductType;
import com.vti.finalexam.form.ProductTypeFormCreating;
import com.vti.finalexam.repository.IProductTypeRepository;
import com.vti.finalexam.specification.ProductTypeSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
@Service
@Transactional
public class ProductTypeService implements IProductTypeService{
    @Autowired
    private IProductTypeRepository repository;
    @Override
    public Page<ProductType> getAllProductTypes(Pageable pageable, String search) {
        Specification<ProductType> where = null;
        if(!StringUtils.isEmpty(search)){
            ProductTypeSpecification searchSpecification = new ProductTypeSpecification("name","LIKE", search);
            where = Specification.where(searchSpecification);
        }
        return repository.findAll(Specification.where(where), pageable);
    }

    @Override
    public List<ProductType> getAllProductTypes() {
        return repository.findAll();
    }

    @Override
    public void createProductType(ProductTypeFormCreating productTypeFormCreating) {

        ProductType productType = new ProductType(
                productTypeFormCreating.getName()
        );
        repository.save(productType);
    }

    @Override
    public void updateProductType(int id, ProductTypeFormCreating productTypeFormCreating) {
        ProductType productType = repository.getProductTypeById(id);
        productType.setName(productTypeFormCreating.getName());
        repository.save(productType);
    }

    @Override
    public ProductType getProductTypeById(int id) {
        return repository.findById(id);
    }

    @Override
    public void deleteProductType(int id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteProductTypes(List<Integer> ids) {
        repository.deleteByIds(ids);
    }
}

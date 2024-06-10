package com.vti.finalexam.service;

import com.vti.finalexam.entity.Product;
import com.vti.finalexam.entity.ProductType;
import com.vti.finalexam.entity.Sale;
import com.vti.finalexam.form.ProductFormCreating;
import com.vti.finalexam.repository.IProductRepository;
import com.vti.finalexam.repository.IProductTypeRepository;
import com.vti.finalexam.repository.ISaleRepository;
import com.vti.finalexam.specification.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.Spliterator;

@Service
@Transactional
public class ProductService implements IProductService{
    @Autowired
    private IProductRepository repository;
    @Autowired
    private IProductTypeRepository productTypeRepository;
    @Autowired
    private ISaleRepository saleRepository;


    @Override
    public Page<Product> getAllProducts(Pageable pageable, String search) {
        Specification<Product> where = null;
        if(!StringUtils.isEmpty(search)){
            ProductSpecification searchSpecification = new ProductSpecification("name","LIKE", search);
            where = Specification.where(searchSpecification);
        }
        return repository.findAll(Specification.where(where), pageable);
    }

    @Override
    public void createProduct(ProductFormCreating productFormCreating) {
        ProductType productType = productTypeRepository.findById(productFormCreating.getType_id());
        Sale sale = null;
        if(0 != productFormCreating.getSale_id()){
            sale = saleRepository.getSaleById(productFormCreating.getSale_id());
        }
        Product product = new Product(
                        productFormCreating.getName(),
                        productFormCreating.getDescription(),
                        productFormCreating.getImage_url(),
                        productFormCreating.getPrice(),
                        sale,
                        productType,
                        productFormCreating.getGenderType()
                        );
        repository.save(product);
    }

    @Override
    public void updateProduct(int id, ProductFormCreating productFormCreating) {
        ProductType productType = productTypeRepository.findById(productFormCreating.getType_id());
        Product product = repository.getProductById(id);
        product.setName(productFormCreating.getName());
        product.setDescription(productFormCreating.getDescription());
        product.setImage_url(productFormCreating.getImage_url());
        product.setTypeProduct(productType);
        repository.save(product);
    }

    @Override
    public List<Product> getFullProduct() {
        return repository.findAll();
    }

    @Override
    public Product getProductById(int id) {
        return repository.getProductById(id);
    }

//    @Override
//    public boolean doesProductExist(String name, String color, String size) {
//        Optional<Product> product = repository.findByNameAndColorAndSize(name, color, size);
//        return product.isPresent();
//    }

    @Override
    public void deleteProduct(int id) {
         repository.deleteById(id);
    }

    @Override
    public void deleteProducts(List<Integer> ids) {
        repository.deleteByIds(ids);
    }
}

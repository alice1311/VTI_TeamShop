package com.vti.finalexam.controller;

import com.vti.finalexam.DTO.ProductDTO;
import com.vti.finalexam.DTO.ProductDetailDTO;
import com.vti.finalexam.entity.Product;
import com.vti.finalexam.entity.ProductDetail;
import com.vti.finalexam.form.ProductDetailFormCreating;
import com.vti.finalexam.form.ProductFormCreating;
import com.vti.finalexam.service.IProductDetailService;
import com.vti.finalexam.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Function;

@RestController
@RequestMapping(value = "api/v1/productDetails")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductDetailController {
    @Autowired
    private IProductDetailService service;
    @GetMapping()
    public ResponseEntity<?> getAllProductDetails(Pageable pageable, @RequestParam String search){
        Page<ProductDetail> entitiesPage = service.getAllProductDetails(pageable, search);
        Page<ProductDetailDTO> dtoPage = entitiesPage.map(new Function<ProductDetail, ProductDetailDTO>() {
            @Override
            public ProductDetailDTO apply(ProductDetail productDetail) {
                ProductDetailDTO dto = new ProductDetailDTO(productDetail.getId(), productDetail.getQuantity(), productDetail.getImg_url(), productDetail.getColor(), productDetail.getSize(), productDetail.getProduct_detail().getId());
                return dto;
            }

        });
        return new ResponseEntity<>(dtoPage, HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> createProductDetail(@RequestBody ProductDetailFormCreating formCreating){
        service.createProductDetail(formCreating);
        return new ResponseEntity<String>("Create successfully", HttpStatus.CREATED);
    }

    @PutMapping(value = "update/{id}")
    public ResponseEntity<?> updateProductDetail(@PathVariable(name = "id") int id, @RequestBody ProductDetailFormCreating formCreating){
        service.updateProductDetail(id, formCreating);
        return new ResponseEntity<String>("Update successfull!", HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getProductDetailById(@PathVariable(name = "id") int id){
        ProductDetail productDetail = service.getProductDetailById(id);
        ProductDetailDTO productDetailDTO = new ProductDetailDTO(
                productDetail.getId(),
                productDetail.getQuantity(),
                productDetail.getImg_url(),
                productDetail.getColor(),
                productDetail.getSize(),
                productDetail.getProduct_detail().getId()
        );
        return new ResponseEntity<ProductDetailDTO>(productDetailDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<?> deleteProductDeatil(@PathVariable(name = "id") int id){
        service.deleteProductDetail(id);
        return new ResponseEntity<String>("Delete successfull!", HttpStatus.OK);
    }

//    @GetMapping("/checkExistence/{name}/{color}/{size}")
//    public boolean checkProductExistence(
//            @PathVariable String name,
//            @PathVariable String color,
//            @PathVariable String size
//    ) {
//        boolean exists = service.doesProductExist(name, color, size);
//        return exists;
//    }

    @DeleteMapping()
    public void deleteProductDetails(@RequestParam(name="ids") List<Integer> ids){
        service.deleteProductDetails(ids);
    }
}

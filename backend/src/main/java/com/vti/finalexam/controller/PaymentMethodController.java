package com.vti.finalexam.controller;

import com.vti.finalexam.DTO.PaymentMethodDTO;
import com.vti.finalexam.DTO.ProductDTO;
import com.vti.finalexam.entity.PaymentMethod;
import com.vti.finalexam.entity.Product;
import com.vti.finalexam.form.PaymentMethodCreating;
import com.vti.finalexam.form.ProductFormCreating;
import com.vti.finalexam.service.IPaymentMethodService;
import com.vti.finalexam.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@RestController
@RequestMapping(value = "api/v1/paymentMethods")
@CrossOrigin(origins = "http://localhost:3000")
public class PaymentMethodController {
    @Autowired
    private IPaymentMethodService service;
    @GetMapping()
    public ResponseEntity<?> getAllPaymentMethods(Pageable pageable, @RequestParam String search){
        Page<PaymentMethod> entitiesPage = service.getAllPaymentMethods(pageable, search);
        Page<PaymentMethodDTO> dtoPage = entitiesPage.map(new Function<PaymentMethod, PaymentMethodDTO>() {
            @Override
            public PaymentMethodDTO apply(PaymentMethod paymentMethod) {
                PaymentMethodDTO dto = new PaymentMethodDTO(paymentMethod.getId(), paymentMethod.getName(),paymentMethod.getDescription_payment());
                return dto;
            }

        });
        return new ResponseEntity<>(dtoPage, HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<?> getPaymentMethods(){
       List<PaymentMethod> paymentMethods = service.getPaymentMethods();
       ArrayList<PaymentMethodDTO> paymentMethodDTOS = new ArrayList<>();
       for (PaymentMethod paymentMethod : paymentMethods){
           PaymentMethodDTO paymentMethodDTO = new PaymentMethodDTO(paymentMethod.getId(), paymentMethod.getName(), paymentMethod.getDescription_payment());
           paymentMethodDTOS.add(paymentMethodDTO);
       }
        return new ResponseEntity<>(paymentMethodDTOS, HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> createPaymentMethod(@RequestBody PaymentMethodCreating formCreating){
        service.createPaymentMethod(formCreating);
        return new ResponseEntity<String>("Create successfully", HttpStatus.CREATED);
    }

    @PutMapping(value = "update/{id}")
    public ResponseEntity<?> updatePaymentMethod(@PathVariable(name = "id") int id, @RequestBody PaymentMethodCreating formCreating){
        service.updatePaymentMethod(id, formCreating);
        return new ResponseEntity<String>("Update successfull!", HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getPaymentMethodById(@PathVariable(name = "id") int id){
        PaymentMethod paymentMethod = service.getPaymentMethodById(id);
        PaymentMethodDTO paymentMethodDTO = new PaymentMethodDTO(
                paymentMethod.getId(),
                paymentMethod.getName(),
                paymentMethod.getDescription_payment());
        return new ResponseEntity<PaymentMethodDTO>(paymentMethodDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<?> deletePaymentMethod(@PathVariable(name = "id") int id){
        service.deletePaymentMethod(id);
        return new ResponseEntity<String>("Delete successfull!", HttpStatus.OK);
    }


    @DeleteMapping()
    public void deletePaymentMethods(@RequestParam(name="ids") List<Integer> ids){
        service.deletePaymentMethods(ids);
    }
}

package com.vti.finalexam.controller;

import com.vti.finalexam.DTO.OderItemDTO;
import com.vti.finalexam.DTO.OrderDTO;
import com.vti.finalexam.entity.Order;
import com.vti.finalexam.entity.OrderItem;
import com.vti.finalexam.form.OrderFormCreating;
import com.vti.finalexam.form.OrderItemFormCreating;
import com.vti.finalexam.form.updating.OrderItemFormUpdating;
import com.vti.finalexam.service.IOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Function;

@RestController
@RequestMapping(value = "api/v1/orderItems")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderItemController {
    @Autowired
    private IOrderItemService service;
    @GetMapping()
    public ResponseEntity<?> getAllOrderItems(Pageable pageable, @RequestParam String search){
        Page<OrderItem> entitiesPage = service.getAllOrderItems(pageable, search);
        Page<OderItemDTO> dtoPage = entitiesPage.map(new Function<OrderItem, OderItemDTO>() {
            @Override
            public OderItemDTO apply(OrderItem orderItem) {
                OderItemDTO dto = new OderItemDTO(orderItem.getId(),orderItem.getSell_price(), orderItem.getSubtotal(), orderItem.getQuantity(),orderItem.getOrder().getId(), orderItem.getProduct_detail_order().getId());

                return dto;
            }

        });
        return new ResponseEntity<>(dtoPage, HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> createOrderItem(@RequestBody OrderItemFormCreating formCreating){
        service.createOderItem(formCreating);
        return new ResponseEntity<String>("Create successfully", HttpStatus.CREATED);
    }

    @PutMapping(value = "update/{id}")
    public ResponseEntity<?> updateOrderItem(@PathVariable(name = "id") int id, @RequestBody OrderItemFormUpdating formUpdating){
        service.updateOderItem(id, formUpdating);
        return new ResponseEntity<String>("Update successfull!", HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getOrderItemById(@PathVariable(name = "id") int id){
       OrderItem orderItem = service.getOrderItemById(id);
        OderItemDTO dto = new OderItemDTO(orderItem.getId(), orderItem.getSell_price(), orderItem.getSubtotal(), orderItem.getQuantity(),orderItem.getOrder().getId(), orderItem.getProduct_detail_order().getId());
        return new ResponseEntity<OderItemDTO>(dto, HttpStatus.OK);
    }

    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<?> deleteOrderItem(@PathVariable(name = "id") int id){
        service.deleteOrderItem(id);
        return new ResponseEntity<String>("Delete successfull!", HttpStatus.OK);
    }


    @DeleteMapping()
    public void deleteOrderItems(@RequestParam(name="ids") List<Integer> ids) {
        service.deleteOrderItems(ids);
    }
}

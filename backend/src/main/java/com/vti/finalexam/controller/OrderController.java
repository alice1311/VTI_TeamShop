package com.vti.finalexam.controller;

import com.vti.finalexam.DTO.*;
import com.vti.finalexam.entity.*;
import com.vti.finalexam.form.OrderCustomerCreatForm;
import com.vti.finalexam.form.OrderFormCreating;
import com.vti.finalexam.form.OrderItemForm;
import com.vti.finalexam.form.ProductFormCreating;
import com.vti.finalexam.service.*;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@RestController
@RequestMapping(value = "api/v1/orders")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {
    @Autowired
    private IOrderService service;

    @Autowired
    private IOrderItemService orderItemService;

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IProductDetailService productDetailService;

    @Autowired
    private IProductService productService;
    @GetMapping()
    public ResponseEntity<?> getAllOrders(Pageable pageable, @RequestParam String search){
        Page<Order> entitiesPage = service.getAllOrders(pageable, search);
        Page<OrderDTO> dtoPage = entitiesPage.map(new Function<Order, OrderDTO>() {
            @Override
            public OrderDTO apply(Order order) {
                if(order.getOderStatus() == Order.OderStatus.ADDED_TO_CARD){
                    OrderDTO dto = new OrderDTO(order.getId(),order.getOder_date(),order.getOderStatus(), order.getCustomer().getId());
                    return dto;
                }else if(order.getOderStatus() == Order.OderStatus.TO_PAY){
                    OrderDTO dto = new OrderDTO(order.getId(),order.getTotal_amount(), order.getOder_date(),order.getOderStatus(), order.getCustomer().getId(), order.getPayment_method().getId());
                    return dto;
                }else{
                    OrderDTO dto = new OrderDTO(order.getId(),order.getTotal_amount(), order.getOder_date(),order.getOderStatus(), order.getCustomer().getId(), order.getEmployee().getId(), order.getPayment_method().getId());
                    return dto;
                }

            }

        });
        return new ResponseEntity<>(dtoPage, HttpStatus.OK);
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<?> getOrders(){
           ArrayList<Order> orders = service.getAll();
           ArrayList<OrderDTO> orderDTOS = new ArrayList<>();
           for(Order order : orders){
               if(order.getOderStatus() == Order.OderStatus.ADDED_TO_CARD){
                  continue;
               }else if(order.getOderStatus() == Order.OderStatus.TO_PAY){
                   OrderDTO dto = new OrderDTO(order.getId(),order.getTotal_amount(), order.getOder_date(),order.getOderStatus(), (order.getCustomer().getFirstName()+order.getCustomer().getLastName()),order.getAddress(), order.getPhone(), order.getPayment_method().getDescription_payment());
                   orderDTOS.add(dto);
               }else{
                   OrderDTO dto = new OrderDTO(order.getId(),order.getTotal_amount(), order.getOder_date(),order.getOderStatus(), (order.getCustomer().getFirstName()+order.getCustomer().getLastName()), (order.getEmployee().getFirstName()+order.getEmployee().getLastName()),order.getAddress(), order.getPhone(), order.getPayment_method().getDescription_payment());
                   orderDTOS.add(dto);
               }
           }

        return new ResponseEntity<>(orderDTOS, HttpStatus.OK);
    }


    @PostMapping(value = "/create")
    public ResponseEntity<?> createOrder(@RequestBody OrderCustomerCreatForm orderCustomerCreatForm){
        service.customer_createOder(orderCustomerCreatForm);
        return new ResponseEntity<String>("Create successfully", HttpStatus.CREATED);
    }

//    @PostMapping(value = "/createCart")
//    public ResponseEntity<?> createOrderCart(@RequestBody OrderFormCreating formCreating){
//        service.createCart(formCreating);
//        return new ResponseEntity<String>("Create successfully", HttpStatus.CREATED);
//    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable(name = "id") int id, @RequestBody OrderFormCreating formCreating){
        service.updateOder(id, formCreating);
        return new ResponseEntity<String>("Update successfull!", HttpStatus.OK);
    }
    @PutMapping(value = "/cancel/{id}")
    public ResponseEntity<?> cancelOrder(@PathVariable(name = "id") int id){
        service.cancelOrder(id);
        return new ResponseEntity<String>("Update successfull!", HttpStatus.OK);
    }
    @PutMapping(value = "/changeStatus/{id}")
    public ResponseEntity<?> changeStatus(@PathVariable(name = "id") int id, @RequestBody changeStatusDTO changeStatusDTO){
        service.changeStatus(id, changeStatusDTO);
        return new ResponseEntity<String>("Update successfull!", HttpStatus.OK);
    }

    @GetMapping(value = "/getOrderbyID/{id}")
    public ResponseEntity<?> getOrderbyID(@PathVariable(name = "id") int id){
        Order order = service.getOrderById(id);
        List<OrderItem> orderItems= order.getOrderItems();
        List<OderItemDTO> oderItemDTOS = new ArrayList<>();
        for(OrderItem orderItem : orderItems){
            OderItemDTO dto = new OderItemDTO(orderItem.getId(),orderItem.getSell_price(), orderItem.getSubtotal(), orderItem.getQuantity(),orderItem.getOrder().getId(), orderItem.getProduct_detail_order().getId(), orderItem.getProduct_detail_order().getProduct_detail().getName(), orderItem.getProduct_detail_order().getSize(), orderItem.getProduct_detail_order().getColor(), orderItem.getProduct_detail_order().getImg_url());
            oderItemDTOS.add(dto);
        }
        return new ResponseEntity<>(oderItemDTOS, HttpStatus.OK);
    }
    @GetMapping(value = "/getByID/{id}")
    public ResponseEntity<?> getByID(@PathVariable(name = "id") int id){
        Order order = service.getOrderById(id);
             if(order.getOderStatus() == Order.OderStatus.TO_PAY){
                OrderDTO dto = new OrderDTO(order.getId(),order.getTotal_amount(), order.getOder_date(),order.getOderStatus(), (order.getCustomer().getFirstName()+order.getCustomer().getLastName()),order.getAddress(), order.getPhone(), order.getPayment_method().getDescription_payment());
                 return new ResponseEntity<>(dto, HttpStatus.OK);
            }else{
                OrderDTO dto = new OrderDTO(order.getId(),order.getTotal_amount(), order.getOder_date(),order.getOderStatus(), (order.getCustomer().getFirstName()+order.getCustomer().getLastName()), (order.getEmployee().getFirstName()+order.getEmployee().getLastName()),order.getAddress(), order.getPhone(), order.getPayment_method().getDescription_payment());
                 return new ResponseEntity<>(dto, HttpStatus.OK);
            }


    }
    @GetMapping(value = "/getCartByCustomer/{id}")
    public ResponseEntity<?> getCartByCustomerId(@PathVariable(name = "id") int id){
        Customer customer = customerService.getCustomerById(id);
        List<Order> orders = service.getOrderByCustomer(id);
        int cartId = 0;
        for (Order order: orders){
            if(order.getOderStatus() == Order.OderStatus.ADDED_TO_CARD){
                cartId = order.getId();
            }
        }
        List<OrderItem> orderItems = orderItemService.getOrderItemByOrder(cartId);
        ArrayList<CartDTO> cartDTOS = new ArrayList<>();
        for (OrderItem orderItem : orderItems){
            ProductDetail productDetail = orderItem.getProduct_detail_order();
            Product product = productService.getProductById(productDetail.getProduct_detail().getId());
            CartDTO cartDTO = new CartDTO(orderItem.getId(), productDetail.getImg_url(), orderItem.getQuantity(), orderItem.getSubtotal(), product.getPrice(),productDetail.getSize(), product.getName(), product.getTypeProduct().getName(), productDetail.getColor());
            cartDTOS.add(cartDTO);
        }

        return new ResponseEntity<>(cartDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/status/{id}")
    public ResponseEntity<?> getOrderByCustomerId(@PathVariable(name = "id") int id){
        Customer customer = customerService.getCustomerById(id);
        List<Order> orders = service.getOrderByCustomer(id);
        ArrayList<CustomerOrderDTO> customerOrderDTOS = new ArrayList<>();
        for(Order order : orders){
            if(order.getOderStatus() != Order.OderStatus.ADDED_TO_CARD) {
                CustomerOrderDTO customerOrderDTO = new CustomerOrderDTO();
                customerOrderDTO.setIdOrder(order.getId());
                customerOrderDTO.setOderStatus(order.getOderStatus());
                customerOrderDTO.setOrderDate(order.getOder_date());
                customerOrderDTO.setPaymentName(order.getPayment_method().getName());
                int totalQuantity = order.getOrderItems().size();
                customerOrderDTO.setTotalQuantity(totalQuantity);
                customerOrderDTO.setTotalAmount(order.getTotal_amount());
                customerOrderDTO.setImg_url(order.getOrderItems().get(0).getProduct_detail_order().getImg_url());
                customerOrderDTO.setColor(order.getOrderItems().get(0).getProduct_detail_order().getColor());
                customerOrderDTO.setSubQuantity(order.getOrderItems().get(0).getQuantity());
                customerOrderDTO.setProductName(order.getOrderItems().get(0).getProduct_detail_order().getProduct_detail().getName());
                customerOrderDTOS.add(customerOrderDTO);
            }
        }
        return new ResponseEntity<>(customerOrderDTOS, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable(name = "id") int id){
        service.deleteOrder(id);
        return new ResponseEntity<String>("Delete successfull!", HttpStatus.OK);
    }


    @DeleteMapping()
    public void deleteOrders(@RequestParam(name="ids") List<Integer> ids){
        service.deleteOrders(ids);
    }



}

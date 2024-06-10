package com.vti.finalexam.service;

import com.vti.finalexam.entity.*;
import com.vti.finalexam.form.OrderFormCreating;
import com.vti.finalexam.form.OrderItemFormCreating;
import com.vti.finalexam.form.updating.OrderItemFormUpdating;
import com.vti.finalexam.repository.*;
import com.vti.finalexam.specification.OderItemSpecification;
import com.vti.finalexam.specification.OderSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderItemService implements IOrderItemService{
    @Autowired
    private IOderItemRepository repository;

    @Autowired
    private IOderRepository oderRepository;

    @Autowired
    private IProductDetailRepository productDetailRepository;

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public Page<OrderItem> getAllOrderItems(Pageable pageable, String search) {
        Specification<OrderItem> where = null;
        if(!StringUtils.isEmpty(search)){
            OderItemSpecification searchSpecification = new OderItemSpecification("name","LIKE", search);
            where = Specification.where(searchSpecification);
        }
        return repository.findAll(Specification.where(where), pageable);
    }

    @Override
    public void createOderItem(OrderItemFormCreating formCreating) {
        Customer customer = customerRepository.getCustomerById(formCreating.getCustomer_id());
        ArrayList<Order> orderArrayList = oderRepository.findByCustomer(customer);
        int order_id;
        order_id = 0;
        for(Order order : orderArrayList){
            if(order.getOderStatus() == Order.OderStatus.ADDED_TO_CARD){
                order_id = order.getId();
            }
        }
        ProductDetail productDetail = productDetailRepository.getDetailById(formCreating.getProduct_detail_id());
        Product product = productDetail.getProduct_detail();
        Order order = oderRepository.getOrderById(order_id);
        List<OrderItem> orderItems = order.getOrderItems();
        boolean check = false;
        for(OrderItem orderItem : orderItems){
            if(orderItem.getProduct_detail_order().getId() == formCreating.getProduct_detail_id()){
                orderItem.setQuantity(orderItem.getQuantity() + formCreating.getQuantity());
                float subtotal = formCreating.getQuantity() * product.getPrice();
                orderItem.setSubtotal(subtotal +orderItem.getSubtotal());
                repository.save(orderItem);
                order.setTotal_amount(order.getTotal_amount() + subtotal);
                check = true;
                break;
            }
        }
        if(!check){
            float subtotal = formCreating.getQuantity() * product.getPrice();
            OrderItem orderItem = new OrderItem(
                    product.getPrice(),
                    subtotal,
                    formCreating.getQuantity(),
                    order,
                    productDetail
            );
            repository.save(orderItem);
            order.setTotal_amount(order.getTotal_amount() + subtotal);
        }



    }

    @Override
    public void updateOderItem(int id, OrderItemFormUpdating formUpdating) {
        OrderItem orderItem = repository.getOrderItemById(id);
        Order order = orderItem.getOrder();
        ProductDetail productDetail = orderItem.getProduct_detail_order();
        if (order.getOderStatus() == Order.OderStatus.TO_PAY){
            productDetail.setQuantity(productDetail.getQuantity() - orderItem.getQuantity());
            productDetail.setQuantity(productDetail.getQuantity()+ formUpdating.getQuantity());
        }
        order.setTotal_amount(order.getTotal_amount()-orderItem.getSubtotal());
        orderItem.setQuantity(formUpdating.getQuantity());
        Product product = productDetail.getProduct_detail();
        float subtotal = formUpdating.getQuantity()*product.getPrice();
        orderItem.setSubtotal(subtotal);
        order.setTotal_amount(order.getTotal_amount()+orderItem.getSubtotal());
        orderItem.setSell_price(product.getPrice());
        repository.save(orderItem);
    }

    @Override
    public void changeCartToOrder(int id_oder, List<Integer> id_oder_items) {
        Order order = oderRepository.getOrderById(id_oder);
        float total_amount = 0;
        for (Integer id_oder_item : id_oder_items){
            OrderItem orderItem = repository.getOrderItemById(id_oder_item);
            total_amount = total_amount + orderItem.getSubtotal();
            orderItem.setOrder(order);
        }
        order.setTotal_amount(total_amount);
    }

    @Override
    public OrderItem getOrderItemById(int id) {
        return repository.getOrderItemById(id);
    }

    @Override
    public List<OrderItem> getOrderItemByOrder(int id) {
        Order order = oderRepository.getOrderById(id);
        return repository.getOrderItemByOrder(order);
    }

    @Override
    public void deleteOrderItem(int id) {
     repository.deleteById(id);
    }

    @Override
    public void deleteOrderItems(List<Integer> ids) {
        repository.deleteByIds(ids);
    }


}

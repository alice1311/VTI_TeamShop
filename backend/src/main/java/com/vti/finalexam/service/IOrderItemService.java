package com.vti.finalexam.service;

import com.vti.finalexam.entity.Order;
import com.vti.finalexam.entity.OrderItem;
import com.vti.finalexam.form.OrderFormCreating;
import com.vti.finalexam.form.OrderItemFormCreating;
import com.vti.finalexam.form.updating.OrderItemFormUpdating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IOrderItemService {
    public Page<OrderItem> getAllOrderItems(Pageable pageable, String search);
    public void createOderItem(OrderItemFormCreating formCreating);
    public  void updateOderItem(int id, OrderItemFormUpdating formUpdating);
    public  void changeCartToOrder(int id_oder, List<Integer> id_oder_items);
    public OrderItem getOrderItemById(int id);
    public List<OrderItem> getOrderItemByOrder(int id);
    public  void deleteOrderItem(int id);

    void deleteOrderItems(List<Integer> ids);
}

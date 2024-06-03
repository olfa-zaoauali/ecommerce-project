package com.olfaproject.springbootecommerce.dto;

import java.util.Set;

import com.olfaproject.springbootecommerce.entity.Address;
import com.olfaproject.springbootecommerce.entity.Customer;
import com.olfaproject.springbootecommerce.entity.Order;
import com.olfaproject.springbootecommerce.entity.OrderItem;

import lombok.Data;

@Data
public class Purchase {

    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems; 
    

}

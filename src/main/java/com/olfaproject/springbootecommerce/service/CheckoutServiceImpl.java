package com.olfaproject.springbootecommerce.service;

import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olfaproject.springbootecommerce.dao.CustomerRepository;
import com.olfaproject.springbootecommerce.dto.Purchase;
import com.olfaproject.springbootecommerce.dto.PurchaseResponse;
import com.olfaproject.springbootecommerce.entity.Customer;
import com.olfaproject.springbootecommerce.entity.Order;
import com.olfaproject.springbootecommerce.entity.OrderItem;
@Service
public class CheckoutServiceImpl implements CheckoutService {

    private CustomerRepository customerRepository; 

    @Autowired
    private CheckoutServiceImpl(CustomerRepository customerRepository){
        this.customerRepository= customerRepository; 
    }

    @Override
    public PurchaseResponse placeOrder(Purchase purchase) {
        //retrieve the order info from dto 
        Order order = purchase.getOrder();

        //generate tracking number
        String orderTrackingNumber= generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber); 

        //populate order with orderItems
        Set<OrderItem> orderItems= purchase.getOrderItems(); 
        orderItems.forEach(item -> order.add(item));

        //populate order with billingAddress and shippingAddress
        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());

        //populate customer with order 
        Customer customer= purchase.getCustomer(); 
        customer.add(order);

        //save to the database 
        customerRepository.save(customer); 
        
        //return a response 
        return new PurchaseResponse(orderTrackingNumber); 
    }

    private String generateOrderTrackingNumber() {
        // generate a random UUID number( UUD version-6) 
        return UUID.randomUUID().toString();
        
    }

    

}

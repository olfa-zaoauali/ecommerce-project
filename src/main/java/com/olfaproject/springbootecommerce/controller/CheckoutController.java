package com.olfaproject.springbootecommerce.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olfaproject.springbootecommerce.dao.CustomerRepository;
import com.olfaproject.springbootecommerce.dto.Purchase;
import com.olfaproject.springbootecommerce.dto.PurchaseResponse;
import com.olfaproject.springbootecommerce.entity.Customer;
import com.olfaproject.springbootecommerce.service.CheckoutService;
import java.util.List;


@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

    private CheckoutService checkoutService; 


    public CheckoutController(CheckoutService checkoutService){
        this.checkoutService= checkoutService; 
    }
   

    @PostMapping("/purchase")
    public PurchaseResponse placeOrder(@RequestBody Purchase purchase){
        PurchaseResponse purchaseResponse= checkoutService.placeOrder(purchase);
        return purchaseResponse; 
    }
   
}

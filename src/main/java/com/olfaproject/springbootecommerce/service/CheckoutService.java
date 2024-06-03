package com.olfaproject.springbootecommerce.service;

import com.olfaproject.springbootecommerce.dto.Purchase;
import com.olfaproject.springbootecommerce.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);

}

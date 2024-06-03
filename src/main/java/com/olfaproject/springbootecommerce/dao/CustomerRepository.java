package com.olfaproject.springbootecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olfaproject.springbootecommerce.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

}

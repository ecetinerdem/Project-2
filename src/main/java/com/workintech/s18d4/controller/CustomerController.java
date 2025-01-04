package com.workintech.s18d4.controller;

import com.workintech.s18d4.dto.CustomerResponse;
import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.service.CustomerService;
import com.workintech.s18d4.util.Converter;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public List<CustomerResponse> findAll() {
        return Converter.convertCustomerResponse(customerService.findAll());
    }

    @GetMapping("/{id}")
    public CustomerResponse findById(@PathVariable("id") Long id) {
        return Converter.convertCustomerResponse(customerService.findById(id));
    }

    @PostMapping
    public CustomerResponse save(@Validated @RequestBody Customer customer) {
        return Converter.convertCustomerResponse(customerService.save(customer));
    }

    @DeleteMapping("/{id}")
    public CustomerResponse delete(@PathVariable("id") Long id) {
        return Converter.convertCustomerResponse(customerService.delete(id));
    }
}

package com.workintech.s18d4.controller;

import com.workintech.s18d4.dto.AddressResponse;
import com.workintech.s18d4.entity.Address;
import com.workintech.s18d4.service.AddressService;
import com.workintech.s18d4.util.Converter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")

public class AddressController {
    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public List<AddressResponse> findAll() {
        List<Address> addresses = addressService.findAll();
        return  Converter.convertAddressResponse(addresses);
    }

    @GetMapping("/{id}")
    public AddressResponse findById(@PathVariable("id") Long id) {
        return Converter.convertAddressResponse(addressService.findById(id));
    }

    @PostMapping
    public AddressResponse save(@Validated @RequestBody Address address) {
        return Converter.convertAddressResponse(addressService.save(address));
    }

    @PutMapping("/{id}")
    public AddressResponse update(@Validated @PathVariable("id") Long id, @RequestBody Address address) {
        addressService.findById(id);
        return  Converter.convertAddressResponse(addressService.save(address));
    }

    @DeleteMapping("/{id}")
    public AddressResponse delete(@PathVariable("id") Long id) {
        return Converter.convertAddressResponse(addressService.delete(id));
    }

}

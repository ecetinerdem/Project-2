package com.workintech.s18d4.util;

import com.workintech.s18d4.dto.AddressResponse;
import com.workintech.s18d4.entity.Address;

import java.util.ArrayList;
import java.util.List;

public class Converter {

    public static List<AddressResponse>convertAddressResponse(List<Address> addresses) {
        List<AddressResponse> addressResponses = new ArrayList<>();
        for(Address address: addresses) {
            addressResponses.add(new AddressResponse(address.getId(),address.getCity(),address.getCountry()));
        }
        return addressResponses;
    }

    public static AddressResponse convertAddressResponse(Address adress) {
        return  new AddressResponse(adress.getId(), adress.getCity(), adress.getCountry());
    }
}

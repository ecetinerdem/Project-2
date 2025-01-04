package com.workintech.s18d4.util;

import com.workintech.s18d4.dto.AccountResponse;
import com.workintech.s18d4.dto.AddressResponse;
import com.workintech.s18d4.dto.CustomerResponse;
import com.workintech.s18d4.entity.Account;
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

    public static List<AccountResponse> convertAccountResponse(List<Account> accounts) {
        List<AccountResponse> accountResponses = new ArrayList<>();
        for(Account account: accounts) {
            accountResponses.add(new AccountResponse(account.getId(),
                    account.getAccountName(),
                    account.getMoneyAmount(),
                    new CustomerResponse(account.getCustomer().getId(),account.getCustomer().getEmail(),account.getCustomer().getSalary()),
                    new AddressResponse(account.getCustomer().getAddress().getId(), account.getCustomer().getAddress().getCity(), account.getCustomer().getAddress().getCountry())));
        }
        return accountResponses;
    }

    public static AccountResponse convertAccountResponse(Account account) {
        return new AccountResponse(account.getId(),
                account.getAccountName(),
                account.getMoneyAmount(),
                new CustomerResponse(account.getCustomer().getId(),account.getCustomer().getEmail(),account.getCustomer().getSalary()),
                new AddressResponse(account.getCustomer().getAddress().getId(), account.getCustomer().getAddress().getCity(), account.getCustomer().getAddress().getCountry()));
    }
}

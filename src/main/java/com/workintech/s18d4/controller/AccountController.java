package com.workintech.s18d4.controller;

import com.workintech.s18d4.dto.AccountResponse;
import com.workintech.s18d4.dto.AddressResponse;
import com.workintech.s18d4.dto.CustomerResponse;
import com.workintech.s18d4.entity.Account;
import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.exceptions.AccountException;
import com.workintech.s18d4.service.AccountService;
import com.workintech.s18d4.service.CustomerService;
import com.workintech.s18d4.util.Converter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@AllArgsConstructor

public class AccountController {

    private final AccountService accountService;
    private final CustomerService customerService;
    @GetMapping
    public List<AccountResponse> findAll() {
        return Converter.convertAccountResponse(accountService.findAll());
    }

    @GetMapping("/{id}")
    public AccountResponse findById(@PathVariable("id") Long id) {
        return Converter.convertAccountResponse(accountService.findById(id));
    }

    @PostMapping("/{customerId}")
    public AccountResponse save(@PathVariable("customerId") Long customerId, @RequestBody Account account) {
        Customer customer = customerService.findById(customerId);
        if(customer != null) {
            customer.getAccounts().add(account);
            account.setCustomer(customer);
            accountService.save(account);
        } else {
            throw new RuntimeException("Customer cannot be added to account");
        }
        return Converter.convertAccountResponse(account);
    }

    @PutMapping("/{customerId}")
    public AccountResponse update (@PathVariable("customerId") Long customerId, @RequestBody Account account ) {
        Customer customer = customerService.findById(customerId);
        Account toBeUpdatedAccount = null;
        for(Account account1: customer.getAccounts()) {
            if(account.getId() == account1.getId()) {
                toBeUpdatedAccount = account1;
            }
        }
        if(toBeUpdatedAccount == null) {
            throw new AccountException("Account with given id does not exist " + customerId, HttpStatus.NOT_FOUND);
        }
        int indexOfToBeUpdated = customer.getAccounts().indexOf(toBeUpdatedAccount);
        customer.getAccounts().set(indexOfToBeUpdated, account);
        account.setCustomer(customer);

        return Converter.convertAccountResponse(accountService.save(account));

    }

    @DeleteMapping("/{id}")
    public AccountResponse delete(@PathVariable("id") Long id) {
        Account account = accountService.delete(id);
        if(account == null) {
            throw new AccountException("Account with given id does not exist " + id, HttpStatus.NOT_FOUND);
        }
        return Converter.convertAccountResponse(accountService.delete(id));
    }


}

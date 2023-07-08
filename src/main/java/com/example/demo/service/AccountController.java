package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Response;
import com.example.demo.dto.ValidateAccount;
import com.example.demo.model.Account;

@RestController
public class AccountController {

    private static List<Account> accounts = new ArrayList<Account>() {
        {
            add(new Account("123", "password", 123));
            add(new Account("456", "password1", 456));
            add(new Account("789", "password2", 789));
        }
    };

    @PostMapping(value = "/validate")
    public static Response<String> validateAccountPassword(@RequestBody ValidateAccount accountDto) {
        var account = accounts.stream()
                .filter(c -> c.getAccountNumber().equals(accountDto.accountNumber()))
                .findFirst().orElse(null);
        var response = new Response<String>();
        if (account == null) {
            response.errorMessage = "La cuenta " + accountDto.accountNumber() + " no existe";
            return response;
        }
        if (!account.getPassword().equals(accountDto.password())) {
            response.errorMessage = "la contraseña de la cuenta " + accountDto.accountNumber() + " es incorrecta";
            return response;
        }
        response.data = "la contraseña de la cuenta " + accountDto.accountNumber() + " es correcta";
        return response;
    }

    public static Account getAccountByNumber(String accountNumber) {
        var account = accounts.stream()
                .filter(c -> c.getAccountNumber().equals(accountNumber)).findFirst().orElse(null);
        if (account == null) {
            throw new RuntimeException("La cuenta " + accountNumber + " no existe");
        }
        return account;
    }
}

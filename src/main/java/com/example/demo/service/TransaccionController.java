package com.example.demo.service;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Response;
import com.example.demo.dto.ValidateAccount;
import com.example.demo.model.Account;
import com.example.demo.model.Transaccion;

@RestController
public class TransaccionController {

    @PostMapping(value = "/transaction")
    public Response<String> realizarTransaccion(@RequestBody Transaccion transaccion) {
        var response = new Response<String>();
        Account sourceAccount = AccountController.getAccountByNumber(transaccion.sourceAccount());
        Account destinationAccount = AccountController.getAccountByNumber(transaccion.destinationAccount());

        var verificacionPassword = AccountController
                .validateAccountPassword(new ValidateAccount(transaccion.sourceAccount(), transaccion.password()));

        if (verificacionPassword.errorMessage != null) {
            response.errorMessage = "La cuenta no pudo ser validad correctamente";
        }
        var transaccionValida = this.validateBalance(sourceAccount, transaccion.amount());
        if (!transaccionValida) {
            response.errorMessage = "La cuenta " + sourceAccount.getAccountNumber() + " no tiene saldo suficiente";
        }
        sourceAccount.setBalance(sourceAccount.getBalance() - transaccion.amount());
        destinationAccount.setBalance(destinationAccount.getBalance() + transaccion.amount());
        response.data = "Transaccion realizada";
        return response;
    }

    private boolean validateBalance(Account cuenta, int amount) {
        if (cuenta.getBalance() < amount) {
            return false;
        }
        return true;
    }
}

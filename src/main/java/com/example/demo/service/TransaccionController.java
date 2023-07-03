package com.example.demo.service;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Cuenta;
import com.example.demo.model.Transaccion;

@RestController
public class TransaccionController {

    @RequestMapping(value = "/transaccion", method = RequestMethod.POST)
    public String realizarTransaccion(@RequestBody Transaccion transaccion) {

        Cuenta cuentaOrigen = CuentaController.getCuentaByNumero(transaccion.getCuentaOrigen());
        Cuenta cuentaDestino = CuentaController.getCuentaByNumero(transaccion.getCuentaDestino());

        String verificacionPassword = CuentaController
                .validarPassword(new Cuenta(transaccion.getCuentaOrigen(), transaccion.getPassword(), 0));
        if (!verificacionPassword.contains("es correcta")) {
            return "La contraseña de la cuenta " + cuentaOrigen.getNumeroCuenta() + " es incorrecta";
        }
        var transaccionValida = this.validarSaldo(cuentaOrigen, transaccion.getMonto());
        if (!transaccionValida) {
            return "La cuenta " + cuentaOrigen.getNumeroCuenta() + " no tiene saldo suficiente";
        }
        cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - transaccion.getMonto());
        cuentaDestino.setSaldo(cuentaDestino.getSaldo() + transaccion.getMonto());
        return "Transaccion realizada";
    }

    private boolean validarSaldo(Cuenta cuenta, int monto) {
        if (cuenta.getSaldo() < monto) {
            return false;
        }
        return true;
    }
}

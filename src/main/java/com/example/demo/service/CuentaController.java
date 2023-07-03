package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Cuenta;

@RestController
public class CuentaController {

    private static List<Cuenta> cuentas = new ArrayList<Cuenta>() {
        {
            add(new Cuenta("123", "contraseña", 123));
            add(new Cuenta("456", "otraContraseña", 123));
            add(new Cuenta("789", "anotherPassword", 213));
        }
    };

    @RequestMapping(value = "/validar", method = RequestMethod.POST)
    public static String validarPassword(@RequestBody Cuenta cuentaParaValidar) {
        var cuentaAsociada = cuentas.stream()
                .filter(c -> c.getNumeroCuenta().equals(cuentaParaValidar.getNumeroCuenta())).findFirst().orElse(null);
        if (cuentaAsociada == null) {
            return "La cuenta no existe";
        }
        if (!cuentaAsociada.getContrasenha().equals(cuentaParaValidar.getContrasenha())) {
            return "La contraseña de la cuenta " + cuentaAsociada.getNumeroCuenta() + " es incorrecta";
        }
        return "La contraseña de la cuenta " + cuentaAsociada.getNumeroCuenta() + " es correcta";
    }

    public static Cuenta getCuentaByNumero(String numeroCuenta) {
        return cuentas.stream().filter(c -> c.getNumeroCuenta().equals(numeroCuenta)).findFirst().orElse(null);
    }
}

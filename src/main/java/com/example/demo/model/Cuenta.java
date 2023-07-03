package com.example.demo.model;

public class Cuenta {
    private String numeroCuenta;
    private String contrasenha;
    private int saldo;

    public Cuenta(String numeroCuenta, String contrasenha, int saldo) {
        this.numeroCuenta = numeroCuenta;
        this.contrasenha = contrasenha;
        this.saldo = saldo;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getContrasenha() {
        return contrasenha;
    }

    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }
    
}

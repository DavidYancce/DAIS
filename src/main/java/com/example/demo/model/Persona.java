package com.example.demo.model;

import java.io.Serializable;

public class Persona implements Serializable {
    private String name;
    private String username;
    private String dni;
    
    public Persona(String nombre, String username, String dni) {
        this.name = nombre;
        this.username = username;
        this.dni = dni;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getDni() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }
    
}

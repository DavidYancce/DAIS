package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Persona;

@RestController
public class PersonServiceRest {

   private static List<Persona> listaPersonas = new ArrayList<Persona>() {
        {
            add(new Persona("Rosa", "Rosa123", "12345678"));
            add(new Persona("Pepito", "Pepito123", "1231412"));
            add(new Persona("Manuela", "Manuela123", "352454"));
        }
    };
     
    @RequestMapping(value="/personas", method=RequestMethod.GET)
    public List<Persona>  getPersonas(){
        return listaPersonas;
    }

}

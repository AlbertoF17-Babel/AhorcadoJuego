package es.babel.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SelectorPalabraService implements ISelectorPalabraService{
    @Override
    public String seleccionarPalabra() {
        String[] listadoPalabras = {"MANZANA", "COCODRILO", "MAESTRO", "ARDILLA", "TAMBOR", "ELEFANTE",
            "JIRAFA", "GUITARRA", "VENTANA", "ESTRELLA"};
        Random rd = new Random();
        int numPalabraSeleccionada = rd.nextInt(10);
        return listadoPalabras[numPalabraSeleccionada];
    }
}

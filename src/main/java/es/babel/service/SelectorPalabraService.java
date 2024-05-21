package es.babel.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SelectorPalabraService implements ISelectorPalabraService{
    @Override
    public String seleccionarPalabra() {
        String[] listadoPalabras = {"MANZANA", "COCODRILO", "MAESTRO", "ARDILLA", "TAMBOR", "ELEFANTE",
                "JIRAFA", "GUITARRA", "VENTANA", "ESTRELLA", "PITO", "PERRO", "LUNA", "LIBRO", "COCHE", "SOL", "FLOR",
                "RÍO", "MONTAÑA", "MESA", "SILLA", "CASA", "JARDÍN", "TELÉFONO", "COMPUTADORA", "BICICLETA", "PAPEL",
                "PLUMA", "CAMISA", "ZAPATO", "CARPETA", "RELOJ", "VENTANA", "PUERTA", "SOMBRA", "ARCOÍRIS", "NUBE",
                "MAR", "ÁRBOL", "CIELO", "ESTRELLA"};
        String[] listadoPalabras2 = {"ÁRBOL"};
        Random rd = new Random();
        int numPalabraSeleccionada = rd.nextInt(listadoPalabras2.length);
        return listadoPalabras2[numPalabraSeleccionada];
    }
}

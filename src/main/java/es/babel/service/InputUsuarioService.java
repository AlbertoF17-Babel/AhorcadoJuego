package es.babel.service;

import org.springframework.stereotype.Service;

import javax.security.sasl.SaslClient;
import java.util.Scanner;

@Service
public class InputUsuarioService implements IInputUsuarioService{
    Scanner sc = new Scanner(System.in);
    @Override
    public int obtenerInputMenuPrincipal() {
        System.out.println("Bienvenido al ahorcado");
        System.out.println("1.- Juego nuevo");
        System.out.println("2.- Salir");
        System.out.print("Seleccione una opción: ");
        return sc.nextInt();
    }

    @Override
    public String obtenerInputLetraOPalabra() {
        System.out.print("Introduzca una sola letra o la palabra que creas que sea: ");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}

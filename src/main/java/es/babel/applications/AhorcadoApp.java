package es.babel.applications;

import es.babel.service.IInputUsuarioService;
import es.babel.service.ILetrasUsadasService;
import es.babel.service.ISelectorPalabraService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AhorcadoApp {
    ISelectorPalabraService selectorPalabraService;
    ILetrasUsadasService letrasUsadasService;
    IInputUsuarioService inputUsuarioService;

    public AhorcadoApp(ISelectorPalabraService selectorPalabraService, ILetrasUsadasService letrasUsadasService,
                       IInputUsuarioService inputUsuarioService){
        this.selectorPalabraService = selectorPalabraService;
        this.letrasUsadasService = letrasUsadasService;
        this.inputUsuarioService = inputUsuarioService;
    }

    public void run() {
        int opcion;
        do {
            opcion = inputUsuarioService.obtenerInputMenuPrincipal();
        } while (opcion != 1 && opcion != 2);

        if (opcion == 1) {
            int intentos = 8;
            boolean ganada = true;
            String palabra = selectorPalabraService.seleccionarPalabra();

            while (intentos >= 1 && ganada) {
                System.out.println("--------------------------------------------");
                System.out.println("Adivina la palabra...");
                for (int i = 0; i < palabra.length(); i++) {
                    Character letra = palabra.charAt(i);
                    if (letrasUsadasService.devolverLetrasAcertadas().contains(letra)) {
                        System.out.print(letra + " ");
                    } else {
                        System.out.print("_ ");
                        ganada = false;
                    }
                }

                System.out.println("\n\n" + intentos + " intentos restantes.");
                letrasUsadasService.verLetrasFallidas();
                System.out.println("--------------------------------------------");

                String letra;
                boolean acierto = false;

                if (!ganada) {
                    letra = inputUsuarioService.obtenerInputLetraOPalabra();
                    if (letra.length() == 1){
                        for (int i = 0; i < palabra.length(); i++) {
                            if (Character.toUpperCase(palabra.charAt(i)) == Character.toUpperCase(letra.charAt(0))) {
                                acierto = true;
                                break;
                            }
                        }

                        if (!acierto) {
                            if (!letrasUsadasService.devolverLetrasFallidas().contains(letra.toUpperCase().charAt(0))){
                                letrasUsadasService.agregarLetraFallida(letra.toUpperCase().charAt(0));
                                intentos--;
                            }
                        } else {
                                letrasUsadasService.agregarLetraAcertada(letra.toUpperCase().charAt(0));
                        }

                    } else {
                        if (letra.equalsIgnoreCase(palabra)){
                            System.out.println("HAS GANADO!!! La palabra era " + palabra);
                            break;
                        } else {
                            System.out.println("Esa no era la palabra correcta...");
                            intentos--;
                        }
                    }
                } else {
                    System.out.println("HAS GANADO!!! La palabra era " + palabra);
                    break;
                }
                ganada = true;
            }
        } else {
            System.out.println("Saliendo del juego...");
        }
    }
}

package es.babel.applications;

import es.babel.service.IInputUsuarioService;
import es.babel.service.ILetrasUsadasService;
import es.babel.service.ISelectorPalabraService;
import org.springframework.stereotype.Component;

import java.text.Normalizer;
import java.util.Scanner;
import java.util.regex.Pattern;

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
        int intentos = 8;
        String palabra = selectorPalabraService.seleccionarPalabra();
        String palabraSinAcentos = removerAcentos(palabra);

        do {
            opcion = inputUsuarioService.obtenerInputMenuPrincipal();
        } while (opcion != 1 && opcion != 2);

        if (opcion == 1) {
            boolean ganada = true;
            while (intentos >= 1 && ganada) {
                System.out.println("--------------------------------------------");
                System.out.println("Adivina la palabra...");
                for (int i = 0; i < palabra.length(); i++) {
                    Character letra = palabra.charAt(i);
                    Character letraSinAcento = palabraSinAcentos.charAt(i);
                    if (letrasUsadasService.devolverLetrasAcertadas().contains(letra) ||
                            letrasUsadasService.devolverLetrasAcertadas().contains(letraSinAcento)) {
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
                            if (Character.toUpperCase(palabraSinAcentos.charAt(i)) == Character.toUpperCase(letra.charAt(0))) {
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
                        if (letra.equalsIgnoreCase(palabraSinAcentos)){
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
        if (intentos == 0){
            System.out.println("TE QUEDASTE SIN INTENTOS!!! La palabra era " + palabra);
        }
    }

    public String removerAcentos(String str) {
        String normalized = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalized).replaceAll("");
    }
}

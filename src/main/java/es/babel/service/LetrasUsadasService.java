package es.babel.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LetrasUsadasService implements ILetrasUsadasService{

    List<Character> letrasAcertadas = new ArrayList<>();
    List<Character> letrasFallidas = new ArrayList<>();

    @Override
    public void agregarLetraAcertada(Character letra) {
        letrasAcertadas.add(letra);
    }

    @Override
    public void agregarLetraFallida(Character letra) {
        letrasFallidas.add(letra);
    }

    @Override
    public void verLetrasFallidas() {
        System.out.println(letrasFallidas);
    }

    @Override
    public List<Character> devolverLetrasAcertadas() {
        return letrasAcertadas;
    }

    @Override
    public List<Character> devolverLetrasFallidas() {
        return letrasFallidas;
    }
}

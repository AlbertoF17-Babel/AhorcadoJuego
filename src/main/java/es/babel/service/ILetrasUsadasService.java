package es.babel.service;

import java.util.List;

public interface ILetrasUsadasService {
    public void agregarLetraAcertada(Character letra);
    public void agregarLetraFallida(Character letra);
    public void verLetrasFallidas();
    public List<Character> devolverLetrasAcertadas();
    public List<Character> devolverLetrasFallidas();
}

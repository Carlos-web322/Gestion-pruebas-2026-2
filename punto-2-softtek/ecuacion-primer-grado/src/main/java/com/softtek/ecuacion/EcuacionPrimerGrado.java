package com.softtek.ecuacion;

/**
 * Resuelve una ecuación de primer grado del tipo: ax + b = c
 * Solución: x = (c - b) / a
 *
 * En términos del código:
 *   parte1 = a
 *   parte2 = b (con signo según el operador)
 *   parte3 = c
 */
public class EcuacionPrimerGrado {

    private Parseador parseador = new Parseador();

    public double obtenerResultado(final String ecuacion) {
        int parte1 = parseador.obtenerParte1(ecuacion);
        int parte2 = parseador.obtenerParte2(ecuacion);
        int parte3 = parseador.obtenerParte3(ecuacion);
        double resultado = Double.valueOf((parte3 - parte2)) / Double.valueOf(parte1);
        return resultado;
    }
}
package com.softtek.ecuacion;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Pruebas unitarias PURAS del Parseador.
 * SUT (System Under Test): Parseador.
 * No tiene dependencias externas → no requiere mocks.
 */
@DisplayName("Pruebas unitarias del Parseador")
class ParseadorTest {

    private final Parseador parseador = new Parseador();

    @Test
    @DisplayName("obtenerParte1 devuelve el coeficiente de x")
    void obtenerParte1Unidades() {
        // Arrange
        String ecuacion = "2x - 1 = 0";

        // Act
        int resultado = parseador.obtenerParte1(ecuacion);

        // Assert
        assertEquals(2, resultado);
    }

    @Test
    @DisplayName("obtenerParte2 devuelve el término independiente cuando hay suma")
    void obtenerParte2Suma() {
        String ecuacion = "2x + 1 = 0";

        int resultado = parseador.obtenerParte2(ecuacion);

        assertEquals(1, resultado);
    }

    @Test
    @DisplayName("obtenerParte2 devuelve valor negativo cuando hay resta")
    void obtenerParte2Resta() {
        String ecuacion = "2x - 1 = 0";

        int resultado = parseador.obtenerParte2(ecuacion);

        assertEquals(-1, resultado);
    }

    @Test
    @DisplayName("obtenerParte3 devuelve el lado derecho de la ecuación")
    void obtenerParte3Positivo() {
        String ecuacion = "2x + 1 = 3";

        int resultado = parseador.obtenerParte3(ecuacion);

        assertEquals(3, resultado);
    }

    @Test
    @DisplayName("obtenerOperador reconoce el signo +")
    void obtenerOperadorSuma() {
        String ecuacion = "2x + 1 = 0";

        String operador = parseador.obtenerOperador(ecuacion);

        assertEquals("+", operador);
    }

    @Test
    @DisplayName("obtenerOperador reconoce el signo -")
    void obtenerOperadorResta() {
        String ecuacion = "2x - 1 = 0";

        String operador = parseador.obtenerOperador(ecuacion);

        assertEquals("-", operador);
    }
}
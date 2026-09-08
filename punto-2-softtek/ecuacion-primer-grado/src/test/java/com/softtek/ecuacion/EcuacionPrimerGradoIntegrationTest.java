package com.softtek.ecuacion;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Pruebas de INTEGRACIÓN de EcuacionPrimerGrado.
 * Usa el Parseador REAL como dependencia (no mockeado).
 * Verifica el comportamiento end-to-end del cálculo.
 *
 * Fórmula: ax + b = c  →  x = (c - b) / a
 */
@DisplayName("Pruebas de integración de EcuacionPrimerGrado")
class EcuacionPrimerGradoIntegrationTest {

    private final EcuacionPrimerGrado ecuacion = new EcuacionPrimerGrado();

    @Test
    @DisplayName("Resuelve 2x - 1 = 0  →  x = 0.5")
    void solucionaEcuacionConMenos() {
        // Arrange
        String entrada = "2x - 1 = 0";
        Double esperado = 0.5;

        // Act
        Double resultado = ecuacion.obtenerResultado(entrada);

        // Assert
        assertEquals(esperado, resultado);
    }

    @Test
    @DisplayName("Resuelve 2x + 1 = 0  →  x = -0.5")
    void solucionaEcuacionConMas() {
        String entrada = "2x + 1 = 0";
        Double esperado = -0.5;

        Double resultado = ecuacion.obtenerResultado(entrada);

        assertEquals(esperado, resultado);
    }

    @Test
    @DisplayName("Resuelve 2x + 1 = 10  →  x = 4.5")
    void solucionaEcuacionConParte3Mayor0() {
        String entrada = "2x + 1 = 10";
        Double esperado = 4.5;

        Double resultado = ecuacion.obtenerResultado(entrada);

        assertEquals(esperado, resultado);
    }
}
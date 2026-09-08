package com.softtek.ecuacion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Pruebas UNITARIAS de EcuacionPrimerGrado usando Mockito.
 * Se mockea el Parseador para aislar el SUT y probar SOLO la fórmula: x = (c - b) / a.
 *
 * Ventajas frente al test de integración:
 * - Independiente de bugs en Parseador.
 * - Más rápida (no ejecuta lógica real de parseo).
 * - Permite probar cualquier combinación de valores sin depender de strings válidos.
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas unitarias de EcuacionPrimerGrado con Mockito")
class EcuacionPrimerGradoMockitoTest {

    @InjectMocks
    private EcuacionPrimerGrado ecuacionPrimerGrado;

    @Mock
    private Parseador parseador;

    @Test
    @DisplayName("Resuelve 2x - 1 = 0 (mockeado)  →  x = 0.5")
    void solucionaEcuacionConMenos() {
        // Arrange: configuramos el mock para simular el comportamiento del Parseador
        String ecuacion = "2x - 1 = 0";
        when(parseador.obtenerParte1(ecuacion)).thenReturn(2);
        when(parseador.obtenerParte2(ecuacion)).thenReturn(-1);
        when(parseador.obtenerParte3(ecuacion)).thenReturn(0);

        // Act
        Double resultado = ecuacionPrimerGrado.obtenerResultado(ecuacion);

        // Assert
        Double esperado = 0.5;
        assertEquals(esperado, resultado);
    }

    @Test
    @DisplayName("Resuelve 2x + 1 = 0 (mockeado)  →  x = -0.5")
    void solucionaEcuacionConMas() {
        String ecuacion = "2x + 1 = 0";
        when(parseador.obtenerParte1(ecuacion)).thenReturn(2);
        when(parseador.obtenerParte2(ecuacion)).thenReturn(1);
        when(parseador.obtenerParte3(ecuacion)).thenReturn(0);

        Double resultado = ecuacionPrimerGrado.obtenerResultado(ecuacion);

        Double esperado = -0.5;
        assertEquals(esperado, resultado);
    }

    @Test
    @DisplayName("Resuelve 2x + 1 = 10 (mockeado)  →  x = 4.5")
    void solucionaEcuacionConParte3Mayor0() {
        String ecuacion = "2x + 1 = 10";
        when(parseador.obtenerParte1(ecuacion)).thenReturn(2);
        when(parseador.obtenerParte2(ecuacion)).thenReturn(1);
        when(parseador.obtenerParte3(ecuacion)).thenReturn(10);

        Double resultado = ecuacionPrimerGrado.obtenerResultado(ecuacion);

        Double esperado = 4.5;
        assertEquals(esperado, resultado);
    }
}
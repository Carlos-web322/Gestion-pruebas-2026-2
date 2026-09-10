package com.mateo.tddjunit.writingtests.c06_assumptions

import com.mateo.tddjunit.domain.Calculator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assumptions.assumeTrue
import org.junit.jupiter.api.Assumptions.assumingThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

/**
 * Tema 6/23 — Assumptions
 * https://docs.junit.org/6.1.3/writing-tests/assumptions.html
 *
 * Cuando una "assumption" es inválida, JUnit ABORTA la prueba (no la marca
 * como fallida): lanza org.opentest4j.TestAbortedException internamente.
 * Se usa cuando no tiene sentido seguir ejecutando la prueba en el entorno
 * actual.
 */
class AssumptionsDemoTest {
    private val calculator = Calculator()

    @Test
    @DisplayName("Solo corre en el servidor CI")
    fun testOnlyOnCiServer() {
        assumeTrue(System.getenv("ENV") == "CI")
        // El resto de la prueba solo corre si ENV=CI; si no, se aborta
        // (aparece como "skipped", no como fallo).
    }

    @Test
    @DisplayName("Solo corre en estación de desarrollo")
    fun testOnlyOnDeveloperWorkstation() {
        assumeTrue(System.getenv("ENV") == "DEV") {
            "Prueba abortada: no estamos en el equipo del desarrollador"
        }
    }

    @Test
    @DisplayName("Corre en todos los entornos")
    fun testInAllEnvironments() {
        assumingThat(System.getenv("ENV") == "CI") {
            // Estas aserciones solo se ejecutan en el servidor de CI.
            assertEquals(2, calculator.divide(4, 2))
        }

        // Estas aserciones se ejecutan siempre, en cualquier entorno.
        assertEquals(42, calculator.multiply(6, 7))
    }
}

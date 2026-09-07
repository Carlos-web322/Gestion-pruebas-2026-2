package com.mateo.tddjunit.writingtests.c08_disabling_tests

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

/**
 * Tema 8/23 — Disabling Tests
 * https://docs.junit.org/6.1.3/writing-tests/disabling-tests.html
 *
 * @Disabled a nivel de clase deshabilita todos sus métodos de prueba; a
 * nivel de método, deshabilita solo ese método. Siempre se recomienda dar
 * un motivo. Importante: @Disabled NO se hereda, cada subclase debe
 * redeclararlo si corresponde.
 */
@Disabled("Deshabilitada hasta que se resuelva el bug #99 (ejemplo de la documentación)")
class DisabledClassDemoTest {
    @Test
    @DisplayName("Prueba omitida por clase deshabilitada")
    fun testWillBeSkipped() {
    }
}

class DisabledTestsDemoTest {

    @Disabled("Deshabilitada hasta que se resuelva el bug #42 (ejemplo de la documentación)")
    @Test
    @DisplayName("Deshabilitada por bug conocido")
    fun testWillBeSkipped() {
    }

    @Test
    @DisplayName("Prueba habilitada normalmente")
    fun testWillBeExecuted() {
    }
}

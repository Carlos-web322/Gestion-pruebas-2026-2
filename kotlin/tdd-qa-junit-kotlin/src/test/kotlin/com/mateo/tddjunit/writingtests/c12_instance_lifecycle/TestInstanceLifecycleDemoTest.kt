package com.mateo.tddjunit.writingtests.c12_instance_lifecycle

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle

/**
 * Tema 12/23 — Test Instance Lifecycle
 * https://docs.junit.org/6.1.3/writing-tests/test-instance-lifecycle.html
 *
 * Por defecto JUnit crea una instancia NUEVA de la clase de prueba antes
 * de cada método ("per-method"), para evitar estado compartido accidental.
 * Con @TestInstance(Lifecycle.PER_CLASS) se usa una única instancia
 * compartida, lo que permite declarar @BeforeAll/@AfterAll en métodos NO
 * estáticos — especialmente útil en Kotlin, donde declarar miembros
 * `static` (via companion object + @JvmStatic) es más incómodo que en Java.
 *
 * La página oficial no trae bloques de código; el ejemplo siguiente se
 * construyó a partir de su descripción textual.
 */
@TestInstance(Lifecycle.PER_CLASS)
class PerClassLifecycleDemoTest {

    private var sharedState = 0

    // No hace falta companion object / @JvmStatic: la instancia se
    // comparte entre todos los métodos de esta clase.
    @BeforeAll
    fun initOnce() {
        sharedState = 100
    }

    @Test
    @DisplayName("Incrementa estado compartido")
    fun test1() {
        sharedState++
        assertEquals(101, sharedState)
    }

    @AfterAll
    fun tearDownOnce() {
        // limpieza final compartida
    }
}

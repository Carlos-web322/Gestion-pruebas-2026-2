package com.mateo.tddjunit.writingtests.c11_execution_order

import org.junit.jupiter.api.ClassOrderer
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestClassOrder
import org.junit.jupiter.api.TestMethodOrder

/**
 * Tema 11/23 — Test Execution Order
 * https://docs.junit.org/6.1.3/writing-tests/test-execution-order.html
 *
 * Por defecto, JUnit ordena las pruebas de forma determinista pero
 * intencionalmente no obvia (para builds reproducibles). Para forzar un
 * orden específico (útil en pruebas de integración secuenciales) se usa
 * @TestMethodOrder + @Order, o @TestClassOrder para clases anidadas.
 */
@TestMethodOrder(OrderAnnotation::class)
class OrderedTestsDemoTest {

    @Test
    @Order(1)
    @DisplayName("Se ejecuta primero (orden 1)")
    fun nullValues() {
        // se ejecuta primero
    }

    @Test
    @Order(2)
    @DisplayName("Se ejecuta segundo (orden 2)")
    fun emptyValues() {
        // se ejecuta segundo
    }

    @Test
    @Order(3)
    @DisplayName("Se ejecuta tercero (orden 3)")
    fun validValues() {
        // se ejecuta tercero
    }
}

@TestClassOrder(ClassOrderer.OrderAnnotation::class)
class OrderedNestedTestClassesDemoTest {

    @Nested
    @Order(1)
    @DisplayName("Clase con @Order(1)")
    inner class PrimaryTests {
        @Test
        @DisplayName("Prueba de la clase prioritaria")
        fun test1() {
        }
    }

    @Nested
    @Order(2)
    @DisplayName("Clase con @Order(2)")
    inner class SecondaryTests {
        @Test
        @DisplayName("Prueba de la clase secundaria")
        fun test2() {
        }
    }
}

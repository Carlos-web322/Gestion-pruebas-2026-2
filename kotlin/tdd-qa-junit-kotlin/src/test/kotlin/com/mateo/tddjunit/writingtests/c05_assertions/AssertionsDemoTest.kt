package com.mateo.tddjunit.writingtests.c05_assertions

import com.mateo.tddjunit.domain.Calculator
import com.mateo.tddjunit.domain.FibonacciCalculator
import com.mateo.tddjunit.domain.Person
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertInstanceOf
import org.junit.jupiter.api.assertNotNull
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.assertTimeout
import java.time.Duration

/**
 * Tema 5/23 — Assertions
 * https://docs.junit.org/6.1.3/writing-tests/assertions.html
 *
 * JUnit Jupiter ofrece funciones de aserción idiomáticas para Kotlin como
 * funciones de nivel superior de org.junit.jupiter.api (no solo métodos
 * estáticos de Assertions), lo que permite un estilo con lambdas y
 * "smart cast" del compilador de Kotlin.
 */
class AssertionsDemoTest {
    private val calculator = Calculator()
    private val person = Person("Jane", "Doe")
    private val people = setOf(person, Person("John", "Doe"))

    @Test
    @DisplayName("No lanza excepción al dividir")
    fun `exception absence testing`() {
        val result = assertDoesNotThrow("No debería lanzar excepción") {
            calculator.divide(0, 1)
        }
        assertEquals(0, result)
    }

    @Test
    @DisplayName("Divide por cero lanza excepción")
    fun `expected exception testing`() {
        val exception = assertThrows<ArithmeticException>("Debería lanzar una excepción") {
            calculator.divide(1, 0)
        }
        assertEquals("/ by zero", exception.message)
    }

    @Test
    @DisplayName("Aserciones agrupadas de persona")
    fun `grouped assertions`() {
        assertAll(
            "Propiedades de la persona",
            { assertEquals("Jane", person.firstName) },
            { assertEquals("Doe", person.lastName) }
        )
    }

    @Test
    @DisplayName("Aserciones agrupadas por colección")
    fun `grouped assertions from a collection`() {
        assertAll(
            "Personas con apellido Doe",
            people.map { { assertEquals("Doe", it.lastName) } }
        )
    }

    @Test
    @DisplayName("Fibonacci dentro del tiempo límite")
    fun `timeout not exceeded testing`() {
        val fibonacciCalculator = FibonacciCalculator()
        val result = assertTimeout(Duration.ofMillis(1000)) {
            fibonacciCalculator.fib(14)
        }
        assertEquals(377, result)
    }

    @Test
    @DisplayName("assertNotNull aplica smart cast")
    fun `assertNotNull with a smart cast`() {
        val nullablePerson: Person? = person
        assertNotNull(nullablePerson)
        // El compilador aplica smart cast a nullablePerson (no nulo):
        // no hace falta el operador de llamada segura (?.).
        assertEquals(person.firstName, nullablePerson.firstName)
    }

    @Test
    @DisplayName("assertInstanceOf aplica smart cast")
    fun `assertInstanceOf with a smart cast`() {
        val maybePerson: Any = person
        assertInstanceOf<Person>(maybePerson)
        assertEquals(person.firstName, maybePerson.firstName)
    }
}

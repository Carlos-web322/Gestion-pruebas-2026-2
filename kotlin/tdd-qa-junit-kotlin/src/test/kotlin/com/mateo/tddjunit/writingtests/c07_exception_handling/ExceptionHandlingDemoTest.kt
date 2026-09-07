package com.mateo.tddjunit.writingtests.c07_exception_handling

import com.mateo.tddjunit.domain.Calculator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.assertThrowsExactly

/**
 * Tema 7/23 — Exception Handling
 * https://docs.junit.org/6.1.3/writing-tests/exception-handling.html
 *
 * Si una excepción sin capturar sale de un método de prueba, JUnit marca
 * la prueba como fallida (declarar `throws` no cambia nada: JUnit no lo
 * interpreta como una expectativa). Las aserciones se implementan
 * internamente con excepciones (AssertionError).
 */
class ExceptionHandlingDemoTest {
    private val calculator = Calculator()

    // ------------------------------------------------------------------
    // Estos dos métodos están @Disabled a propósito: son ejemplos de la
    // documentación oficial que FALLAN deliberadamente, para mostrar cómo
    // se ve una prueba en rojo por una excepción no capturada o por una
    // aserción incorrecta. Se dejan aquí como evidencia/documentación,
    // pero deshabilitados para que `./gradlew test` quede en verde.
    // Para ver el fallo en la consola, comenta la línea @Disabled y
    // vuelve a correr las pruebas.
    // ------------------------------------------------------------------

    @Disabled("Ejemplo intencional de fallo: excepción no capturada (ver docs de JUnit)")
    @Test
    @DisplayName("Falla por excepción no capturada")
    fun failsDueToUncaughtException() {
        // División por cero: lanza ArithmeticException sin capturar,
        // lo que hace fallar la prueba.
        calculator.divide(1, 0)
    }

    @Disabled("Ejemplo intencional de fallo: aserción incorrecta (ver docs de JUnit)")
    @Test
    @DisplayName("Falla por aserción incorrecta")
    fun failsDueToUncaughtAssertionError() {
        // El valor esperado debería ser 2, no 99: la prueba falla.
        assertEquals(99, calculator.add(1, 1))
    }

    @Test
    @DisplayName("Verifica excepción esperada")
    fun testExpectedExceptionIsThrown() {
        val exception = assertThrows<IllegalArgumentException> {
            throw IllegalArgumentException("expected message")
        }
        assertEquals("expected message", exception.message)

        // También pasa porque IllegalArgumentException es subclase de RuntimeException.
        assertThrows<RuntimeException> {
            throw IllegalArgumentException("expected message")
        }
    }

    @Test
    @DisplayName("Verifica excepción exacta esperada")
    fun testExpectedExactExceptionIsThrown() {
        val exception = assertThrowsExactly<IllegalArgumentException> {
            throw IllegalArgumentException("expected message")
        }
        assertEquals("expected message", exception.message)
    }

    @Test
    @DisplayName("Verifica que no lance excepción")
    fun testExceptionIsNotThrown() {
        assertDoesNotThrow {
            shouldNotThrowException()
        }
    }

    private fun shouldNotThrowException() {
        // no-op
    }
}

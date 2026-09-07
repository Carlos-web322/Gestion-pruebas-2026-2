package com.mateo.tddjunit.tdd

import com.mateo.tddjunit.domain.Calculator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * ==========================================================================
 *  CICLO TDD (Test-Driven Development) APLICADO A Calculator
 * ==========================================================================
 *
 * Este archivo documenta, paso a paso, cómo un/a QA que practica TDD
 * construyó la clase `Calculator` (ver src/main/kotlin/.../domain/Calculator.kt).
 * El ciclo se repite para cada método: ROJO -> VERDE -> REFACTOR.
 *
 * ROJO    (Red):    se escribe primero una prueba para un comportamiento que
 *                    todavía no existe. La prueba DEBE fallar (o ni siquiera
 *                    compilar), porque el código de producción aún no la
 *                    satisface. Este paso es responsabilidad del QA/desarrollador:
 *                    define, en forma ejecutable, qué significa "correcto".
 *
 * VERDE   (Green):   se escribe el código MÍNIMO necesario en la clase de
 *                    producción para que la prueba pase. No se optimiza ni
 *                    se generaliza de más: solo se busca pasar de rojo a verde.
 *
 * REFACTOR:          con la prueba en verde como red de seguridad, se mejora
 *                    el diseño del código (nombres, duplicación, claridad)
 *                    sin cambiar el comportamiento observable. Si algo se
 *                    rompe, la prueba vuelve a ponerse en rojo inmediatamente.
 *
 * El rol del QA en este ciclo no es "probar al final": es codiseñar el
 * comportamiento esperado ANTES de que exista, y mantener la suite de
 * pruebas como documentación viva y como red de seguridad para refactors
 * futuros.
 */
class CalculatorTddWalkthroughTest {

    private val calculator = Calculator()

    // ----------------------------------------------------------------------
    // Iteración 1: suma
    // ----------------------------------------------------------------------
    // ROJO: en un primer momento `Calculator` no tenía ningún método.
    //       Esta prueba se escribió ANTES de que existiera `add()`, por lo
    //       que ni siquiera compilaba: ese es precisamente el estado "rojo"
    //       de partida en TDD estricto.
    // VERDE: se agregó `fun add(a: Int, b: Int): Int = a + b` — la
    //        implementación mínima que hace pasar la prueba.
    // REFACTOR: no fue necesario ningún cambio de diseño en este paso.
    @Test
    @DisplayName("RED->GREEN: add() debe sumar dos enteros")
    fun addition() {
        assertEquals(2, calculator.add(1, 1))
    }

    // ----------------------------------------------------------------------
    // Iteración 2: resta
    // ----------------------------------------------------------------------
    // ROJO: se agregó esta prueba esperando `subtract()`, que aún no existía.
    // VERDE: se implementó `fun subtract(a: Int, b: Int): Int = a - b`.
    @Test
    @DisplayName("RED->GREEN: subtract() debe restar dos enteros")
    fun subtraction() {
        assertEquals(1, calculator.subtract(3, 2))
    }

    // ----------------------------------------------------------------------
    // Iteración 3: multiplicación
    // ----------------------------------------------------------------------
    @Test
    @DisplayName("RED->GREEN: multiply() debe multiplicar dos enteros")
    fun multiplication() {
        assertEquals(42, calculator.multiply(6, 7))
    }

    // ----------------------------------------------------------------------
    // Iteración 4: división y su caso límite
    // ----------------------------------------------------------------------
    // Un/a QA que piensa en TDD no prueba solo el "camino feliz": aquí se
    // escribieron DOS pruebas antes de tocar el código de `divide()`:
    // 1) el caso normal, y 2) el caso límite (división por cero), que es
    // exactamente el tipo de caso borde que un QA debe anticipar.
    @Nested
    @DisplayName("RED->GREEN: divide()")
    inner class DivisionTests {

        @Test
        @DisplayName("divide(6, 3) retorna 2")
        fun normalDivision() {
            assertEquals(2, calculator.divide(6, 3))
        }

        @Test
        @DisplayName("divide(1, 0) lanza ArithmeticException (caso borde)")
        fun divisionByZero() {
            val exception = assertThrows<ArithmeticException> {
                calculator.divide(1, 0)
            }
            assertEquals("/ by zero", exception.message)
        }
    }

    // ----------------------------------------------------------------------
    // REFACTOR de la suite (no del código de producción)
    // ----------------------------------------------------------------------
    // Tras tener las 4 operaciones en verde, se refactorizaron las pruebas
    // de división agrupándolas en la clase anidada `DivisionTests` (ver
    // arriba) para reflejar mejor la relación "cuando se divide..." /
    // "cuando el divisor es cero...". Ninguna aserción cambió: solo se
    // reorganizó la estructura, apoyándose en que la suite seguía en verde
    // en todo momento.
}

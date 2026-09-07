package com.mateo.tddjunit.writingtests.c16_repeated_tests

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.RepetitionInfo
import org.junit.jupiter.api.TestInfo
import org.junit.jupiter.api.fail
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode.SAME_THREAD

/**
 * Tema 16/23 — Repeated Tests
 * https://docs.junit.org/6.1.3/writing-tests/repeated-tests.html
 *
 * @RepeatedTest repite un método un número fijo de veces, con soporte
 * completo de callbacks de ciclo de vida en cada repetición.
 * `failureThreshold` detiene las repeticiones restantes tras cierto número
 * de fallos.
 */
@Execution(SAME_THREAD)
class RepeatedTestsDemoTest {

    @RepeatedTest(10)
    @DisplayName("Repite la prueba 10 veces")
    fun repeatedTest() {
        // se ejecuta 10 veces
    }

    @RepeatedTest(5)
    @DisplayName("Repite 5 veces con info de repetición")
    fun repeatedTestWithRepetitionInfo(repetitionInfo: RepetitionInfo) {
        assertEquals(5, repetitionInfo.totalRepetitions)
    }

    @RepeatedTest(value = 1, name = "{displayName} {currentRepetition}/{totalRepetitions}")
    @DisplayName("Repeat!")
    fun customDisplayName(testInfo: TestInfo) {
        assertEquals("Repeat! 1/1", testInfo.displayName)
    }

    // @Disabled a propósito: este ejemplo de la documentación falla cada
    // dos repeticiones para ilustrar failureThreshold (detiene las
    // repeticiones restantes tras el segundo fallo). Se deja aquí como
    // evidencia/documentación del comportamiento, deshabilitado para que
    // `./gradlew test` quede en verde. Comenta el @Disabled para verlo en
    // acción.
    @Disabled("Ejemplo intencional de fallo: demuestra failureThreshold (ver docs de JUnit)")
    @RepeatedTest(value = 8, failureThreshold = 2)
    @DisplayName("Detiene tras umbral de fallos")
    fun repeatedTestWithFailureThreshold(repetitionInfo: RepetitionInfo) {
        // Simula un fallo cada dos repeticiones.
        if (repetitionInfo.currentRepetition % 2 == 0) {
            fail("Fallo simulado en la repetición ${repetitionInfo.currentRepetition}")
        }
    }
}

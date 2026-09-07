package com.mateo.tddjunit.writingtests.c21_timeouts

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import org.junit.jupiter.api.Timeout.ThreadMode
import java.util.concurrent.TimeUnit

/**
 * Tema 21/23 — Timeouts
 * https://docs.junit.org/6.1.3/writing-tests/timeouts.html
 *
 * @Timeout hace fallar un test, test factory, test template o método de
 * ciclo de vida si su tiempo de ejecución excede la duración indicada.
 * `threadMode = ThreadMode.SEPARATE_THREAD` ejecuta el código en un hilo
 * aparte.
 */
@Tag("timeout")
class TimeoutsDemoTest {

    @BeforeEach
    @Timeout(5)
    fun setUp() {
        // Falla si el setup tarda más de 5 segundos.
    }

    @Test
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    @DisplayName("Falla si excede 500 ms")
    fun failsIfExecutionTimeExceeds500Milliseconds() {
        // Falla si la ejecución tarda más de 500 ms.
    }

    @Test
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS, threadMode = ThreadMode.SEPARATE_THREAD)
    @DisplayName("Falla si excede 500 ms en otro hilo")
    fun failsIfExecutionTimeExceeds500MillisecondsInSeparateThread() {
        // Igual, pero el código de la prueba corre en un hilo separado.
    }
}

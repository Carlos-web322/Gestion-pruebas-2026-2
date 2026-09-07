package com.mateo.tddjunit.writingtests.c10_tagging_and_filtering

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

/**
 * Tema 10/23 — Tagging and Filtering
 * https://docs.junit.org/6.1.3/writing-tests/tagging-and-filtering.html
 *
 * Las clases y métodos se etiquetan con @Tag; luego se puede filtrar qué
 * pruebas correr por línea de comandos, Gradle o Maven, por ejemplo:
 *   ./gradlew test --tests "*" -DincludeTags="fast"
 * o configurando `tasks.test { useJUnitPlatform { includeTags("fast") } }`
 * en build.gradle.kts.
 */
@Tag("fast")
@Tag("model")
class TaggingDemoTest {

    @Test
    @Tag("taxes")
    @DisplayName("Cálculo de impuestos etiquetado")
    fun testingTaxCalculation() {
        // Esta prueba queda etiquetada con "fast", "model" y "taxes".
    }
}

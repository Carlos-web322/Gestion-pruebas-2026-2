package com.mateo.tddjunit.writingtests.c01_annotations

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

/**
 * Tema 1/23 — Annotations
 * https://docs.junit.org/6.1.3/writing-tests/annotations.html
 *
 * Las anotaciones de JUnit Jupiter (@Test, @BeforeEach, @Tag, @Disabled...)
 * viven en org.junit.jupiter.api y pueden combinarse para crear
 * meta-anotaciones propias que reducen la duplicación entre clases de
 * prueba. Aquí se define `@Fast`, una meta-anotación que aplica @Tag("fast").
 */
@Target(AnnotationTarget.TYPE, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Tag("fast")
annotation class Fast

/**
 * Meta-anotación compuesta: combina @Tag("fast") y @Test en una sola
 * anotación reutilizable.
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Tag("fast")
@Test
annotation class FastTest

class AnnotationsDemoTest {

    @Fast
    @Test
    @DisplayName("Prueba normal con tag fast")
    fun myFastTest() {
        // Prueba normal, pero descubierta también por el tag "fast".
    }

    @FastTest
    @DisplayName("Prueba vía meta-anotación @FastTest")
    fun myOtherFastTest() {
        // Gracias a la meta-anotación @FastTest, no hace falta repetir @Test.
    }
}

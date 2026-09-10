package com.mateo.tddjunit.writingtests.c14_dependency_injection

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInfo
import org.junit.jupiter.api.TestReporter

/**
 * Tema 14/23 — Dependency Injection for Constructors and Methods
 * https://docs.junit.org/6.1.3/writing-tests/dependency-injection-for-constructors-and-methods.html
 *
 * Los constructores y métodos de prueba pueden tener parámetros que JUnit
 * resuelve en tiempo de ejecución mediante implementaciones de
 * ParameterResolver. JUnit trae resolvers incorporados para TestInfo
 * (metadatos de la prueba actual) y TestReporter (publicar entradas en el
 * reporte).
 */
@DisplayName("TestInfo Demo")
class TestInfoDemoTest {

    @BeforeEach
    fun init(testInfo: TestInfo) {
        assertTrue(testInfo.displayName in listOf("TEST 1", "test2()"))
    }

    @Test
    @DisplayName("TEST 1")
    @Tag("my-tag")
    fun test1(testInfo: TestInfo) {
        assertEquals("TEST 1", testInfo.displayName)
        assertTrue("my-tag" in testInfo.tags)
    }

    // Sin @DisplayName a propósito: este método demuestra el nombre de
    // visualización POR DEFECTO que genera JUnit ("test2()"), el cual se
    // compara literalmente en el @BeforeEach de arriba. Agregar @DisplayName
    // aquí cambiaría testInfo.displayName y rompería esa aserción.
    @Test
    fun test2() {
    }
}

class TestReporterDemoTest {

    @Test
    @DisplayName("Reporta un valor de estado")
    fun reportSingleValue(testReporter: TestReporter) {
        testReporter.publishEntry("a status message")
    }

    @Test
    @DisplayName("Reporta par clave-valor")
    fun reportKeyValuePair(testReporter: TestReporter) {
        testReporter.publishEntry("a key", "a value")
    }

    @Test
    @DisplayName("Reporta múltiples pares clave-valor")
    fun reportMultipleKeyValuePairs(testReporter: TestReporter) {
        testReporter.publishEntry(
            mapOf(
                "user name" to "mateo",
                "curso" to "QA en TDD"
            )
        )
    }
}

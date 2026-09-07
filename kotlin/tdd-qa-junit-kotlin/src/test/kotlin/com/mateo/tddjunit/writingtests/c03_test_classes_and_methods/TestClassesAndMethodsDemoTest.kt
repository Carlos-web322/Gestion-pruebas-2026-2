package com.mateo.tddjunit.writingtests.c03_test_classes_and_methods

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

/**
 * Tema 3/23 — Test Classes and Methods
 * https://docs.junit.org/6.1.3/writing-tests/test-classes-and-methods.html
 *
 * Los métodos de prueba y de ciclo de vida pueden declararse localmente,
 * heredarse de superclases o interfaces; no pueden ser `abstract` (salvo
 * @TestFactory, que sí debe retornar un valor) ni `private`, aunque no
 * necesitan ser `public`.
 *
 * Nota: la documentación oficial también describe soporte para funciones
 * `suspend` de Kotlin como métodos de prueba (requiere kotlin-reflect y
 * kotlinx-coroutines-core/test en el classpath). Ese caso no se incluye
 * como código ejecutable en este proyecto para no añadir dependencias
 * adicionales solo para un ejemplo; queda documentado aquí conceptualmente.
 */
class TestClassesAndMethodsDemoTest {

    private var initialized = false

    @BeforeEach
    fun setUp() {
        initialized = true
    }

    // No es `public` explícitamente y aun así es válido: JUnit no exige
    // visibilidad pública, solo prohíbe `private`.
    @Test
    @DisplayName("Método no público es válido")
    fun regularTest() {
        assertTrue(initialized)
    }
}

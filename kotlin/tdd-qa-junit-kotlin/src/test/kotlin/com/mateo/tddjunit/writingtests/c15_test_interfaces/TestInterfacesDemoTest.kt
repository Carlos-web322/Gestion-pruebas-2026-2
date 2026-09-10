package com.mateo.tddjunit.writingtests.c15_test_interfaces

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

/**
 * Tema 15/23 — Test Interfaces and Default Methods
 * https://docs.junit.org/6.1.3/writing-tests/test-interfaces-and-default-methods.html
 *
 * JUnit permite declarar @Test en métodos `default` (o, en Kotlin, en
 * métodos de interfaz con cuerpo). Las clases que implementan la interfaz
 * heredan esas pruebas: es una forma de definir "contratos de
 * comportamiento" reutilizables (por ejemplo, un contrato de equals() o
 * de compareTo()) que cada implementación concreta valida solo con
 * implementar la interfaz.
 */
interface Testable<T> {
    fun createValue(): T
}

interface EqualsContract<T> : Testable<T> {
    fun createNotEqualValue(): T

    @Test
    @DisplayName("Valor es igual a sí mismo")
    fun valueEqualsItself() {
        val value = createValue()
        assertEquals(value, value)
    }

    @Test
    @DisplayName("Valor no es igual a null")
    fun valueDoesNotEqualNull() {
        val value = createValue()
        assertNotEquals(null, value)
    }

    @Test
    @DisplayName("Valores distintos no son iguales")
    fun valueDoesNotEqualDifferentValue() {
        val value = createValue()
        val differentValue = createNotEqualValue()
        assertNotEquals(value, differentValue)
        assertNotEquals(differentValue, value)
    }
}

interface ComparableContract<T : Comparable<T>> : Testable<T> {
    fun createSmallerValue(): T

    @Test
    @DisplayName("compareTo consigo mismo retorna 0")
    fun returnsZeroWhenComparedToItself() {
        val value = createValue()
        assertEquals(0, value.compareTo(value))
    }

    @Test
    @DisplayName("compareTo mayor retorna positivo")
    fun returnsPositiveNumberWhenComparedToSmallerValue() {
        val value = createValue()
        val smallerValue = createSmallerValue()
        assertTrue(value > smallerValue)
    }

    @Test
    @DisplayName("compareTo menor retorna negativo")
    fun returnsNegativeNumberWhenComparedToLargerValue() {
        val value = createValue()
        val smallerValue = createSmallerValue()
        assertTrue(smallerValue < value)
    }
}

/**
 * `StringTests` no escribe ni un solo @Test: hereda los 6 casos de prueba
 * de EqualsContract y ComparableContract con solo implementar 3 métodos
 * de fábrica. Este es el poder de los "contratos de comportamiento".
 */
class StringContractTest :
    ComparableContract<String>,
    EqualsContract<String> {
    override fun createValue() = "banana"

    override fun createSmallerValue() = "apple" // 'a' < 'b' en "banana"

    override fun createNotEqualValue() = "cherry"
}

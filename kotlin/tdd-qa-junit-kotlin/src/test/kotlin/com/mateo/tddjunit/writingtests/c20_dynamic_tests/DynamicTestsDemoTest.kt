package com.mateo.tddjunit.writingtests.c20_dynamic_tests

import com.mateo.tddjunit.domain.Calculator
import com.mateo.tddjunit.domain.StringUtils.isPalindrome
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.DynamicContainer.dynamicContainer
import org.junit.jupiter.api.DynamicNode
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory
import java.util.stream.IntStream
import java.util.stream.Stream

/**
 * Tema 20/23 — Dynamic Tests
 * https://docs.junit.org/6.1.3/writing-tests/dynamic-tests.html
 *
 * Un método @TestFactory no es en sí un caso de prueba: es una "fábrica"
 * que genera casos de prueba en tiempo de ejecución. Debe retornar un
 * DynamicNode (o una colección/stream/iterable/array de ellos):
 * DynamicContainer para jerarquías anidadas, DynamicTest para pruebas
 * individuales de evaluación perezosa. A diferencia de @Test, los tests
 * dinámicos NO tienen callbacks de ciclo de vida propios.
 */
class DynamicTestsDemoTest {
    private val calculator = Calculator()

    @TestFactory
    @DisplayName("Pruebas dinámicas desde colección")
    fun dynamicTestsFromCollection(): Collection<DynamicTest> =
        listOf(
            dynamicTest("1st dynamic test") { assertTrue(isPalindrome("madam")) },
            dynamicTest("2nd dynamic test") { assertEquals(4, calculator.multiply(2, 2)) }
        )

    @TestFactory
    @DisplayName("Pruebas dinámicas desde stream")
    fun dynamicTestsFromStream(): Stream<DynamicTest> =
        Stream.of("racecar", "radar", "mom", "dad")
            .map { text -> dynamicTest(text) { assertTrue(isPalindrome(text)) } }

    @TestFactory
    @DisplayName("Pruebas dinámicas de números pares")
    fun dynamicTestsFromIntStream(): Stream<DynamicTest> =
        // Genera pruebas para los primeros 10 números pares.
        IntStream.iterate(0) { n -> n + 2 }
            .limit(10)
            .mapToObj { n -> dynamicTest("test$n") { assertEquals(0, n % 2) } }

    @TestFactory
    @DisplayName("Pruebas dinámicas con contenedores")
    fun dynamicTestsWithContainers(): Stream<DynamicNode> =
        Stream.of("A", "B", "C")
            .map { input ->
                dynamicContainer(
                    "Container $input",
                    Stream.of(
                        dynamicTest("not null") { assertNotNull(input) },
                        dynamicContainer(
                            "properties",
                            Stream.of(
                                dynamicTest("length > 0") { assertTrue(input.length > 0) },
                                dynamicTest("not empty") { assertFalse(input.isEmpty()) }
                            )
                        )
                    )
                )
            }
}

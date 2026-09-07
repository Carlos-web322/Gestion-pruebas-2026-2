package com.mateo.tddjunit.writingtests.c13_nested_tests

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.EmptyStackException
import java.util.Stack

/**
 * Tema 13/23 — Nested Tests
 * https://docs.junit.org/6.1.3/writing-tests/nested-tests.html
 *
 * @Nested permite expresar relaciones jerárquicas entre grupos de pruebas
 * usando clases internas (`inner class` en Kotlin, nunca static/companion).
 * Las clases anidadas heredan los métodos de ciclo de vida de la externa.
 * Este es el ejemplo clásico de la documentación de JUnit: "una pila".
 */
@DisplayName("A stack")
class NestedTestsDemoTest {

    @Test
    @DisplayName("is instantiated with new Stack()")
    fun isInstantiatedWithNew() {
        Stack<Any>()
    }

    @Nested
    @DisplayName("when new")
    inner class WhenNew {
        lateinit var stack: Stack<Any>

        @BeforeEach
        fun createNewStack() {
            stack = Stack()
        }

        @Test
        @DisplayName("is empty")
        fun isEmpty() {
            assertTrue(stack.isEmpty())
        }

        @Test
        @DisplayName("throws EmptyStackException when popped")
        fun throwsExceptionWhenPopped() {
            assertThrows<EmptyStackException> { stack.pop() }
        }

        @Nested
        @DisplayName("after pushing an element")
        inner class AfterPushing {
            val anElement = "an element"

            @BeforeEach
            fun pushAnElement() {
                stack.push(anElement)
            }

            @Test
            @DisplayName("it is no longer empty")
            fun isNotEmpty() {
                assertFalse(stack.isEmpty())
            }

            @Test
            @DisplayName("returns the element when popped and is empty")
            fun returnElementWhenPopped() {
                assertEquals(anElement, stack.pop())
                assertTrue(stack.isEmpty())
            }
        }
    }
}

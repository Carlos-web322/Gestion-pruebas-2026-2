package com.mateo.tddjunit.domain

/**
 * Usada en el ejemplo de `assertTimeout` de la documentación de Assertions.
 */
class FibonacciCalculator {
    fun fib(n: Int): Long {
        if (n <= 1) return n.toLong()
        var a = 0L
        var b = 1L
        repeat(n - 1) {
            val next = a + b
            a = b
            b = next
        }
        return b
    }
}

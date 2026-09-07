package com.mateo.tddjunit.domain

/**
 * Calculadora simple usada como caso de estudio para demostrar TDD
 * (Test-Driven Development) a lo largo de este proyecto.
 *
 * Cada método fue agregado siguiendo el ciclo rojo -> verde -> refactor:
 * primero se escribió una prueba que fallaba (rojo), luego el código
 * mínimo para que pasara (verde), y finalmente se revisó el diseño sin
 * cambiar el comportamiento (refactor). El detalle de ese proceso está
 * documentado en `src/test/kotlin/.../tdd/CalculatorTddWalkthroughTest.kt`.
 */
class Calculator {

    fun add(a: Int, b: Int): Int = a + b

    fun subtract(a: Int, b: Int): Int = a - b

    fun multiply(a: Int, b: Int): Int = a * b

    /**
     * División entera. Lanza ArithmeticException("/ by zero") si b es 0,
     * igual que el operador `/` nativo de Kotlin/Java sobre enteros.
     */
    fun divide(a: Int, b: Int): Int = a / b
}

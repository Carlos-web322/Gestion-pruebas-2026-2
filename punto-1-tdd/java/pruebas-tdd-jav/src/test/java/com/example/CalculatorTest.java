package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


import org.junit.jupiter.api.Test;
import com.example.util.Calculator;

class CalculatorTest {

    private final Calculator calculator = new Calculator();

    @Test
    void addition() {
        assertEquals(2, calculator.add(1, 1));
    }

    @Test
    void subtraction() {
        assertEquals(3, calculator.subtract(5, 2));
    }

    @Test
    void multiplication() {
        assertEquals(12, calculator.multiply(3, 4));
    }

    @Test
    void division() {
        assertEquals(5, calculator.divide(10, 2));
    }

    @Test
    void divisionPorCero_lanzaExcepcion() {
        assertThrows(ArithmeticException.class, () -> calculator.divide(10, 0));
    }   

}   
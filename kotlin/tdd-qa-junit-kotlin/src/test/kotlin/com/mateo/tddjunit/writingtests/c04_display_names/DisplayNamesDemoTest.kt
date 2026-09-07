package com.mateo.tddjunit.writingtests.c04_display_names

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.DisplayNameGeneration
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores
import org.junit.jupiter.api.IndicativeSentencesGeneration
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/**
 * Tema 4/23 — Display Names
 * https://docs.junit.org/6.1.3/writing-tests/display-names.html
 *
 * @DisplayName da nombres legibles a clases/métodos de prueba (con
 * espacios, símbolos o incluso emojis) que aparecen en reportes e IDEs.
 * Los generadores automáticos (@DisplayNameGeneration) evitan tener que
 * escribir @DisplayName en cada método.
 */
@DisplayName("A special test case")
class DisplayNamesDemoTest {

    @Test
    @DisplayName("Custom test name containing spaces")
    fun testWithDisplayNameContainingSpaces() {
    }

    @Test
    @DisplayName("😱")
    fun testWithDisplayNameContainingEmoji() {
    }
}

/**
 * ReplaceUnderscores convierte los "_" del nombre del método en espacios
 * al momento de generar el nombre de visualización.
 */
@DisplayNameGeneration(ReplaceUnderscores::class)
class A_year_is_not_supported {

    @Test
    @DisplayName("Ejemplo generador ReplaceUnderscores")
    fun if_it_is_zero() {
    }

    @DisplayName("A negative value for year is not supported by the leap year computation.")
    @ParameterizedTest(name = "For example, year {0} is not supported.")
    @ValueSource(ints = [-1, -4])
    fun if_it_is_negative(year: Int) {
    }
}

/**
 * IndicativeSentences arma oraciones completas combinando el nombre de la
 * clase y del método, útil para leer la suite como especificación.
 */
@IndicativeSentencesGeneration(separator = " -> ", generator = ReplaceUnderscores::class)
class A_year_is_a_leap_year {

    @Test
    @DisplayName("Ejemplo de oración indicativa")
    fun if_it_is_divisible_by_4_but_not_by_100() {
    }

    @ParameterizedTest(name = "Year {0} is a leap year.")
    @ValueSource(ints = [2016, 2020, 2048])
    @DisplayName("Años bisiestos de ejemplo")
    fun if_it_is_one_of_the_following_years(year: Int) {
    }
}

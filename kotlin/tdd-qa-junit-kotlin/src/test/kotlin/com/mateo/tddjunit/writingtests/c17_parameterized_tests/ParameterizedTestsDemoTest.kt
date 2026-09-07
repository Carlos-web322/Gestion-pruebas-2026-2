package com.mateo.tddjunit.writingtests.c17_parameterized_tests

import com.mateo.tddjunit.domain.StringUtils.isPalindrome
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.ArgumentsSource
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.EnumSource
import org.junit.jupiter.params.provider.EnumSource.Mode.EXCLUDE
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.NullAndEmptySource
import org.junit.jupiter.params.provider.ValueSource
import org.junit.jupiter.params.support.ParameterDeclarations
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.aggregator.ArgumentsAccessor
import org.junit.jupiter.params.provider.ArgumentsProvider
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalUnit
import java.util.EnumSet
import java.util.stream.Stream

/**
 * Tema 17/23 — Parameterized Classes and Tests
 * https://docs.junit.org/6.1.3/writing-tests/parameterized-classes-and-tests.html
 *
 * @ParameterizedTest ejecuta un mismo método varias veces con argumentos
 * distintos. Este archivo cubre los mecanismos principales de "sources"
 * que documenta JUnit: @ValueSource, @EnumSource, @MethodSource,
 * @CsvSource, valores nulos/vacíos, nombres de visualización
 * personalizados, ArgumentsAccessor y un ArgumentsProvider propio.
 *
 * No se incluye @ParameterizedClass (que repite TODOS los tests de una
 * clase, no solo un método): sigue siendo una característica
 * experimental/incubadora de JUnit y se documenta solo conceptualmente
 * para no arriesgar la compilación del proyecto con una API inestable.
 */

// 1) @ValueSource: lista simple de literales
class ValueSourceTest {
    @ParameterizedTest
    @ValueSource(strings = ["racecar", "radar", "able was I ere I saw elba"])
    @DisplayName("Verifica palíndromos")
    fun palindromes(candidate: String) {
        assertTrue(isPalindrome(candidate))
    }
}

// 2) @EnumSource: variantes principales (todos los valores, inclusión, exclusión)
class EnumSourceDemoTest {
    @ParameterizedTest
    @EnumSource(ChronoUnit::class)
    @DisplayName("Prueba con todos los ChronoUnit")
    fun testWithEnumSource(unit: TemporalUnit) {
        assertNotNull(unit)
    }

    @ParameterizedTest
    @EnumSource(names = ["DAYS", "HOURS"])
    @DisplayName("Incluye solo DAYS y HOURS")
    fun testWithEnumSourceInclude(unit: ChronoUnit) {
        assertTrue(EnumSet.of(ChronoUnit.DAYS, ChronoUnit.HOURS).contains(unit))
    }

    @ParameterizedTest
    @EnumSource(mode = EXCLUDE, names = ["ERAS", "FOREVER"])
    @DisplayName("Excluye ERAS y FOREVER")
    fun testWithEnumSourceExclude(unit: ChronoUnit) {
        assertFalse(EnumSet.of(ChronoUnit.ERAS, ChronoUnit.FOREVER).contains(unit))
    }
}

// 3) @MethodSource: método local que provee los argumentos
class MethodSourceDemoTest {
    @ParameterizedTest
    @MethodSource("stringProvider")
    @DisplayName("Argumentos desde método local")
    fun testWithExplicitLocalMethodSource(argument: String) {
        assertNotNull(argument)
    }

    @ParameterizedTest
    @MethodSource("stringIntAndListProvider")
    @DisplayName("Múltiples argumentos desde método")
    fun testWithMultiArgMethodSource(str: String, num: Int, list: List<String>) {
        assertEquals(5, str.length)
        assertTrue(num in 1..2)
        assertEquals(2, list.size)
    }

    companion object {
        @JvmStatic
        fun stringProvider(): Stream<String> = Stream.of("apple", "banana")

        @JvmStatic
        fun stringIntAndListProvider(): Stream<Arguments> =
            Stream.of(
                arguments("apple", 1, listOf("a", "b")),
                arguments("lemon", 2, listOf("x", "y"))
            )
    }
}

// 4) @CsvSource: valores en línea, separados por coma
class CsvSourceDemoTest {
    @ParameterizedTest
    @CsvSource(
        value = [
            "apple,         1",
            "banana,        2",
            "'lemon, lime', 0xF1",
            "strawberry,    700_000"
        ]
    )
    @DisplayName("Argumentos desde CSV en línea")
    fun testWithCsvSource(fruit: String, rank: Int) {
        assertNotNull(fruit)
        assertNotEquals(0, rank)
    }
}

// 5) Valores nulos y vacíos
class NullEmptyBlankDemoTest {
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = [" ", "   ", "\t", "\n"])
    @DisplayName("Valores nulos, vacíos y en blanco")
    fun nullEmptyAndBlankStrings(text: String?) {
        assertTrue(text == null || text.isBlank())
    }
}

// 6) Nombre de visualización personalizado por invocación
class CustomDisplayNameDemoTest {
    @ParameterizedTest(name = "{index} ==> el ranking de {0} es {1}")
    @CsvSource(value = ["apple, 1", "banana, 2", "'lemon, lime', 3"])
    @DisplayName("Nombre de visualización personalizado")
    fun testWithCustomDisplayNames(fruit: String, rank: Int) {
    }
}

// 7) ArgumentsAccessor para argumentos heterogéneos
enum class Gender { F, M }

data class ParamPerson(
    val firstName: String,
    val lastName: String,
    val gender: Gender,
    val dateOfBirth: LocalDate
)

class ArgumentsAccessorDemoTest {
    @ParameterizedTest
    @CsvSource(
        value = [
            "Jane, Doe, F, 1990-05-20",
            "John, Doe, M, 1990-10-22"
        ]
    )
    @DisplayName("Argumentos heterogéneos con accessor")
    fun testWithArgumentsAccessor(arguments: ArgumentsAccessor) {
        val person =
            ParamPerson(
                arguments.getString(0),
                arguments.getString(1),
                arguments.get(2, Gender::class.java),
                arguments.get(3, LocalDate::class.java)
            )
        if (person.firstName == "Jane") {
            assertEquals(Gender.F, person.gender)
        } else {
            assertEquals(Gender.M, person.gender)
        }
        assertEquals("Doe", person.lastName)
        assertEquals(1990, person.dateOfBirth.year)
    }
}

// 8) Proveedor de argumentos personalizado (ArgumentsProvider)
class MyArgumentsProvider : ArgumentsProvider {
    override fun provideArguments(
        parameters: ParameterDeclarations,
        context: ExtensionContext
    ): Stream<out Arguments> = Stream.of("apple", "banana").map { Arguments.of(it) }
}

class ArgumentsSourceDemoTest {
    @ParameterizedTest
    @ArgumentsSource(MyArgumentsProvider::class)
    @DisplayName("Argumentos desde proveedor personalizado")
    fun testWithArgumentsSource(argument: String) {
        assertNotNull(argument)
    }
}

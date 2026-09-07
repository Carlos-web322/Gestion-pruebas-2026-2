package com.mateo.tddjunit.writingtests.c23_built_in_extensions

import com.mateo.tddjunit.domain.ListWriter
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.AutoClose
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import org.junit.jupiter.api.util.ClearSystemProperty
import org.junit.jupiter.api.util.SetSystemProperty
import java.nio.file.Files
import java.nio.file.Path

/**
 * Tema 23/23 — Built-in Extensions
 * https://docs.junit.org/6.1.3/writing-tests/built-in-extensions.html
 *
 * JUnit Jupiter trae extensiones listas para usar y registradas
 * automáticamente: @TempDir (directorio temporal por prueba), @AutoClose
 * (cierra un recurso Closeable/AutoCloseable al terminar la prueba), y
 * las extensiones de propiedades de sistema @ClearSystemProperty /
 * @SetSystemProperty (que restauran el valor original al terminar).
 *
 * No se incluyen aquí los ejemplos de @DefaultLocale/@DefaultTimeZone ni
 * el TempDirFactory sobre un sistema de archivos en memoria (Jimfs): el
 * segundo requiere la librería externa Jimfs, que no se agregó como
 * dependencia para mantener el proyecto simple; quedan documentados
 * conceptualmente en el informe.
 */
class TempDirDemoTest {

    @Test
    @DisplayName("Escribe elementos en archivo temporal")
    fun writeItemsToFile(
        @TempDir tempDir: Path
    ) {
        val file = tempDir.resolve("test.txt")

        ListWriter(file).write("a", "b", "c")

        assertEquals(listOf("a,b,c"), Files.readAllLines(file))
    }

    @Test
    @DisplayName("Copia archivo entre directorios temporales")
    fun copyFileFromSourceToTarget(
        @TempDir source: Path,
        @TempDir target: Path
    ) {
        val sourceFile = source.resolve("test.txt")
        ListWriter(sourceFile).write("a", "b", "c")

        val targetFile = Files.copy(sourceFile, target.resolve("test.txt"))

        assertNotEquals(sourceFile, targetFile)
        assertEquals(listOf("a,b,c"), Files.readAllLines(targetFile))
    }
}

/**
 * Recurso de ejemplo, propio de este proyecto (no de la documentación),
 * para poder demostrar @AutoClose sin depender de una librería externa
 * de cliente HTTP.
 */
class FakeConnection : AutoCloseable {
    var closed = false
        private set

    fun ping(): String = "pong"

    override fun close() {
        closed = true
    }
}

class AutoCloseDemoTest {

    @AutoClose
    val connection = FakeConnection()

    @Test
    @DisplayName("Conexión responde antes de cerrarse")
    fun connectionRespondsBeforeBeingClosed() {
        assertEquals("pong", connection.ping())
        assertEquals(false, connection.closed)
        // Al terminar la prueba, JUnit llamará automáticamente a close()
        // sobre `connection` gracias a @AutoClose.
    }
}

class SystemPropertyExtensionsDemoTest {

    @Test
    @ClearSystemProperty(key = "una.propiedad")
    @DisplayName("Limpia propiedad de sistema")
    fun testClearingProperty() {
        assertNull(System.getProperty("una.propiedad"))
    }

    @Test
    @SetSystemProperty(key = "una.propiedad", value = "nuevo valor")
    @DisplayName("Establece propiedad de sistema")
    fun testSettingProperty() {
        assertEquals("nuevo valor", System.getProperty("una.propiedad"))
        // JUnit restaura el valor original de "una.propiedad" (o la quita
        // si no existía) automáticamente al terminar esta prueba.
    }
}

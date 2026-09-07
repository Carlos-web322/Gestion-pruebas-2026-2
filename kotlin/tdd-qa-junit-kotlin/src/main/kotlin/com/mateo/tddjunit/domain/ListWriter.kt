package com.mateo.tddjunit.domain

import java.nio.file.Files
import java.nio.file.Path

/**
 * Usada en el ejemplo de la extensión incorporada `@TempDir`
 * (sección "Built-in Extensions" de la documentación de JUnit).
 */
class ListWriter(private val target: Path) {
    fun write(vararg items: String) {
        Files.writeString(target, items.joinToString(","))
    }
}

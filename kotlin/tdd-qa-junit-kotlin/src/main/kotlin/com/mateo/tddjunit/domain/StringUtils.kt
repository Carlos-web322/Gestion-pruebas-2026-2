package com.mateo.tddjunit.domain

/**
 * Utilidad usada en los ejemplos de Dynamic Tests y Test Interfaces de la
 * documentación de JUnit (comprobación de palíndromos).
 */
object StringUtils {
    fun isPalindrome(text: String): Boolean {
        val normalized = text.replace(" ", "").lowercase()
        return normalized == normalized.reversed()
    }
}

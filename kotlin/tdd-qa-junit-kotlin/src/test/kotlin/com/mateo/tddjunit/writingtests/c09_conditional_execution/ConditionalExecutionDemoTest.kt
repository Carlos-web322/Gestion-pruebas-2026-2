package com.mateo.tddjunit.writingtests.c09_conditional_execution

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.DisabledIfSystemProperty
import org.junit.jupiter.api.condition.DisabledOnOs
import org.junit.jupiter.api.condition.EnabledIf
import org.junit.jupiter.api.condition.EnabledIfSystemProperty
import org.junit.jupiter.api.condition.EnabledOnOs
import org.junit.jupiter.api.condition.OS.LINUX
import org.junit.jupiter.api.condition.OS.MAC
import org.junit.jupiter.api.condition.OS.WINDOWS

/**
 * Tema 9/23 — Conditional Test Execution
 * https://docs.junit.org/6.1.3/writing-tests/conditional-test-execution.html
 *
 * Estas pruebas no fallan cuando la condición no se cumple: JUnit las
 * marca como "skipped" (abortadas), ni verde ni roja. Al correr este
 * proyecto verás algunas en verde y otras "skipped" según el sistema
 * operativo desde el que ejecutes las pruebas.
 */
class ConditionalExecutionDemoTest {

    @Test
    @EnabledOnOs(LINUX, MAC)
    @DisplayName("Solo corre en Linux o macOS")
    fun onLinuxOrMac() {
        // Se ejecuta solo en Linux o macOS.
    }

    @Test
    @DisabledOnOs(WINDOWS)
    @DisplayName("Se omite en Windows")
    fun notOnWindows() {
        // Se omite (skipped) si corre en Windows.
    }

    @Test
    @EnabledIfSystemProperty(named = "os.arch", matches = ".*64.*")
    @DisplayName("Solo en arquitecturas de 64 bits")
    fun onlyOn64BitArchitectures() {
        // Se ejecuta solo si la arquitectura de la JVM contiene "64".
    }

    @Test
    @DisabledIfSystemProperty(named = "ci-server", matches = "true")
    @DisplayName("Se omite en servidor CI")
    fun notOnCiServer() {
        // Se omite si la propiedad de sistema ci-server=true.
    }

    @Test
    @EnabledIf("customCondition")
    @DisplayName("Habilitada por condición personalizada")
    fun enabledByCustomCondition() {
        // Se ejecuta porque customCondition() retorna true.
    }

    companion object {
        @JvmStatic
        fun customCondition(): Boolean = true
    }
}

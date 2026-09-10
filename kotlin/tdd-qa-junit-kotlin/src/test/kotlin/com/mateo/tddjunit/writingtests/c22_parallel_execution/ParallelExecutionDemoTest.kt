package com.mateo.tddjunit.writingtests.c22_parallel_execution

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode
import org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT
import org.junit.jupiter.api.parallel.ResourceAccessMode.READ
import org.junit.jupiter.api.parallel.ResourceAccessMode.READ_WRITE
import org.junit.jupiter.api.parallel.ResourceLock
import org.junit.jupiter.api.parallel.Resources.SYSTEM_PROPERTIES
import java.util.Properties

/**
 * Tema 22/23 — Parallel Execution
 * https://docs.junit.org/6.1.3/writing-tests/parallel-execution.html
 *
 * Por defecto JUnit ejecuta las pruebas secuencialmente en un único hilo.
 * La ejecución paralela se activa con
 * `junit.jupiter.execution.parallel.enabled=true` en
 * src/test/resources/junit-platform.properties (ver ese archivo en este
 * proyecto). @Execution declara el modo por prueba/clase (SAME_THREAD o
 * CONCURRENT); @ResourceLock protege recursos compartidos (como las
 * propiedades de sistema) para que dos pruebas concurrentes no se pisen.
 */
@Execution(ExecutionMode.CONCURRENT)
class ExplicitExecutionModeDemoTest {
    @Test
    @DisplayName("Corre en modo concurrente")
    fun testA() {
        // se ejecuta en modo concurrente (heredado de la clase)
    }

    @Test
    @Execution(ExecutionMode.SAME_THREAD)
    @DisplayName("Sobrescribe a modo same thread")
    fun testB() {
        // sobreescribe a same_thread para esta prueba puntual
    }
}

@Execution(CONCURRENT)
class StaticSharedResourcesDemoTest {
    private lateinit var backup: Properties

    @BeforeEach
    fun backup() {
        backup = Properties()
        backup.putAll(System.getProperties())
    }

    @AfterEach
    fun restore() {
        System.setProperties(backup)
    }

    @Test
    @ResourceLock(value = SYSTEM_PROPERTIES, mode = READ)
    @DisplayName("Propiedad personalizada no existe")
    fun customPropertyIsNotSetByDefault() {
        assertNull(System.getProperty("mi.propiedad"))
    }

    @Test
    @ResourceLock(value = SYSTEM_PROPERTIES, mode = READ_WRITE)
    @DisplayName("Establece propiedad en manzana")
    fun canSetCustomPropertyToApple() {
        System.setProperty("mi.propiedad", "manzana")
        assertEquals("manzana", System.getProperty("mi.propiedad"))
    }

    @Test
    @ResourceLock(value = SYSTEM_PROPERTIES, mode = READ_WRITE)
    @DisplayName("Establece propiedad en banana")
    fun canSetCustomPropertyToBanana() {
        System.setProperty("mi.propiedad", "banana")
        assertEquals("banana", System.getProperty("mi.propiedad"))
    }
}

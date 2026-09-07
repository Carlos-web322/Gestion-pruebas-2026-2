package com.mateo.tddjunit.writingtests.c02_definitions

/**
 * Tema 2/23 — Definitions
 * https://docs.junit.org/6.1.3/writing-tests/definitions.html
 *
 * Página puramente conceptual: la documentación oficial no trae ejemplos
 * de código (ni Kotlin ni Java) para este tema, así que no hay una prueba
 * ejecutable aquí. Define el vocabulario base usado en el resto del
 * capítulo:
 *
 * A nivel de JUnit Platform:
 * - Container: un nodo del árbol de pruebas que contiene otros containers
 *   o tests (por ejemplo, una clase de prueba).
 * - Test: un nodo que verifica un comportamiento esperado al ejecutarse
 *   (por ejemplo, un método @Test).
 *
 * A nivel de JUnit Jupiter:
 * - Lifecycle Method: cualquier método anotado con @BeforeAll, @AfterAll,
 *   @BeforeEach o @AfterEach.
 * - Test Class: una clase de nivel superior, una clase miembro static, o
 *   una clase @Nested, con al menos un método de prueba.
 * - Test Method: un método de instancia anotado con @Test, @RepeatedTest,
 *   @ParameterizedTest, @TestFactory o @TestTemplate.
 */

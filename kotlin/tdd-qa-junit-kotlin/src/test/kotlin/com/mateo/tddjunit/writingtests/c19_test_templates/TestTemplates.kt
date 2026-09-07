package com.mateo.tddjunit.writingtests.c19_test_templates

/**
 * Tema 19/23 — Test Templates
 * https://docs.junit.org/6.1.3/writing-tests/test-templates.html
 *
 * Página conceptual, sin ejemplos de código (ni Kotlin ni Java) en la
 * documentación oficial. Un método @TestTemplate funciona como plantilla,
 * no como caso de prueba fijo: se invoca varias veces según los contextos
 * de invocación provistos por un TestTemplateInvocationContextProvider
 * registrado, y cada invocación se comporta como un @Test normal.
 * @RepeatedTest (tema 16) y @ParameterizedTest (tema 17) son
 * especializaciones incorporadas de los test templates.
 */

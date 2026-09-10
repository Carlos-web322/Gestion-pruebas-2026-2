package com.mateo.tddjunit.writingtests.c18_class_templates

/**
 * Tema 18/23 — Class Templates
 * https://docs.junit.org/6.1.3/writing-tests/class-templates.html
 *
 * Página conceptual, sin ejemplos de código (ni Kotlin ni Java) en la
 * documentación oficial. @ClassTemplate no es una clase de prueba normal:
 * es una plantilla que se invoca varias veces según los contextos de
 * invocación que devuelva un ClassTemplateInvocationContextProvider
 * registrado. Cada invocación se comporta como una clase de prueba
 * completa, con soporte total de callbacks de ciclo de vida y extensiones.
 * Las clases parametrizadas (@ParameterizedClass, ver tema 17) son una
 * especialización incorporada de las plantillas de clase.
 */

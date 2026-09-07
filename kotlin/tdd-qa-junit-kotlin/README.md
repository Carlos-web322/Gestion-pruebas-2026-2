# TDD + QA con Kotlin y JUnit 6.1.3

Proyecto de práctica que acompaña el trabajo **"Cómo funciona un QA en TDD"**,
basado en el capítulo *Writing Tests* de la documentación oficial de JUnit
6.1.3: <https://docs.junit.org/6.1.3/writing-tests/intro.html>.

## ¿Qué hay aquí?

1. **`src/main/kotlin/.../domain/`** — un puñado de clases pequeñas
   (`Calculator`, `StringUtils`, `FibonacciCalculator`, `Person`,
   `ListWriter`) usadas como "código de producción" en los ejemplos.

2. **`src/test/kotlin/.../tdd/CalculatorTddWalkthroughTest.kt`** — el
   corazón del trabajo: documenta paso a paso cómo se construyó
   `Calculator` siguiendo el ciclo **rojo → verde → refactor** de TDD,
   con comentarios explicando cada fase.

3. **`src/test/kotlin/.../writingtests/c01_...` a `c23_...`** — una
   carpeta por cada uno de los 23 temas del capítulo "Writing Tests" de
   la documentación de JUnit (Annotations, Assertions, Nested Tests,
   Parameterized Tests, Parallel Execution, etc.), con el código Kotlin
   adaptado fielmente de esa documentación oficial.

## Cómo ejecutarlo

**Con IntelliJ IDEA (recomendado):**

1. Abre esta carpeta como proyecto en IntelliJ (`File > Open`).
2. Espera a que IntelliJ sincronice Gradle (la primera vez descarga
   Kotlin, JUnit y Gradle mismo desde Internet — puede tardar unos
   minutos).
3. Click derecho sobre la carpeta `src/test/kotlin` → **Run 'All Tests'**.
4. Revisa los resultados en la ventana de pruebas: verde = pasó,
   gris/amarillo = "skipped" (omitida a propósito, por ejemplo por
   `@Disabled` o por una condición de `@EnabledOnOs`), nunca debería
   quedar nada en rojo salvo que tú mismo actives alguno de los ejemplos
   marcados como "fallo intencional" (ver más abajo).

**Por línea de comandos**, desde esta carpeta:

```bash
./gradlew test           # Linux/macOS
gradlew.bat test         # Windows
```

El reporte HTML queda en `build/reports/tests/test/index.html`.

## Ejemplos de fallo intencional (a propósito)

Algunos temas de la documentación de JUnit ilustran cómo se ve una prueba
en rojo (excepción no capturada, aserción incorrecta, `failureThreshold`
en `@RepeatedTest`). Esos métodos están marcados con `@Disabled(...)` para
que la ejecución normal del proyecto quede en verde. Si quieres ver cómo
luce un fallo real en la consola de JUnit, comenta temporalmente la línea
`@Disabled(...)` de cualquiera de ellos (están en
`c07_exception_handling` y `c16_repeated_tests`) y vuelve a correr las
pruebas.

## Qué NO se incluyó y por qué

Para que el proyecto compile de forma confiable con una sola dependencia
(`junit-jupiter`, vía `junit-bom`), se dejaron fuera de forma consciente
algunos ejemplos de la documentación que requieren librerías externas
adicionales:

- Funciones `suspend` de Kotlin como métodos de prueba (tema 3): requiere
  `kotlinx-coroutines-core`/`-test`.
- `@ParameterizedClass` (tema 17): característica experimental/incubadora
  de JUnit 6, se documenta solo conceptualmente.
- `TempDirFactory` sobre un sistema de archivos en memoria con Jimfs
  (tema 23): requiere la librería Jimfs.
- Inyección de parámetros aleatorios vía una anotación `@Random` propia
  (tema 14): la documentación no publica el código de esa extensión.

Estos casos quedan documentados en los comentarios del código y en el
informe entregado junto con este proyecto.

## Dependencias (build.gradle.kts)

```kotlin
testImplementation(platform("org.junit:junit-bom:6.1.3"))
testImplementation("org.junit.jupiter:junit-jupiter")
testRuntimeOnly("org.junit.platform:junit-platform-launcher")
```

Todas se descargan de Maven Central la primera vez que sincronizas el
proyecto o corres `./gradlew test`; necesitas conexión a internet normal
(no restringida) la primera vez.

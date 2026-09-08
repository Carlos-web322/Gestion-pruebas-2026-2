# Punto 2 — Ejercicios Softtek

Replicación del ejercicio del artículo de Softtek **"¿Por qué hacer testing unitario?"** usando **JUnit 5** y **Mockito** (versión modernizada del artículo original que usa JUnit 4).

## Ejercicio

Resolver ecuaciones de primer grado del tipo `ax + b = c` mediante dos clases:

- **`Parseador`** — extrae los componentes numéricos y el operador de una ecuación en formato String.
- **`EcuacionPrimerGrado`** — usa el Parseador y aplica la fórmula `x = (c - b) / a`.

## Conceptos demostrados

### 1. Tipos de pruebas

| Suite | Tipo | Propósito |
|-------|------|-----------|
| `ParseadorTest` | Unitaria pura | Prueba métodos del Parseador sin dependencias externas. |
| `EcuacionPrimerGradoIntegrationTest` | Integración | Prueba `EcuacionPrimerGrado` con el Parseador REAL. |
| `EcuacionPrimerGradoMockitoTest` | Unitaria con doble | Prueba `EcuacionPrimerGrado` mockeando el Parseador con Mockito. |

### 2. Principio FIRST

- **Fast** — la suite completa corre en ~2 segundos.
- **Isolated** — cada test es independiente; los mocks aíslan dependencias.
- **Repeatable** — mismos resultados en cada ejecución.
- **Self-validating** — cada test se auto-verifica con `assertEquals`.
- **Timely** — pruebas escritas junto al código de producción.

### 3. Estructura AAA (Arrange - Act - Assert)

Todos los tests siguen el patrón:

- **Arrange:** preparación de datos y mocks.
- **Act:** ejecución del SUT (System Under Test).
- **Assert:** verificación del resultado con `assertEquals`.

### 4. Mockito

Se usan las anotaciones `@Mock`, `@InjectMocks` y `@ExtendWith(MockitoExtension.class)`, junto con la configuración de comportamiento con `when(...).thenReturn(...)`.

## Cómo correr las pruebas

Desde la carpeta `ecuacion-primer-grado`:

    mvn test

Resultado esperado:

    Tests run: 12, Failures: 0, Errors: 0, Skipped: 0
    BUILD SUCCESS

## Modernización frente al artículo original

| Concepto | Artículo (JUnit 4) | Este proyecto (JUnit 5) |
|----------|-------------------|-------------------------|
| Inicializar mocks | `MockitoAnnotations.initMocks(this)` en `@Before` | `@ExtendWith(MockitoExtension.class)` |
| Asserts | `org.junit.Assert.*` | `org.junit.jupiter.api.Assertions.*` |
| Anotación de test | `import org.junit.Test` | `import org.junit.jupiter.api.Test` |

## Referencia

Artículo original: *"¿Por qué hacer testing unitario?"* — Softtek.
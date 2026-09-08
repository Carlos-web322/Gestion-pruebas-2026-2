# Gestión de Pruebas y Configuración - 2026-2

Repositorio grupal para las entregas de la materia. Contiene los tres puntos del trabajo, cada uno con sus subpartes en tecnologías distintas.

## Integrantes

| # | Nombre | Usuario GitHub |
|---|--------|----------------|
| 1 | Carlos Alberto Díaz Betancur | Carlos-web322 |
| 2 | Mateo Maya | (por completar) |
| 3 | Jorge Rivera | (por completar) |
| 4 | Harol | (por completar) |
| 5 | Sebastián | (por completar) |

## Distribución de responsabilidades

| Punto | Subparte | Responsable | Estado |
|-------|----------|-------------|--------|
| 1 | TDD Java | Carlos | ✅ Completado |
| 1 | TDD Kotlin | Mateo Maya | ⚪ Pendiente |
| 2 | Ejercicios Softtek | Carlos | ✅ Completado |
| 3 | CRUD Python + TDD | Jorge Rivera | ⚪ Pendiente |
| 3 | CRUD React + TDD | Harol | ⚪ Pendiente |
| 3 | CRUD (lenguaje por definir) + TDD | Sebastián | ⚪ Pendiente |

## Estructura del repositorio

```
Gestion-pruebas-2026-2/
├── punto-1-tdd/
│   ├── java/               # TDD con JUnit 5
│   └── kotlin/             # TDD con Kotlin
├── punto-2-softtek/        # Ejercicios Softtek
└── punto-3-crud-tdd/
    ├── python/             # CRUD + TDD en Python
    ├── react/              # CRUD + TDD en React
    └── otro-lenguaje/      # CRUD + TDD (otro lenguaje)
```

Cada subcarpeta contiene su propio README con instrucciones específicas para correr el proyecto y sus pruebas.

## Flujo de trabajo con Git

1. Cada integrante trabaja en una rama independiente por punto (ej. `punto-1-java`, `punto-3-python`).
2. Al terminar, se abre un **Pull Request** hacia `main`.
3. Otro compañero revisa y aprueba el merge.
4. Nunca se hace push directo a `main`.

## Cómo clonar el repositorio

```bash
git clone https://github.com/Carlos-web322/Gestion-pruebas-2026-2.git
cd Gestion-pruebas-2026-2
```
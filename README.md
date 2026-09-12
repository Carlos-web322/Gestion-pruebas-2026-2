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
| 1 | TDD Kotlin | Mateo Maya | ✅ Completado |
| 2 | Ejercicios Softtek | Carlos | ✅ Completado |
| 3 | CRUD Python + TDD | Jorge Rivera | ✅ Completado |
| 3 | CRUD React + TDD | Harol | ✅ Completado |
| 3 | CRUD Angular + TDD | Sebastián | ✅ Completado |

## Estructura del repositorio

```
Gestion-pruebas-2026-2/
├── punto-1-tdd/
│   └── java/                          # TDD con JUnit 5 (Carlos)
├── kotlin/
│   └── tdd-qa-junit-kotlin/           # TDD con JUnit 6 / Kotlin (Mateo)
├── punto-2-softtek/
│   └── ecuacion-primer-grado/         # Ejercicios Softtek (Carlos)
├── punto-3-crud-tdd/
│   └── python/                        # CRUD + TDD en Python (Jorge)
├── punto-3-react-tareas/
│   └── contador-tdd/                  # CRUD + TDD en React (Harol)
├── punto-3-angular/                   # CRUD + TDD en Angular (Sebastián)
└── docs/
    └── comparativa-tdd.html           # Comparativa transversal entre lenguajes
```

## 🚀 Cómo correr cada punto

### Prerequisitos

Dependiendo del punto que quieras ejecutar, necesitas tener instalado:

| Herramienta | Versión mínima | Necesaria para |
|-------------|----------------|----------------|
| JDK (Temurin) | 17 | Java, Kotlin, Softtek |
| Maven | 3.8+ | Java, Softtek |
| Gradle | (incluido en el wrapper) | Kotlin |
| Node.js | 18+ | React, Angular |
| Python | 3.10+ | Python |

Todos los comandos se ejecutan desde la **raíz del repositorio** salvo que se indique lo contrario.

---

### Punto 1 — Java (JUnit 5)

```bash
cd punto-1-tdd/java/pruebas-tdd-jav
mvn test
```

**Resultado esperado:** `Tests run: 5, Failures: 0, Errors: 0`

---

### Punto 1 — Kotlin (JUnit 6)

```bash
cd kotlin/tdd-qa-junit-kotlin
./gradlew test          # Linux / Mac
gradlew.bat test        # Windows
```

**Resultado esperado:** `BUILD SUCCESSFUL` con todos los tests en verde.

---

### Punto 2 — Softtek (JUnit 5 + Mockito)

```bash
cd punto-2-softtek/ecuacion-primer-grado
mvn test
```

**Resultado esperado:** `Tests run: 12, Failures: 0, Errors: 0`

---

### Punto 3 — Python (pytest)

```bash
cd punto-3-crud-tdd/python
pip install -r requirements.txt
pytest
```

**Resultado esperado:** todos los tests en verde (`passed`).

Para levantar la app web:

```bash
python app.py
```

---

### Punto 3 — React (Vitest)

```bash
cd punto-3-react-tareas/contador-tdd
npm install
npm test
```

**Resultado esperado:** todos los tests en verde.

Para levantar la app:

```bash
npm run dev
```

Y abrir `http://localhost:5173` en el navegador.

---

### Punto 3 — Angular (Jasmine + Karma)

```bash
cd punto-3-angular
npm install
npm test
```

**Resultado esperado:** `Executed 16 of 16 SUCCESS`

Para levantar la app:

```bash
npm start
```

Y abrir `http://localhost:4200` en el navegador.

---

## 📊 Documentación

Consulta la [Comparativa TDD entre lenguajes](docs/comparativa-tdd.html) para ver la explicación consolidada del proyecto: filosofía TDD, tabla comparativa entre tecnologías y ejemplos de código de cada subparte.

## Flujo de trabajo con Git

1. Cada integrante trabaja en una rama independiente por punto (ej. `punto-1-java`, `punto-3-python`).
2. Al terminar, se abre un **Pull Request** hacia `main`.
3. Otro compañero revisa y aprueba el merge.

## Cómo clonar el repositorio

```bash
git clone https://github.com/Carlos-web322/Gestion-pruebas-2026-2.git
cd Gestion-pruebas-2026-2
```
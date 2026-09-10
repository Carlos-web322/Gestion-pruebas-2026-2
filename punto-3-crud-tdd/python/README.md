# CRUD Usuarios con TDD en Python

CRUD de usuarios desarrollado con la metodologia **TDD (Test-Driven Development)** usando Flask, SQLite y pytest.

---

## Estructura del proyecto

```
python/
├── app.py              # API REST (6 endpoints)
├── test_app.py         # 14 tests TDD con pytest
├── requirements.txt    # Dependencias (flask, pytest)
└── templates/
    └── index.html      # Frontend HTML (formulario + tabla)
```

---

## Requisitos

- Python 3.10+
- pip

---

## Instalacion

```bash
cd python
pip install -r requirements.txt
```

---

## Ejecutar la aplicacion

```bash
python app.py
```

Abrir en el navegador: `http://localhost:5000`

---

## Ejecutar las pruebas

```bash
pytest test_app.py -v
```

Resultado esperado:

```
test_app.py::test_crear_usuario PASSED
test_app.py::test_crear_usuario_sin_nombre PASSED
test_app.py::test_crear_usuario_sin_email PASSED
test_app.py::test_crear_usuario_body_vacio PASSED
test_app.py::test_listar_usuarios_vacio PASSED
test_app.py::test_listar_usuarios_con_datos PASSED
test_app.py::test_obtener_usuario_por_id PASSED
test_app.py::test_obtener_usuario_no_existe PASSED
test_app.py::test_actualizar_usuario PASSED
test_app.py::test_actualizar_usuario_no_existe PASSED
test_app.py::test_actualizar_usuario_sin_campos PASSED
test_app.py::test_eliminar_usuario PASSED
test_app.py::test_eliminar_usuario_no_existe PASSED
test_app.py::test_flujo_completo PASSED

14 passed
```

---

## Que es TDD

TDD (Test-Driven Development) es una metodologia donde los tests se escriben **antes** del codigo. El ciclo tiene 3 pasos:

```
1. RED      →  Escribir un test que falla (el codigo no existe aun)
2. GREEN    →  Escribir el codigo minimo para que el test pase
3. REFACTOR →  Mejorar el codigo sin romper los tests
```

Se repite este ciclo por cada funcionalidad.

---

## API REST — Endpoints

| Metodo | Ruta             | Descripcion         | Body (JSON)                              |
|--------|------------------|----------------------|------------------------------------------|
| GET    | `/`              | Frontend HTML        | —                                        |
| GET    | `/usuarios`      | Listar todos         | —                                        |
| GET    | `/usuarios/<id>` | Obtener uno por ID   | —                                        |
| POST   | `/usuarios`      | Crear usuario        | `{"nombre": "...", "email": "...", "edad": 25}` |
| PUT    | `/usuarios/<id>` | Actualizar usuario   | `{"nombre": "...", "email": "...", "edad": 30}` |
| DELETE | `/usuarios/<id>` | Eliminar usuario     | —                                        |

- `nombre` y `email` son obligatorios.
- `edad` es opcional.

---

## Inventario de pruebas — 14 tests

### CREATE (4 tests)

| # | Test                            | Que valida                                  |
|---|----------------------------------|---------------------------------------------|
| 1 | `test_crear_usuario`            | POST con datos validos retorna 201 + datos  |
| 2 | `test_crear_usuario_sin_nombre` | POST sin nombre retorna 400                 |
| 3 | `test_crear_usuario_sin_email`  | POST sin email retorna 400                  |
| 4 | `test_crear_usuario_body_vacio` | POST con body vacio retorna 400             |

### READ (4 tests)

| # | Test                              | Que valida                                  |
|---|-----------------------------------|---------------------------------------------|
| 5 | `test_listar_usuarios_vacio`     | GET /usuarios sin datos retorna lista vacia |
| 6 | `test_listar_usuarios_con_datos` | GET /usuarios con 2 creados retorna 2       |
| 7 | `test_obtener_usuario_por_id`    | GET /usuarios/1 retorna el usuario correcto |
| 8 | `test_obtener_usuario_no_existe` | GET /usuarios/999 retorna 404               |

### UPDATE (3 tests)

| # | Test                                | Que valida                                  |
|---|-------------------------------------|---------------------------------------------|
| 9 | `test_actualizar_usuario`          | PUT con datos validos retorna 200 + datos   |
|10 | `test_actualizar_usuario_no_existe`| PUT a ID inexistente retorna 404            |
|11 | `test_actualizar_usuario_sin_campos`| PUT con body vacio retorna 400             |

### DELETE (2 tests)

| # | Test                              | Que valida                                    |
|---|-----------------------------------|-----------------------------------------------|
|12 | `test_eliminar_usuario`          | DELETE retorna 200 y el usuario ya no existe   |
|13 | `test_eliminar_usuario_no_existe`| DELETE a ID inexistente retorna 404            |

### INTEGRACION (1 test)

| # | Test                  | Que valida                                         |
|---|-----------------------|----------------------------------------------------|
|14 | `test_flujo_completo` | Ciclo completo: crear → leer → actualizar → eliminar |

---

## Demostracion TDD en vivo — Paso a paso

A continuacion se detalla como demostrar el ciclo RED → GREEN → REFACTOR en la exposicion, agregando una nueva funcionalidad: **validar que no se cree un usuario con email duplicado**.

### Paso 1: RED — Escribir el test (falla)

Abrir `test_app.py` y agregar este test al final del archivo, **despues de la linea 139** (despues de `test_eliminar_usuario_no_existe`):

```python
def test_crear_usuario_email_duplicado(client):
    """No debe permitir crear dos usuarios con el mismo email."""
    client.post("/usuarios", json={"nombre": "Ana", "email": "ana@test.com"})
    resp = client.post("/usuarios", json={"nombre": "Luis", "email": "ana@test.com"})
    assert resp.status_code == 400
    assert "duplicado" in resp.get_json()["error"]
```

Ejecutar los tests:

```bash
pytest test_app.py -v
```

**Resultado: el test nuevo FALLA (RED)**. Retorna 201 en vez de 400 porque no existe la validacion.

```
test_app.py::test_crear_usuario_email_duplicado FAILED
    assert resp.status_code == 400
    assert 201 == 400
```

### Paso 2: GREEN — Implementar el codigo minimo

Abrir `app.py`, ir a la funcion `crear()` (**linea 51**) y agregar la validacion de email duplicado **antes** del INSERT. El bloque completo queda asi:

```python
@app.post("/usuarios")
def crear():
    data = request.get_json()
    if not data or not data.get("nombre") or not data.get("email"):
        return jsonify({"error": "nombre y email son obligatorios"}), 400
    db = get_db()
    # --- NUEVO: validar email duplicado ---
    existe = db.execute(
        "SELECT id FROM usuarios WHERE email = ?", (data["email"],)
    ).fetchone()
    if existe:
        return jsonify({"error": "Email duplicado"}), 400
    # --- FIN NUEVO ---
    cursor = db.execute(
        "INSERT INTO usuarios (nombre, email, edad) VALUES (?, ?, ?)",
        (data["nombre"], data["email"], data.get("edad")),
    )
    db.commit()
    return jsonify({"id": cursor.lastrowid, **data}), 201
```

Las lineas nuevas van entre la linea 54 (`db = get_db()`) y la linea 55 (`cursor = db.execute(...)`).

Ejecutar los tests de nuevo:

```bash
pytest test_app.py -v
```

**Resultado: todos pasan (GREEN)**, incluyendo el nuevo.

```
test_app.py::test_crear_usuario_email_duplicado PASSED
15 passed
```

### Paso 3: REFACTOR

En este caso el codigo ya es limpio y no necesita refactorizar. Se puede mencionar:

> "En REFACTOR revisamos si el codigo se puede mejorar sin romper los tests.
> En este caso es tan simple que no necesita cambios, pero en un proyecto
> real aqui se eliminaria duplicacion o se mejoraria la estructura."

### Resumen de la demostracion

| Paso     | Que se hace                          | Resultado           |
|----------|---------------------------------------|----------------------|
| RED      | Agregar test en `test_app.py` L140   | Test falla (201 != 400) |
| GREEN    | Agregar validacion en `app.py` L55   | 15/15 tests pasan    |
| REFACTOR | Revisar, no hay cambios necesarios   | 15/15 tests pasan    |

---

## Tecnologias

| Tecnologia | Para que                          |
|------------|-----------------------------------|
| Python     | Lenguaje                          |
| Flask      | Framework web (API REST)          |
| SQLite     | Base de datos embebida            |
| pytest     | Framework de pruebas              |
| HTML/JS    | Frontend (consume la API)         |

"""
Tests TDD para CRUD de Usuarios.

Metodología TDD: cada test se escribió ANTES de implementar la funcionalidad.
Ciclo: RED (test falla) → GREEN (código mínimo) → REFACTOR (mejorar).
"""
import os
import tempfile
import pytest
from app import app


@pytest.fixture
def client():
    """Crea un cliente de prueba con BD temporal (se borra al terminar)."""
    db_fd, db_path = tempfile.mkstemp(suffix=".db")
    os.environ["DATABASE"] = db_path

    import app as app_module
    app_module.DATABASE = db_path
    app.config["TESTING"] = True

    with app.test_client() as c:
        yield c

    os.close(db_fd)
    os.unlink(db_path)


# ========== CREATE ==========

def test_crear_usuario(client):
    """RED: no existe POST /usuarios → GREEN: implementar endpoint."""
    resp = client.post("/usuarios", json={
        "nombre": "Jorge Rivera",
        "email": "jorge@test.com",
        "edad": 25,
    })
    assert resp.status_code == 201
    data = resp.get_json()
    assert data["nombre"] == "Jorge Rivera"
    assert data["email"] == "jorge@test.com"
    assert "id" in data


def test_crear_usuario_sin_nombre(client):
    """RED: no valida campos → GREEN: agregar validación."""
    resp = client.post("/usuarios", json={"email": "test@test.com"})
    assert resp.status_code == 400


def test_crear_usuario_sin_email(client):
    resp = client.post("/usuarios", json={"nombre": "Test"})
    assert resp.status_code == 400


def test_crear_usuario_body_vacio(client):
    resp = client.post("/usuarios", json={})
    assert resp.status_code == 400


# ========== READ ==========

def test_listar_usuarios_vacio(client):
    """RED: no existe GET /usuarios → GREEN: implementar endpoint."""
    resp = client.get("/usuarios")
    assert resp.status_code == 200
    assert resp.get_json() == []


def test_listar_usuarios_con_datos(client):
    client.post("/usuarios", json={"nombre": "Ana", "email": "ana@test.com"})
    client.post("/usuarios", json={"nombre": "Luis", "email": "luis@test.com"})
    resp = client.get("/usuarios")
    assert resp.status_code == 200
    assert len(resp.get_json()) == 2


def test_obtener_usuario_por_id(client):
    """RED: no existe GET /usuarios/<id> → GREEN: implementar endpoint."""
    client.post("/usuarios", json={"nombre": "Ana", "email": "ana@test.com"})
    resp = client.get("/usuarios/1")
    assert resp.status_code == 200
    assert resp.get_json()["nombre"] == "Ana"


def test_obtener_usuario_no_existe(client):
    resp = client.get("/usuarios/999")
    assert resp.status_code == 404


# ========== UPDATE ==========

def test_actualizar_usuario(client):
    """RED: no existe PUT /usuarios/<id> → GREEN: implementar endpoint."""
    client.post("/usuarios", json={"nombre": "Ana", "email": "ana@test.com"})
    resp = client.put("/usuarios/1", json={
        "nombre": "Ana Maria",
        "email": "anam@test.com",
        "edad": 30,
    })
    assert resp.status_code == 200
    assert resp.get_json()["nombre"] == "Ana Maria"


def test_actualizar_usuario_no_existe(client):
    resp = client.put("/usuarios/999", json={
        "nombre": "Test",
        "email": "test@test.com",
    })
    assert resp.status_code == 404


def test_actualizar_usuario_sin_campos(client):
    client.post("/usuarios", json={"nombre": "Ana", "email": "ana@test.com"})
    resp = client.put("/usuarios/1", json={})
    assert resp.status_code == 400


# ========== DELETE ==========

def test_eliminar_usuario(client):
    """RED: no existe DELETE /usuarios/<id> → GREEN: implementar endpoint."""
    client.post("/usuarios", json={"nombre": "Ana", "email": "ana@test.com"})
    resp = client.delete("/usuarios/1")
    assert resp.status_code == 200
    # Verificar que ya no existe
    resp = client.get("/usuarios/1")
    assert resp.status_code == 404


def test_eliminar_usuario_no_existe(client):
    resp = client.delete("/usuarios/999")
    assert resp.status_code == 404


# ========== INTEGRACIÓN ==========

def test_flujo_completo(client):
    """Test de integración: crear → leer → actualizar → eliminar."""
    # Crear
    resp = client.post("/usuarios", json={
        "nombre": "Test",
        "email": "test@test.com",
        "edad": 20,
    })
    assert resp.status_code == 201
    uid = resp.get_json()["id"]

    # Leer
    resp = client.get(f"/usuarios/{uid}")
    assert resp.get_json()["nombre"] == "Test"

    # Actualizar
    resp = client.put(f"/usuarios/{uid}", json={
        "nombre": "Test Actualizado",
        "email": "nuevo@test.com",
        "edad": 21,
    })
    assert resp.get_json()["nombre"] == "Test Actualizado"

    # Eliminar
    resp = client.delete(f"/usuarios/{uid}")
    assert resp.status_code == 200

    # Verificar que no existe
    resp = client.get(f"/usuarios/{uid}")
    assert resp.status_code == 404

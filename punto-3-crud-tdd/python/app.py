import sqlite3
import os
from flask import Flask, request, jsonify, g, render_template

app = Flask(__name__)
DATABASE = os.environ.get("DATABASE", "usuarios.db")


def get_db():
    if "db" not in g:
        g.db = sqlite3.connect(DATABASE)
        g.db.row_factory = sqlite3.Row
        g.db.execute(
            """CREATE TABLE IF NOT EXISTS usuarios (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT NOT NULL,
                email TEXT NOT NULL,
                edad INTEGER
            )"""
        )
    return g.db


@app.teardown_appcontext
def close_db(exception):
    db = g.pop("db", None)
    if db:
        db.close()


@app.get("/")
def index():
    return render_template("index.html")


@app.get("/usuarios")
def listar():
    db = get_db()
    rows = db.execute("SELECT * FROM usuarios").fetchall()
    return jsonify([dict(r) for r in rows])


@app.get("/usuarios/<int:uid>")
def obtener(uid):
    db = get_db()
    row = db.execute("SELECT * FROM usuarios WHERE id = ?", (uid,)).fetchone()
    if not row:
        return jsonify({"error": "Usuario no encontrado"}), 404
    return jsonify(dict(row))


@app.post("/usuarios")
def crear():
    data = request.get_json()
    if not data or not data.get("nombre") or not data.get("email"):
        return jsonify({"error": "nombre y email son obligatorios"}), 400
    db = get_db()
    cursor = db.execute(
        "INSERT INTO usuarios (nombre, email, edad) VALUES (?, ?, ?)",
        (data["nombre"], data["email"], data.get("edad")),
    )
    db.commit()
    return jsonify({"id": cursor.lastrowid, **data}), 201


@app.put("/usuarios/<int:uid>")
def actualizar(uid):
    data = request.get_json()
    if not data or not data.get("nombre") or not data.get("email"):
        return jsonify({"error": "nombre y email son obligatorios"}), 400
    db = get_db()
    result = db.execute(
        "UPDATE usuarios SET nombre = ?, email = ?, edad = ? WHERE id = ?",
        (data["nombre"], data["email"], data.get("edad"), uid),
    )
    db.commit()
    if result.rowcount == 0:
        return jsonify({"error": "Usuario no encontrado"}), 404
    return jsonify({"id": uid, **data})


@app.delete("/usuarios/<int:uid>")
def eliminar(uid):
    db = get_db()
    result = db.execute("DELETE FROM usuarios WHERE id = ?", (uid,))
    db.commit()
    if result.rowcount == 0:
        return jsonify({"error": "Usuario no encontrado"}), 404
    return jsonify({"mensaje": "Usuario eliminado"})


if __name__ == "__main__":
    app.run(debug=True)

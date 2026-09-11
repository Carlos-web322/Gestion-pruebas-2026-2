# React + TypeScript + Vite

## Instalación

```bash
npm install
```

| Comando            | Descripción                                       |
|---------------------|--------------------------------------------------|
| `npm run dev`       | Levanta el servidor de desarrollo                |
| `npm run test`      | Corre los tests                                  |
| `npm run test:ui`   | Corre los tests con interfaz visual de Vitest    |

## Estructura del proyecto

```
src/
├── components/          # Componentes de UI, sin lógica de negocio
│   ├── TareaForm.tsx    # Formulario para agregar una tarea
│   ├── TareaItem.tsx    # Una tarea individual (checkbox + texto + eliminar)
│   ├── TareaList.tsx    # Lista de tareas (o mensaje si está vacía)
│   └── __tests__/       # Tests de cada componente
├── hooks/
│   ├── useTareas.ts     # Estado y lógica de negocio: agregar, togglear, eliminar
│   └── __tests__/
├── types/
│   └── Tarea.ts         # Tipo compartido: { id, texto, completada }
├── utils/
│   ├── storage.ts        # Lectura/escritura de tareas en localStorage
│   └── __tests__/
├── App.tsx               # Composición: conecta el hook con los componentes
└── main.tsx               # Punto de entrada de la aplicación
```

- **`components/`** Renderiza y dispara eventos
- **`hooks/useTareas`** Lógica del CRUD y persistencia en el `localStorage`
- **`utils/storage.ts`** Aisla acceso a `localStorage`
- Los test viven junto a lo que testean (carpetas `__tests__/`)

  Cada pieza del proyecto siguió este ciclo:

1. **Red** — se escribe una prueba que describe el comportamiento esperado y falla porque el código aún no existe.
2. **Green** — se escribe el código mínimo necesario para que la prueba pase.

import type { Tarea } from "../types/Tarea"

interface tareaItemProps {
    tarea: Tarea
    onUpdate: (id: string) => void
    onDelete: (id: string) => void
}

export function TareaItem({ tarea, onUpdate, onDelete }: tareaItemProps) {
    return (
        <li>
            <input
                type="checkbox"
                checked={tarea.estado}
                onChange={() => onUpdate(tarea.id)}
                aria-label={tarea.text}
            />
            <span
                style={{ textDecoration: tarea.estado ? 'line-through' : 'none' }}
            >
                {tarea.text}
            </span>
            <button
                type="button"
                aria-label={`Eliminar ${tarea.text}`}
                onClick={() => onDelete(tarea.id)}
            >
                Eliminar
            </button>
        </li>
    )
}
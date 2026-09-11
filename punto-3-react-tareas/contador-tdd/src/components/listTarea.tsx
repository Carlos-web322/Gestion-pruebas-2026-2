import type { Tarea } from "../types/Tarea"
import { TareaItem } from "./tareaItem"


interface prop {
    tareas: Tarea[],
    onUpdate: (id: string) => void,
    onDelete: (id: string) => void
}

const ListTarea = ({ tareas, onDelete, onUpdate }: prop) => {
    if ( tareas.length === 0 ) {
        return <p>No hay tareas</p>
    }
    return (
        <ul>
            { tareas.map(( tarea ) => (
                <TareaItem
                    key={ tarea.id }
                    tarea={ tarea }
                    onUpdate = { onUpdate }
                    onDelete = { onDelete }
                />
            ))}
        </ul>
    )
}

export default ListTarea

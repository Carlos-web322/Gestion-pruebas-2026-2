import { useEffect, useState } from 'react'
import type { Tarea } from '../types/Tarea'
import { getTarea, saveTarea } from '../utils/storage'

export function useTarea() {
  const [tareas, setTarea] = useState<Tarea[]>(() => getTarea())

  useEffect(() => {
    saveTarea(tareas)
  }, [tareas])

  function addTarea(text: string) {
    const trimmed = text.trim()
    if (!trimmed) return

    const newTarea: Tarea = {
      id: crypto.randomUUID(),
      text: trimmed,
      estado: false,
    }

    setTarea((prev) => [...prev, newTarea])
  }

  function updateTarea(id: string) {
    setTarea((prev) =>
      prev.map((tarea) =>
        tarea.id === id ? { ...tarea, estado: !tarea.estado } : tarea
      )
    )
  }

  function deleteTarea(id: string) {
  setTarea((prev) => prev.filter((todo) => todo.id !== id))
}

  return { tareas, addTarea, updateTarea, deleteTarea}
}
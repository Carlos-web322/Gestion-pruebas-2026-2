import { useState } from 'react'
import type { Todo } from '../types/Todos' 

export function useTodos() {
  const [todos, setTodos] = useState<Todo[]>([])

  function addTodo(text: string) {
    const trimmed = text.trim()
    if (!trimmed) return

    const newTodo: Todo = {
      id: crypto.randomUUID(),
      text: trimmed,
      estado: false,
    }

    setTodos((prev) => [...prev, newTodo])
  }

  return { todos, addTodo }
}
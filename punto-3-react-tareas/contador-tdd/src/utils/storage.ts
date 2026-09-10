import type { Todo } from "../types/Todos";

const STORAGE_KEY = 'todos';

export function getTodos() : Todo[] {
    const raw = localStorage.getItem(STORAGE_KEY)
    if(!raw) return []
    return JSON.parse(raw)
}

export function saveTodos(todos: Todo[]): void {
    localStorage.setItem(STORAGE_KEY, JSON.stringify(todos))
}
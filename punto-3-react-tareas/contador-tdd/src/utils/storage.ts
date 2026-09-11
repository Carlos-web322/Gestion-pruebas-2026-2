import type { Tarea } from "../types/Tarea";

const STORAGE_KEY = 'tarea';

export function getTarea() : Tarea[] {
    const raw = localStorage.getItem(STORAGE_KEY)
    if(!raw) return []
    return JSON.parse(raw)
}

export function saveTarea(tarea: Tarea[]): void {
    localStorage.setItem(STORAGE_KEY, JSON.stringify(tarea))
}
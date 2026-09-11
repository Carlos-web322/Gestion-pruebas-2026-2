import type { Tarea } from '../../types/Tarea';
import { getTarea, saveTarea } from '../storage';

describe('storage', () => {
  beforeEach(() => {
    localStorage.clear()
  })

  it('devuelve una lista vacía si no hay tareas guardadas', () => {
    expect(getTarea()).toEqual([])
  })

  it('guarda tareas y luego las recupera correctamente', () => {
    const todos: Tarea[] = [
      { id: '1', text: 'Aprender TDD', estado: false },
      { id: '2', text: 'Tomar café', estado: true },
    ]

    saveTarea(todos)

    expect(getTarea()).toEqual(todos)
  })
})
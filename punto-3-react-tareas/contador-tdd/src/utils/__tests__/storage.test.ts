import type { Todo } from '../../types/Todos';
import { getTodos, saveTodos } from '../storage';

describe('storage', () => {
  beforeEach(() => {
    localStorage.clear()
  })

  it('devuelve una lista vacía si no hay tareas guardadas', () => {
    expect(getTodos()).toEqual([])
  })

  it('guarda tareas y luego las recupera correctamente', () => {
    const todos: Todo[] = [
      { id: '1', text: 'Aprender TDD', estado: false },
      { id: '2', text: 'Tomar café', estado: true },
    ]

    saveTodos(todos)

    expect(getTodos()).toEqual(todos)
  })
})
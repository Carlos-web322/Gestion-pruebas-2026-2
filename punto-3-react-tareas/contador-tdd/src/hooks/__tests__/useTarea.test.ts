import {renderHook, act} from '@testing-library/react'
import { useTarea } from '../useTarea'
import type { Tarea } from '../../types/Tarea'
import { getTarea, saveTarea } from '../../utils/storage'

describe('useTarea prueba unitaria al Hook', () => {
    beforeEach( () => {
        localStorage.clear()
    })

    it('no agrega una tarea si el texto esta vacio', () => {
        const { result } = renderHook( () => useTarea ())

        act ( () => {
            result.current.addTarea('   ')
        })

        expect( result.current.tareas).toHaveLength(0)
    })

    it('agrega una tarea correctamente cuando el texto es válido', () => {
    const { result } = renderHook(() => useTarea())

    act(() => {
      result.current.addTarea('Aprender TDD')
    })

    expect(result.current.tareas).toHaveLength(1)
    expect(result.current.tareas[0]).toMatchObject({
      text: 'Aprender TDD',
      estado: false,
    })
    expect(result.current.tareas[0].id).toBeDefined()
  })
})

describe(' useTarea || Persistencia en localStorage', ( ) => {
  beforeEach ( () => {
    localStorage.clear()
  })

  it('Carga las tareas existentes en localStorage al iniciar', () => {
    const existentes: Tarea[] = [
      {id: '1', text: 'Tarea cargada', estado: false},
    ]
    saveTarea(existentes)

    const { result } = renderHook(() => useTarea())

    expect(result.current.tareas).toEqual(existentes)
  })

  it('Persiste en localStorage al agregar una nueva tarea', () => {
    const {result} = renderHook( () => useTarea() )

    act( () => {
      result.current.addTarea('Nueva tarea')
    })

    const guardadas = getTarea()
    expect(guardadas).toHaveLength(1)
    expect(guardadas[0].text).toBe('Nueva tarea')

  })
})

describe('useTarea - updateTodo', () => {
  beforeEach(() => {
    localStorage.clear()
  })

  it('alterna el estado done de una tarea existente', () => {
    const { result } = renderHook(() => useTarea())

    act(() => {
      result.current.addTarea('Tarea a togglear')
    })

    const id = result.current.tareas[0].id

    act(() => {
      result.current.updateTarea(id)
    })

    expect(result.current.tareas[0].estado).toBe(true)

    act(() => {
      result.current.updateTarea(id)
    })

    expect(result.current.tareas[0].estado).toBe(false)
  })

  it('no afecta otras tareas al togglear una', () => {
    const { result } = renderHook(() => useTarea())

    act(() => {
      result.current.addTarea('Tarea A')
      result.current.addTarea('Tarea B')
    })

    const idA = result.current.tareas[0].id

    act(() => {
      result.current.updateTarea(idA)
    })

    expect(result.current.tareas[0].estado).toBe(true)
    expect(result.current.tareas[1].estado).toBe(false)
  })
})

describe('useTarea - deleteTarea', () => {
  beforeEach(() => {
    localStorage.clear()
  })

  it('elimina una tarea por su id', () => {
    const { result } = renderHook(() => useTarea())

    act(() => {
      result.current.addTarea('Tarea a eliminar')
    })

    const id = result.current.tareas[0].id

    act(() => {
      result.current.deleteTarea(id)
    })

    expect(result.current.tareas).toHaveLength(0)
  })

  it('solo elimina la tarea indicada, deja el resto intacto', () => {
    const { result } = renderHook(() => useTarea())

    act(() => {
      result.current.addTarea('Tarea A')
      result.current.addTarea('Tarea B')
    })

    const idA = result.current.tareas[0].id

    act(() => {
      result.current.deleteTarea(idA)
    })

    expect(result.current.tareas).toHaveLength(1)
    expect(result.current.tareas[0].text).toBe('Tarea B')
  })
})
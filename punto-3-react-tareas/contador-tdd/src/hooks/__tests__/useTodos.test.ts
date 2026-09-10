import {renderHook, act} from '@testing-library/react'
import { useTodos } from '../useTodos'

describe('useTodos', () => {
    beforeEach( () => {
        localStorage.clear()
    })

    it('no agrega una tarea si el texto esta vacio', () => {
        const { result } = renderHook( () => useTodos ())

        act ( () => {
            result.current.addTodo('   ')
        })

        expect( result.current.todos).toHaveLength(0)
    })

    it('agrega una tarea correctamente cuando el texto es válido', () => {
    const { result } = renderHook(() => useTodos())

    act(() => {
      result.current.addTodo('Aprender TDD')
    })

    expect(result.current.todos).toHaveLength(1)
    expect(result.current.todos[0]).toMatchObject({
      text: 'Aprender TDD',
      estado: false,
    })
    expect(result.current.todos[0].id).toBeDefined()
  })
})
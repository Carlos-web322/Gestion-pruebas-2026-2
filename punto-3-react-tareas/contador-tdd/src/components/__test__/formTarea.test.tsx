import '@testing-library/jest-dom/vitest'
import { render, screen } from "@testing-library/react"
import userEvent from '@testing-library/user-event'
import { FormTarea } from '../formTarea'

describe('Formulario de tarea', () => {
    it('No llama a onAdd si el input esta vacio', async () => {
        const onAdd = vi.fn()
        const user = userEvent.setup()

        render(<FormTarea onAdd={onAdd} />)

        await user.click(screen.getByRole('button', { name: /agregar/i }))

        expect(onAdd).not.toHaveBeenCalled()
    })

    it('llama a onAdd con el texto ingresado y limpia el input', async () => {
    const onAdd = vi.fn()
    const user = userEvent.setup()

    render(<FormTarea onAdd={onAdd} />)

    const input = screen.getByLabelText(/nueva tarea/i)
    await user.type( input, 'Comprar leche')
    await user.click( screen.getByRole('button', { name: /agregar/i }))

    expect(onAdd).toHaveBeenCalledWith('Comprar leche')
    expect(input).toHaveValue('')
    })
})
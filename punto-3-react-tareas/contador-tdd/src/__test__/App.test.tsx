import { render, screen } from "@testing-library/react"
import userEvent from "@testing-library/user-event"
import App from "../App"

describe(' Prueba end to end - integracion del CRUD', () => {
    beforeEach(() => {
        localStorage.clear()
    })

    it('agrega, marca como completada y elimina una tarea', async () => {
        const user = userEvent.setup()
        render(<App />)

        // Agregar
        const input = screen.getByLabelText(/nueva tarea/i)
        await user.type(input, 'Estudiar TDD')
        await user.click(screen.getByRole('button', { name: /agregar/i }))

        expect(screen.getByText('Estudiar TDD')).toBeInTheDocument()

        // Marcar como completada
        const checkbox = screen.getByRole('checkbox', { name: /estudiar tdd/i })
        await user.click(checkbox)
        expect(checkbox).toBeChecked()

        // Eliminar
        await user.click(
            screen.getByRole('button', { name: /eliminar estudiar tdd/i })
        )
        expect(screen.queryByText('Estudiar TDD')).not.toBeInTheDocument()
    })

    it('no permite agregar una tarea con input vacío', async () => {
        const user = userEvent.setup()
        render(<App />)

        await user.click(screen.getByRole('button', { name: /agregar/i }))

        expect(screen.getByText(/no hay tareas/i)).toBeInTheDocument()
    })
})


import { render, screen } from "@testing-library/react";
import type { Tarea } from "../../types/Tarea";
import ListTarea from "../listTarea";

const tareas: Tarea[] = [
    { id: '1', text: 'Tarea 1', estado: false },
    { id: '2', text: 'Tarea 2', estado: false },
]

describe('Lista de tareas: ', () => {
    it('renderiza una tarea por cada elemento del array', () => {
        render(<ListTarea tareas = { tareas } onUpdate={ vi.fn()} onDelete={vi.fn()} />)

        expect(screen.getAllByRole('listitem')).toHaveLength(2)
        expect(screen.getByText('Tarea 1')).toBeInTheDocument()
        expect(screen.getByText('Tarea 2')).toBeInTheDocument()
    })

    it('muestra un mensaje cuando no hay tareas', () => {
        render(<ListTarea tareas = {[]} onUpdate={ vi.fn() } onDelete = { vi.fn() } />)

        expect(screen.getByText(/no hay tareas/i)).toBeInTheDocument()
    })
})
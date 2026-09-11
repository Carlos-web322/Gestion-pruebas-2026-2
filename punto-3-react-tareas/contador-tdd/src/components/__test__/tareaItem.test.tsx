import userEvent from "@testing-library/user-event";
import type { Tarea } from "../../types/Tarea";
import { render, screen } from "@testing-library/react";
import { TareaItem } from "../tareaItem";


const tarea: Tarea = { id: '1', text: 'Comprar pan', estado: false }

describe('TareaItem', () => {
  it( 'muestra el texto de la tarea', () => {
    render(<TareaItem tarea={ tarea } onUpdate = { vi.fn() } onDelete={vi.fn()} />)

    expect(screen.getByText('Comprar pan')).toBeInTheDocument()
  })

  it( 'el checkbox refleja el estado', () => {
    render(
      <TareaItem
        tarea={{ ...tarea, estado: true }}
        onUpdate = { vi.fn() }
        onDelete={vi.fn()}
      />
    )

    const checkbox = screen.getByRole( 'checkbox', { name: /comprar pan/i })
    expect( checkbox ).toBeChecked()
  })

  it( 'llama a onUpdate con el id al hacer click en el checkbox', async () => {
    const onUpdate   =  vi.fn( )
    const user = userEvent.setup()

    render(<TareaItem tarea={tarea} onUpdate = { onUpdate }   onDelete={ vi.fn() } />)

    await user.click( screen.getByRole( 'checkbox', { name: /comprar pan/i }))

    expect( onUpdate ) .toHaveBeenCalledWith('1')
  })

  it( 'llama a onDelete con el id al hacer click en eliminar', async () => {
    const onDelete = vi.fn()
    const user = userEvent.setup()

    render( <TareaItem tarea={ tarea } onUpdate = { vi.fn() } onDelete ={ onDelete } />)

    await user.click(
      screen.getByRole( 'button', { name: /eliminar comprar pan/i })
    )

    expect( onDelete ).toHaveBeenCalledWith('1')
  })
})
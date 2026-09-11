import userEvent from "@testing-library/user-event";
import type { Tarea } from "../../types/Tarea";
import { render, screen } from "@testing-library/react";


const tarea: Tarea = { id: '1', text: 'Comprar pan', estado: false }

describe('tareaItem', () => {
  it( 'muestra el texto de la tarea', () => {
    render(<tareaItem tarea={ tarea } onUpdate = { vi.fn() } onDelete={vi.fn()} />)

    expect(screen.getByText('Comprar pan')).toBeInTheDocument()
  })

  it( 'el checkbox refleja el estado done', () => {
    render(
      <tareaItem
        tarea={{ ...tarea, done: true }}
        onUpdate = { vi.fn() }
        onDelete={vi.fn()}
      />
    )

    const checkbox = screen.getByRole( 'checkbox', { name: /comprar pan/i })
    expect( checkbox ).toBeChecked()
  })

  it( 'llama a onUpdate   c on el i d al hacer click en el checkbox', async () => {
    const onUpdate   =  vi.fn( )
    const user = userEvent.setup()

    render(<tareaItem tarea={tarea} onUpdate = { onUpdate }   onDelete={ vi.fn() } />)

    await user.click( screen.getByRole( 'checkbox', { name: /comprar pan/i }))

    expect( onUpdate ) .toHaveBeenCalledWith('1')
  })

  it( 'llama a onDelete con el id al hacer click en eliminar', async () => {
    const onDelete = vi.fn()
    const user = userEvent.setup()

    render( <tareaItem tarea={ tarea } onUpdate = { vi.fn() } onDelete ={ onDelete } />)

    await user.click(
      screen.getByRole( 'button', { name: /eliminar comprar pan/i })
    )

    expect( onDelete ).toHaveBeenCalledWith('1')
  })
})
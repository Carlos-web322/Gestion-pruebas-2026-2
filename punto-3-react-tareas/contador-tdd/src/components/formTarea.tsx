import { useState, type FormEvent } from "react"


interface TareaProps {
    onAdd: (text: string) => void
}

export function FormTarea({ onAdd }: TareaProps) {

    const [text, setText] = useState('')

    function handleSubmit(e: FormEvent) {
        e.preventDefault()
        if (!text.trim()) return

        onAdd(text)
        setText('')
    }

    return (
        <form onSubmit={handleSubmit}>
            <label htmlFor="new-todo">Nueva tarea</label>
            <input
                id="new-todo"
                type="text"
                value={text}
                onChange={(e) => setText(e.target.value)}
            />
            <button type="submit">Agregar</button>
        </form>
    )
}
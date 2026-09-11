// import { useState } from 'react'
import './App.css'
import { FormTarea } from './components/formTarea'
import ListTarea from './components/listTarea'
import { useTarea } from './hooks/useTarea'

function App() {

  const { tareas, addTarea, updateTarea, deleteTarea} = useTarea()

  return (
    <>
      <FormTarea onAdd = { addTarea }/>
      <ListTarea tareas = { tareas } onUpdate = { updateTarea } onDelete={ deleteTarea }/>
    </>
  )
}

export default App

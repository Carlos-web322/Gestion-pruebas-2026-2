// import { useState } from 'react'
import './App.css'
import { FormTarea } from './components/formTarea'

function App() {

  const Prop = ( texto: string ) => {
    console.log('Entro: ', texto)
  }

  return (
    <>
      <FormTarea onAdd={Prop}/>
    </>
  )
}

export default App

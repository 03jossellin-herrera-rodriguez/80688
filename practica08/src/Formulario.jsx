import MyFieldSet from './MyFieldSet.jsx'

function Formulario(props){
    return{
        <>
        <form action>
        <MiFieldSet titulo="Datos Personales" txt1="Nombre" txt2="Password"/>
        <MiFieldSet titulo="Datos Generales" txt1="Direccion" txt2="Correo"/>
        <input type="submit" value="Enviar datos"/>
        </form>
        </>
    }
}
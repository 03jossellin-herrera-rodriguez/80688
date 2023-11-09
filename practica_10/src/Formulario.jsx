/*import { TextField, Button, Box } from "@muy/material";*/
// Por ejemplo, puedes usar los componentes locales de Material-UI
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import Box from "@mui/material/Box";
//nuevas
import { useState } from "react"
import axios from "axios";


    function Formulario(props) {

        const[Cargando, setCargando] = useState (false)
        const [datosFormulario, setDatosFormulario] = useState({ nombre: '', password: '' });

  

        const hacerPeticion= async () => {
            try{
                const response = await axios.get('http://localhost:4567/tipoUsuario')
                console.log("hacerPeticion", response)
                return response.data
            }catch(error){
                throw error
            }
        }

        const cambiosFormulario = (evento) =>{
            const {name, value} = evento.target
            setDatosFormulario({ ...datosFormulario, [name] : value})
        }

        const procesaFormulario = async (evento) =>{
            /*previene que se ejecute el submit para el boton*/
                evento.preventDefault() 
            console.log("datos recuperados del form", datosFormulario)
            setCargando(true)
            try{
                const response= await hacerPeticion()
                console.log("procesarFormulario", response)
                setCargando(false) 
                if(datosFormulario.nombre === response.tipousuario){
                    console.log("Usuario correcto")
                }else{
                    console.log("Error, usuario incorrecto")
                }
            }catch(error){
                console.log("error", error);
                setCargando(false)
            }

        }


        return(
            <>
                <h1>Inicio de sesion</h1>
                <form onSubmit={ procesaFormulario }>
                <Box m={5}>
                    <TextField label="Nombre:" variant="outlined" fullWidth onChange={cambiosFormulario} name="nombre" value={datosFormulario.nombre}></TextField>
                </Box>
                <Box m={5}>
                    <TextField label="Contraseña:" variant="outlined" fullWidth onChange={cambiosFormulario} name="password" value={datosFormulario.password} ></TextField>
                </Box>
                <Box m={5}>
                    <Button variant="contained" type="submit" color="primary" fullWidth disabled={Cargando}>Iniciar sesión</Button>
                </Box>
                </form>
            </>
        ) 
}

export default Formulario
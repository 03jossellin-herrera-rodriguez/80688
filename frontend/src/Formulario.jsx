import { TextField, Button, Box } from "@mui/material";

//nuevas
import { useState } from "react"
import axios from "axios";


    function Formulario(props) {

        const[Cargando, setCargando] = useState (false)
        const [datosFormulario, setDatosFormulario] = useState({ nombre: '', correo: '', tel: '' });

/* recien comentado */
         /* const hacerPeticion= async () => {
            try{
                const response = await axios.post('http://localhost:4567', {datosFormulario})
                console.log("hacerPeticion", response)
                return response.data
            }catch(error){
                throw error;
            }
        }; */

        const hacerPeticion = async () => {
            try {
                const response = await axios.post('http://localhost:4567', datosFormulario);
                console.log("hacerPeticion", response);
                return response.data;
            } catch (error) {
                throw error;
            }
        };
        
        
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
            //ya 
        const registrar = async () => {
            console.log("Boton registrar presionado");
            try {
                const response = await axios.post('http://localhost:4567/registrar', datosFormulario);
                console.log("Registro exitoso", response.data);
                alert("Registro exitoso");
            } catch (error) {
                console.error("Error al registrar", error);
                alert("Error al registrar");
            }
        }

        const modificar = async () => {
            console.log("Botón Modificar presionado");
            try {
                const response = await axios.put('http://localhost:4567/modificar', datosFormulario);
                console.log("Modificacion exitosa", response.data);
                alert("Modificacion exitosa");
            } catch (error) {
                console.error("Error al modificar", error);
            }
        }
        
        const consultar = async () => {
            console.log("Boton Consultar presionado");
            try {
                const response = await axios.get('http://localhost:4567/consultar', datosFormulario);
                console.log("Consulta exitosa", response.data);
                alert("Datos consultados exitosamente");
            } catch (error) {
                console.error("Error al consultar", error);
            }
        }
        
        const eliminar = async () => {
            console.log("Botón eliminar presionado");
            try {
                const response = await axios.delete('http://localhost:4567/eliminar', { params: datosFormulario });
                console.log("Eliminación exitosa", response.data);
            } catch (error) {
                console.error("Error al eliminar", error);
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
                    <TextField label="Correo:" variant="outlined" fullWidth onChange={cambiosFormulario} name="correo" value={datosFormulario.correo} ></TextField>
                </Box>
                <Box m={5}>
                    <TextField label="Tel:" variant="outlined" fullWidth onChange={cambiosFormulario} name="tel" value={datosFormulario.tel} ></TextField>
                </Box>
                
                
                
                <Box m={5}>
                    <Button variant="contained" color="primary" fullWidth disabled={Cargando} onClick={registrar}>Registrar</Button>
                </Box>
                <Box m={5}>
                    <Button variant="contained" color="primary" fullWidth disabled={Cargando} onClick={modificar}>Modificar</Button>
                </Box>
                <Box m={5}>
                    <Button variant="contained" color="primary" fullWidth disabled={Cargando} onClick={consultar}>Consular</Button>
                </Box>
                <Box m={5}>
                    <Button variant="contained" color="primary" fullWidth disabled={Cargando} onClick={eliminar}>Eliminar</Button>
                </Box> 
                
                </form>
            </>
        ) 
}

export default Formulario
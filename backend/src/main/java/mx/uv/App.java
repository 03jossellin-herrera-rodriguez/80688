package mx.uv;
import static spark.Spark.*;
import com.google.gson.*;
import java.util.ArrayList;
import java.util.List;

public class App {
    private static List<Usuario> usuarios = new ArrayList<>();

    public static void main(String[] args) {
        //codigo que nos dijo el profe para permitir solicitudes de cualquier orign en el servidor
        //solicitudes de cualquier metodo de los q ya vimos
        options("/*", (request, response) -> {
            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }
            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }
            return "OK";
        });

        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));
        //aca termina



        // Resto del código Spark
        get("/", 
            (request, response) -> "<h1>Bienvenido al backend</h1>"
        );

        
        post("/registrar", (request, response) -> {
            String nombre = request.queryParams("nombre");
            String correo = request.queryParams("correo");
            String tel = request.queryParams("tel");
            //instancia del usuario con sus 3 parametros para despues meterla al arraylist q se crep
            Usuario nuevoUsuario = new Usuario(nombre, correo, tel);
            usuarios.add(nuevoUsuario);

            JsonObject resp = new JsonObject();
            resp.addProperty("registrar", "usuario");
            resp.addProperty("mensaje", "Usuario registrado exitosamente");
            return resp;
        });

        /* get("/consultar", (request, response) -> {
            String nombre = request.queryParams("nombre");

            Usuario usuarioConsultado = consultarUsuarioPorNombre(nombre);

            JsonObject resp = new JsonObject();
            resp.addProperty("consultar", "usuario");
            
            if (usuarioConsultado != null) {
                resp.addProperty("nombre", usuarioConsultado.getNombre());
                resp.addProperty("correo", usuarioConsultado.getCorreo());
                resp.addProperty("tel", usuarioConsultado.getTel());
                //puesto reciente
                resp.addProperty("mensaje", "Usuario consultado");
            } else {
                resp.addProperty("mensaje", "Usuario no encontrado");
            }
            return resp;
        }); */
        get("/consultar", (request, response) -> {
            try {
                String nombre = request.queryParams("nombre");
                //aniadido
                System.out.println("Iniciando consultarUsuarioPorNombre, nombre: " + nombre);

                Usuario usuarioConsultado = consultarUsuarioPorNombre(nombre);
        
                JsonObject resp = new JsonObject();
                resp.addProperty("consultar", "usuario");
        
                if (usuarioConsultado != null) {
                    resp.addProperty("nombre", usuarioConsultado.getNombre());
                    resp.addProperty("correo", usuarioConsultado.getCorreo());
                    resp.addProperty("tel", usuarioConsultado.getTel());
                    resp.addProperty("mensaje", "Usuario consultado");
                } else {
                    resp.addProperty("mensaje", "Usuario no encontrado");
                }
                //aniadido
                System.out.println("Finalizando consultarUsuarioPorNombre, usuario consultado: " + usuarioConsultado);

                return resp;
            } catch (Exception e) {
                e.printStackTrace();
                response.status(500); // Establece el código de estado 500 en caso de error
                return "Error interno del servidor: " + e.getMessage();
            }
        });
        
      
        put("/modificar", (request, response) -> {
            String nombre = request.queryParams("nombre");
            String correo = request.queryParams("correo");
            String tel = request.queryParams("tel");

            Usuario usuarioModificado = modificarUsuario(nombre, correo, tel);

            JsonObject resp = new JsonObject();
            resp.addProperty("modificar", "usuario");
            if (usuarioModificado != null) {
                resp.addProperty("mensaje", "Usuario modificado exitosamente");
            } else {
                resp.addProperty("mensaje", "Usuario no encontrado");
            }
            return resp;
        });

       
        delete("/eliminar", (request, response) -> {
            String nombre = request.queryParams("nombre");

            boolean eliminado = eliminarUsuario(nombre);

            JsonObject resp = new JsonObject();
            resp.addProperty("eliminar", "usuario");
            if (eliminado) {
                resp.addProperty("mensaje", "Usuario eliminado exitosamente");
            } else {
                resp.addProperty("mensaje", "Usuario no encontrado");
            }
            return resp;
        });
    }

   /*  private static Usuario consultarUsuarioPorNombre(String nombre) {
        try{
            System.out.println("Lista de usuarios para consultar: " + usuarios);
            //for each que buscara en la lista de usuarios
            for (Usuario usuario : usuarios) {
            //usuario.getNombre().equals(nombre)
                if (usuario != null && usuario.getNombre().equals(nombre)) {
                    return usuario; 
                }
            }
            return null;
        }catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        
        
    }    */
    private static Usuario consultarUsuarioPorNombre(String nombre) {
        try {
            System.out.println("Iniciando consultarUsuarioPorNombre, nombre: " + nombre);
            System.out.println("Lista de usuarios para consultar: " + usuarios);
    
            // for each que buscará en la lista de usuarios
            for (Usuario usuario : usuarios) {
                if (usuario != null && usuario.getNombre().equals(nombre)) {
                    System.out.println("Usuario encontrado: " + usuario.getNombre());
                    return usuario;
                }
            }
    
            System.out.println("Usuario no encontrado para consultar: " + nombre);
            return null;
        } catch (Exception e) {
            System.err.println("Error en consultarUsuarioPorNombre: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    

   /*  private static Usuario modificarUsuario(String nombre, String correo, String tel) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombre)) {
                usuario.setCorreo(correo);
                usuario.setTel(tel);
                return usuario;
            }
        }
        return null;
    } */

    /* private static Usuario modificarUsuario(String nombre, String correo, String tel) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombre)) {
                Usuario usuarioModificado = new Usuario(usuario.getNombre(), correo, tel);
                usuarios.remove(usuario);  // Eliminar el usuario existente
                usuarios.add(usuarioModificado);  // Agregar el usuario modificado
               //aniadido
                System.err.println("Usuario modificado: " + usuarioModificado.getNombre());
                return usuarioModificado;
            }
        }
        //aniadido
        System.err.println("Usuario no encontrado para modificar: " + nombre);
        return null;
    } */
    /* private static synchronized Usuario modificarUsuario(String nombre, String correo, String tel) {
        try {
            System.out.println("Lista de usuarios antes de la modificación: " + usuarios);
            for (Usuario usuario : usuarios) {
                if (usuario != null && usuario.getNombre().equals(nombre)) {
                    Usuario usuarioModificado = new Usuario(usuario.getNombre(), correo, tel);
                    usuarios.remove(usuario);  // Eliminar el usuario existente
                    usuarios.add(usuarioModificado);  // Agregar el usuario modificado
                    System.err.println("Usuario modificado: " + usuarioModificado.getNombre());
                    return usuarioModificado;
                }
            }
            System.out.println("Usuario no encontrado para modificar: " + nombre);
            return null;
        } catch (Exception e) {
            System.err.println("Error en modificarUsuario: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    } */
    private static synchronized Usuario modificarUsuario(String nombre, String correo, String tel) {
        try {
            System.out.println("Lista de usuarios antes de la modificación: " + usuarios);
            for (int i = usuarios.size() - 1; i >= 0; i--) {
                Usuario usuario = usuarios.get(i);
                if (usuario != null && usuario.getNombre().equals(nombre)) {
                    Usuario usuarioModificado = new Usuario(nombre, correo, tel);
                    usuarios.set(i, usuarioModificado); 
                    System.err.println("Usuario modificado: " + usuarioModificado.getNombre());
                    return usuarioModificado;
                }
            }
            System.out.println("Usuario no encontrado para modificar: " + nombre);
            return null;
        } catch (Exception e) {
            System.err.println("Error en modificarUsuario: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    


    private static boolean eliminarUsuario(String nombre) {
        try{
            Usuario usuarioAEliminar = null;
        //usuario representa cada elemento de la lista durante su recorrimiento
            for (Usuario usuario : usuarios) {
                if (usuario != null && usuario.getNombre().equals(nombre)) {
                    usuarioAEliminar = usuario;
                    break;
                }
            }

            if (usuarioAEliminar != null) {
                usuarios.remove(usuarioAEliminar);
                return true;
            } else {
                return false;
            }

        }catch(Exception e) {
            System.err.println("Error en eliminarUsuario: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
        
    }
    
    //clase usuario
    private static class Usuario {
        private String nombre;
        private String correo;
        private String tel;

        public Usuario(String nombre, String correo, String tel) {
            this.nombre = nombre;
            this.correo = correo;
            this.tel = tel;
        }

        public String getNombre() {
            return nombre;
        }

        public String getCorreo() {
            return correo;
        }

        public String getTel() {
            return tel;
        }

        public void setNombre(String nombre){
            this.nombre=nombre;
        }
        public void setCorreo(String correo) {
            this.correo = correo;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }
    }
}

package mx.uv;

import static spark.Spark.*;
import com.google.gson.*;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        options("/*",(request,response)->{
            String accessControlRequestHeaders=request.headers("Access-Control-Request-Headers");
            if(accessControlRequestHeaders!=null){
                response.header("Access-Control-Allow-Headers",accessControlRequestHeaders);
            }
            String accessControlRequestMethod=request.headers("Access-Control-Request-Method");
            if(accessControlRequestMethod!=null){
                response.header("Access-Control-Allow-Methods",accessControlRequestMethod);
            }
            return "OK";
        });
        before((request,response)->response.header("Access-Control-Allow-Origin","*"));

        // Resto del código Spark
        get("/",
            (request, response) -> "<h1>Bienvenido</h1>"
        );
        get("/hola",
            (request, response) -> "<h1>Hola mundo</h1>"
        );
        get("/adios",
            (request, response) -> "<h1>Adios mundo</h1>"
        );
        get("/fin",
            (request, response) -> "<h1>Fin mundo</h1>"
        );

        get("/alumnos",
            (request, response) -> "{alumno:john, matricula: 80688, carrera:TC}"
        );

        post("/alumno",
            (request, response) -> {
                System.out.println(request.contentLength());
                System.out.println(request.contentType());          
                System.out.println(request.contextPath());

                return "profesor"+request.queryParams("paterno");
            }
        );

        get("/tipoUsuario", (request, response) -> {
            JsonObject respuesta = new JsonObject();
            respuesta.addProperty("tipousuario", "profesor");
            respuesta.addProperty("nombre", request.queryParams("nombre"));
            respuesta.addProperty("materno", request.queryParams("materno"));
            respuesta.addProperty("paterno", request.queryParams("materno"));
            
            return respuesta;
        });
    }
}

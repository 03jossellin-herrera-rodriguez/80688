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
            (request, response) -> "<h1>Bienvenido al backend</h1>"
        );

        get("/registrar", (request, response) -> {
            JsonObject resp = new JsonObject();
            resp.addProperty("registrar", "usuario");
            resp.addProperty("nombre", request.queryParams("nombre"));
            resp.addProperty("correo", request.queryParams("correo"));
            resp.addProperty("tel", request.queryParams("tel"));
            return resp;
        });
 
        get("/consultar", (request, response) -> {
            JsonObject resp = new JsonObject();
            resp.addProperty("consultar", "usuario");
            resp.addProperty("nombre", request.queryParams("nombre"));
            resp.addProperty("correo", request.queryParams("correo"));
            resp.addProperty("tel", request.queryParams("tel"));
            return resp;
        });

        get("/modificar", (request, response) -> {
            JsonObject resp = new JsonObject();
            resp.addProperty("modificar", "usuario");
            resp.addProperty("nombre", request.queryParams("nombre"));
            resp.addProperty("correo", request.queryParams("correo"));
            resp.addProperty("tel", request.queryParams("tel"));
            return resp;
        });

        get("/registrar", (request, response) -> {
            JsonObject resp = new JsonObject();
            resp.addProperty("registrar", "usuario");
            resp.addProperty("nombre", request.queryParams("nombre"));
            resp.addProperty("correo", request.queryParams("correo"));
            resp.addProperty("tel", request.queryParams("tel"));
            return resp;
        });


    }
}

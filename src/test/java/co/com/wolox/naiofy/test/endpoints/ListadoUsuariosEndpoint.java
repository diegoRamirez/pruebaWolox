package co.com.wolox.naiofy.test.endpoints;

import co.com.wolox.naiofy.test.model.Usuario;
import co.com.wolox.naiofy.test.utilitys.Utilitys;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;



public class ListadoUsuariosEndpoint extends BaseEndpoints {
    private final String LISTAUSUARIOS_ENDPOINT_PATH = "users";

    Utilitys utilitys = new Utilitys();

    public ListadoUsuariosEndpoint() throws IOException {
        super();
    }

    public String getPath() {
        return this.LISTAUSUARIOS_ENDPOINT_PATH;
    }


    public Response goListadoUsuarios(RequestSpecification request) {
        String url = getBaseUrl() + this.getPath();
        Response response;
        response = sendRequest(request, BaseEndpoints.GET_REQUEST, url, null);
        response.then().log().all();
        return response;
    }

    public RequestSpecification getRequest() {
        RequestSpecification request = RestAssured.given();
        request.log().all();

        return request;
    }

    public void verifyFormat(Response r, Usuario usuario) {


        ArrayList<Object> nestedArray = (ArrayList<Object>)r.jsonPath().getList("page");

        for (int i = 0; i < nestedArray.size(); i++) {
            Map<String, String> map = (Map<String, String>) nestedArray.get(i);

            usuario.setFirstName(String.valueOf(map.get("first_name")));
            usuario.setLastName(String.valueOf(map.get("last_name")));
            usuario.setEmail(String.valueOf(map.get("email")));
            usuario.setRole(String.valueOf(map.get("role")));

            boolean booleanFirstName = utilitys.isNumeric(usuario.getFirstName());
            boolean booleanlastName = utilitys.isNumeric(usuario.getLastName());
            boolean booleanEmail = utilitys.isNumeric(usuario.getEmail());
            boolean booleanRole = utilitys.isNumeric(usuario.getRole());

            Assert.assertFalse("El campo firstName no tiene un formato correcto en el usuario: " + (i+1),booleanFirstName);
            Assert.assertFalse("El campo lastName no tiene un formato correcto en el usuario: " + (i+1),booleanlastName);
            Assert.assertFalse("El campo email no tiene un formato correcto en el usuario: "+ (i+1),booleanEmail);
            Assert.assertFalse("El campo role no tiene un formato correcto en el usuario: "+ (i+1),booleanRole);

        }
    }

    public void verifyUser(String tipoUsuario, Response r) {
        String role;
        ArrayList<Object> nestedArray = (ArrayList<Object>)r.jsonPath().getList("page");
        for (int i = 0; i < nestedArray.size(); i++) {
            Map<String, String> map = (Map<String, String>) nestedArray.get(i);
            role = String.valueOf(map.get("role"));
            boolean booleanRole = true;
            if (tipoUsuario.equals("Regular")){
                booleanRole = role.equals("regular");
            }
            if (tipoUsuario.equals("Admin")){
                booleanRole = (role.equals("admin") || role.equals("regular"));
            }

            Assert.assertTrue("Existe un error en el campo role",booleanRole);

        }
    }
}

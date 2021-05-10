package co.com.wolox.naiofy.test.endpoints;

import co.com.wolox.naiofy.test.utilitys.Utilitys;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListadoAlbumesEndpoint extends BaseEndpoints{
    private final String LISTA_ALBUMES_ENDPOINT_PATH = "albums";
    Utilitys utilitys = new Utilitys();

    public ListadoAlbumesEndpoint() throws IOException {
        super();
    }

    public String getPath() {
        return this.LISTA_ALBUMES_ENDPOINT_PATH;
    }


    public Response goListadoAlbumes(RequestSpecification request) {
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

    public void verifyFormat(Response r) {

        String user_id ;
        String id ;
        String title ;

        ArrayList<Object> nestedArray = (ArrayList<Object>)r.jsonPath().getList("");

        for (int i = 0; i < nestedArray.size(); i++) {
            Map<String, String> map = (Map<String, String>) nestedArray.get(i);
            user_id = String.valueOf(map.get("user_id"));
            id = String.valueOf(map.get("id"));
            title = String.valueOf(map.get("title"));

            boolean booleanUserId = utilitys.isNumeric(user_id);
            boolean booleanId = utilitys.isNumeric(id);
            boolean booleanTitle = utilitys.isNumeric(title);

            Assert.assertTrue("El campo user_id no tiene un formato correcto",booleanUserId);
            Assert.assertTrue("El campo id no tiene un formato correcto",booleanId);
            Assert.assertFalse("El campo email no tiene un formato correcto",booleanTitle);

        }
    }

    public String getIdAlbum(Response response) {
        String idAlbum;
        int tope;
        int indice;
        ArrayList<Object> nestedArray = (ArrayList<Object>)response.jsonPath().getList("");
        tope=nestedArray.size();
        indice = utilitys.generarIndice(tope);
        Map<String, String> map = (Map<String, String>) nestedArray.get(indice);
        idAlbum = String.valueOf(map.get("id"));
        return idAlbum;
    }

    public List<String> getIdAlbumes(Response response){
        List<String> listaIdAlbum = new ArrayList<String>();
        for (int i=0; i<5; i++){
            String idAlbum;
            int tope;
            int indice;
            ArrayList<Object> nestedArray = (ArrayList<Object>)response.jsonPath().getList("");
            tope=nestedArray.size();
            indice = utilitys.generarIndice(tope);
            Map<String, String> map = (Map<String, String>) nestedArray.get(indice);
            idAlbum = String.valueOf(map.get("id"));
            listaIdAlbum.add(idAlbum);
        }
        return listaIdAlbum;
    }
}

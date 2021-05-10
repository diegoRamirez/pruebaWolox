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


public class AlbumesCompradosEndpoint extends BaseEndpoints{
    private final String ALBUMES_COMPRADOS_ENDPOINT_PATH = "users/";
    private final String ALBUMES_COMPRADOS_ENDPOINT_PATH_2 = "/albums";
    Utilitys utilitys = new Utilitys();

    public AlbumesCompradosEndpoint() throws IOException {
        super();
    }

    public String getPath() {
        return this.ALBUMES_COMPRADOS_ENDPOINT_PATH;
    }
    public String getPath2() {
        return this.ALBUMES_COMPRADOS_ENDPOINT_PATH_2;
    }


    public Response goAlbumesComprados(RequestSpecification request,String idUsuario) {
        String url = getBaseUrl() + this.getPath() + idUsuario + this.getPath2();
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

        String user_id;
        String id;
        String title;

        ArrayList<Object> nestedArray = (ArrayList<Object>) r.jsonPath().getList("album" );

        for (int i = 0; i < nestedArray.size(); i++) {
            Map<String, String> map = (Map<String, String>) nestedArray.get(i);
            user_id = String.valueOf(map.get("user_id"));
            id = String.valueOf(map.get("id"));
            title = String.valueOf(map.get("title"));

            boolean booleanUserId = utilitys.isNumeric(user_id);
            boolean booleanId = utilitys.isNumeric(id);
            boolean booleanTitle = utilitys.isNumeric(title);

            Assert.assertTrue("El campo user_id no tiene un formato correcto", booleanUserId);
            Assert.assertTrue("El campo id no tiene un formato correcto", booleanId);
            Assert.assertFalse("El campo email no tiene un formato correcto", booleanTitle);
        }
    }

    public void verifyAlbumesComprados(Response response, List<String> listIdAlbum) {
        ArrayList<Object> nestedArray = (ArrayList<Object>) response.jsonPath().getList("album" );
        int contador = 0;
        boolean flag;
        if(listIdAlbum.size() == nestedArray.size()){
            for (int i=0; i<listIdAlbum.size();i++){
                if(nestedArray.contains(listIdAlbum.get(i)))
                {
                    contador++;
                }else
                    break;
            }
        }
        flag = (contador==listIdAlbum.size());
        Assert.assertTrue("No aparecen todos los albumes comprados por el usuario", flag);
    }
}

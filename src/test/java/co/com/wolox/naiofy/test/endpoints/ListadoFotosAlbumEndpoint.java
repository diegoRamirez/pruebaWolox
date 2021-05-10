package co.com.wolox.naiofy.test.endpoints;

import co.com.wolox.naiofy.test.utilitys.Utilitys;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class ListadoFotosAlbumEndpoint extends BaseEndpoints{
    private final String LISTA_FOTOS_ALBUMES_ENDPOINT_PATH = "albums/";
    private final String LISTA_FOTOS_ALBUMES_ENDPOINT_PATH_2 = "/photos";
    Utilitys utilitys = new Utilitys();

    public ListadoFotosAlbumEndpoint() throws IOException {
        super();
    }

    public String getPath() {
        return this.LISTA_FOTOS_ALBUMES_ENDPOINT_PATH;
    }
    public String getPath2() {
        return this.LISTA_FOTOS_ALBUMES_ENDPOINT_PATH_2;
    }


    public Response goListadoFotosAlbum(RequestSpecification request,String id) {
        String url = getBaseUrl() + this.getPath() + id + this.getPath2();
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

        String album_id ;
        String id ;
        String title ;
        String url;
        String thumbnail_url;

        ArrayList<Object> nestedArray = (ArrayList<Object>)r.jsonPath().getList("");

        for (int i = 0; i < nestedArray.size(); i++) {
            Map<String, String> map = (Map<String, String>) nestedArray.get(i);
            album_id = String.valueOf(map.get("album_id"));
            id = String.valueOf(map.get("id"));
            title = String.valueOf(map.get("title"));
            url = String.valueOf(map.get("url"));
            thumbnail_url = String.valueOf(map.get("thumbnail_url"));

            boolean booleanAlbumId = utilitys.isNumeric(album_id);
            boolean booleanId = utilitys.isNumeric(id);
            boolean booleanTitle = utilitys.isNumeric(title);
            boolean booleanUrl = utilitys.isNumeric(url);
            boolean booleanThumbnail_url = utilitys.isNumeric(thumbnail_url);

            Assert.assertTrue("El campo user_id no tiene un formato correcto",booleanAlbumId);
            Assert.assertTrue("El campo id no tiene un formato correcto",booleanId);
            Assert.assertFalse("El campo title no tiene un formato correcto",booleanTitle);
            Assert.assertFalse("El campo url no tiene un formato correcto",booleanUrl);
            Assert.assertFalse("El campo email no tiene un formato correcto",booleanThumbnail_url);

        }
    }
}

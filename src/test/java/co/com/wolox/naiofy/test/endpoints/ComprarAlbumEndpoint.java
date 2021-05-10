package co.com.wolox.naiofy.test.endpoints;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.IOException;

public class ComprarAlbumEndpoint extends BaseEndpoints{
    private final String COMPRAR_ALBUM_ENDPOINT_PATH = "albums/";

    public ComprarAlbumEndpoint() throws IOException {
        super();
    }

    public String getPath() {
        return this.COMPRAR_ALBUM_ENDPOINT_PATH;
    }


    public Response goComprarAlbum(RequestSpecification request, String idAlbum) {
        String url = getBaseUrl() + this.getPath() + idAlbum;
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
}

package co.com.wolox.naiofy.test.endpoints;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import java.io.IOException;
import static io.restassured.RestAssured.given;

public class InvalidarSesionEndpoint extends BaseEndpoints{
    private final String INVALIDAR_SESION_ENDPOINT_PATH = "users/sessions/invalidate_all";

    public InvalidarSesionEndpoint() throws IOException {
        super();
    }

    public String getPath() {
        return this.INVALIDAR_SESION_ENDPOINT_PATH;
    }


    public Response goInvalidarSesion(RequestSpecification request) {
        String url = getBaseUrl() + this.getPath();
        Response response ;
        response =sendRequest(request, BaseEndpoints.POST_REQUEST, url, null);

        response.then().log().all();
        return response;
    }

    public RequestSpecification getRequest() {
        RequestSpecification request = given();
        request.log().all();

        return request;
    }

    public void verifyResponseStatusValueRegular(Response response, int code) {
        int statusCode = response.getStatusCode();
        boolean flag = code == statusCode;
        Assert.assertFalse("Error: No se debe permitir invalidar sesion a un usuario regular",flag);
    }
}

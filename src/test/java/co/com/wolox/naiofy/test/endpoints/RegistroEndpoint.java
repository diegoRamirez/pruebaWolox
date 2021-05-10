package co.com.wolox.naiofy.test.endpoints;

import co.com.wolox.naiofy.test.model.Registro;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class RegistroEndpoint extends BaseEndpoints{

    private final String REGISTRO_ENDPOINT_PATH = "users";
    private Registro defaultRegistro;
    Properties properties = new Properties();

    public RegistroEndpoint() throws IOException {
        super();
        defaultRegistro = new Registro();
        properties.load(new FileReader("src/test/resources/configuration.properties"));
    }

    public String getPath() {
        return this.REGISTRO_ENDPOINT_PATH;
    }

    public Registro getDefaultRegistro() {
        return this.defaultRegistro;
    }

    public Response goRegistro(RequestSpecification request, Registro registro) {
        String url = getBaseUrl() + this.getPath();
        Response response;
        response =sendRequest(request, BaseEndpoints.POST_REQUEST, url, registro);
        response.then().log().all();
        defaultRegistro = registro;

        return response;
    }

    public RequestSpecification getRequest() {
        RequestSpecification request = RestAssured.given();
        request.log().all();

        return request;
    }



}

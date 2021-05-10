package co.com.wolox.naiofy.test.endpoints;

import co.com.wolox.naiofy.test.model.Login;
import co.com.wolox.naiofy.test.utilitys.Utilitys;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class LoginEndpoint extends BaseEndpoints {
    private final String LOGIN_ENDPOINT_PATH = "users/sessions";
    private Login defaultLogin;
    private String user_id = "";
    Properties properties = new Properties();
    Utilitys utilitys = new Utilitys();

    public LoginEndpoint(String tipo) throws IOException {
        super();
        defaultLogin = new Login(tipo);
        properties.load(new FileReader("src/test/resources/configuration.properties"));
    }

    public String getPath() {
        return this.LOGIN_ENDPOINT_PATH;
    }

    public Login getDefaultLogin() {
        return this.defaultLogin;
    }

    public Response goLogin(RequestSpecification request, Login login) {
        String url = getBaseUrl() + this.getPath();
        Response response;
        response =sendRequest(request, BaseEndpoints.POST_REQUEST, url, login);
        response.then().log().all();
        return response;
    }

    public RequestSpecification getRequest() {
        RequestSpecification request = RestAssured.given();
        request.log().all();

        return request;
    }

    public void verifyHeaderAuthorization(Response response){
        String headerAuthorization = response.getHeader("Authorization");
        boolean flag;
        if((headerAuthorization.isEmpty()) ||(headerAuthorization.equals(""))){
            flag = false;
        }else
            flag = true;
        assertTrue(flag);
    }

    public String getTokenAuth(Response response){
        String tokenAuthorization = response.getHeader("Authorization");
        return tokenAuthorization;
    }

    public void verifyUserId(String tipoCliente, String key, String val, Response r) {
        if (tipoCliente.equals("Admin"))
            val = properties.getProperty("admin_user_id");
        else
            val = properties.getProperty("regular_user_id");
        String keyValue = r.jsonPath().getString(key);
        assertThat(keyValue, is(val));
    }
    public RequestSpecification getRequestWithToken(String token) {
        RequestSpecification r = RestAssured.given();
        r.header("Authorization", token);
        return r;
    }
    public String getUserId(String tipoUsuario){
        if (tipoUsuario.equals("Admin"))
            user_id = properties.getProperty("admin_user_id");
        else
            user_id = properties.getProperty("regular_user_id");
        return user_id;
    }

    public String getUserId(Response response){
        user_id = response.jsonPath().getString("user_id");
        return user_id;
    }

    public void verifyFormat(Response response) {
        String user_id ;


        user_id = response.jsonPath().getString("user_id");
        boolean booleanUserId = utilitys.isNumeric(user_id);
        Assert.assertTrue("El campo user_id no tiene un formato correcto " ,booleanUserId);

    }
}


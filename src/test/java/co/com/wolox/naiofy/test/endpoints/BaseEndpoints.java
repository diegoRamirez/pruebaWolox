package co.com.wolox.naiofy.test.endpoints;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;
import java.util.Map;

import static io.restassured.config.EncoderConfig.encoderConfig;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;
import org.json.JSONObject;
import org.junit.Assert;


public class BaseEndpoints {

    public static final int GET_REQUEST = 0;
    public static final int POST_REQUEST = 1;
    public static final int DELETE_REQUEST = 2;
    public static final int PUT_REQUEST = 3;

    protected final String base_url = "https://nodejs-qa-training.herokuapp.com/";

    public BaseEndpoints() {
    }

    public void verifyResponseStatusValue(Response response, int expectedCode) {
        assertThat(response.getStatusCode(), is(expectedCode));
    }

    public String getBaseUrl() {
        return this.base_url;
    }


    public RequestSpecification getRequestWithJSONHeaders() {
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));

        RequestSpecification r = RestAssured.given();
        r.header("Content-Type", "application/json");
        return r;
    }


    protected JSONObject createJSONPayload(Object pojo) {
        return new JSONObject(pojo);
    }


    public void verifyErrors(Response r) {
        ArrayList<Object> nestedArray = (ArrayList<Object>)r.jsonPath().getList("errors");
        boolean flag = nestedArray.size()==3;
        Assert.assertTrue("No se retornaron todos los errores al tiempo",flag);
    }

    public void verifyPage(Response r){
        ArrayList<Object> nestedArray = (ArrayList<Object>)r.jsonPath().getList("page");
        boolean flag = nestedArray.get(0) == null;
        Assert.assertTrue("El listado de albumes no debe tener paginador",flag);
    }

    public Response sendRequest(RequestSpecification request, int requestType, String url, Object pojo) {
        Response response = null;

        // Add the Json to the body of the request
        if (null != pojo) {
            String payload = createJSONPayload(pojo).toString();
            request.body(payload);
        }

        // need to add a switch based on request type
        switch (requestType) {
            case BaseEndpoints.GET_REQUEST:
                if (null == request) {
                    response = RestAssured.when().get(url);
                } else {
                    response = request.get(url);
                }
                break;
            case BaseEndpoints.POST_REQUEST:
                if (null == request) {
                    response = RestAssured.when().post(url);
                } else {
                    response = request.post(url);
                }
                break;
            case BaseEndpoints.DELETE_REQUEST:
                if (null == request) {
                    response = RestAssured.when().delete(url);
                } else {
                    response = request.delete(url);
                }
                break;
            case BaseEndpoints.PUT_REQUEST:
                if (null == request) {
                    response = RestAssured.when().put(url);
                } else {
                    response = request.put(url);
                }
                break;
            default:
                if (null == request) {
                    response = RestAssured.when().post(url);
                } else {
                    response = request.post(url);
                }
                response = request.post(url);
                break;
        }
        return response;
    }
}

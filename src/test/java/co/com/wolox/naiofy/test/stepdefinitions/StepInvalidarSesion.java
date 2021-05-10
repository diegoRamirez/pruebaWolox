package co.com.wolox.naiofy.test.stepdefinitions;

import co.com.wolox.naiofy.test.Base.Base;
import co.com.wolox.naiofy.test.endpoints.InvalidarSesionEndpoint;
import co.com.wolox.naiofy.test.endpoints.LoginEndpoint;
import co.com.wolox.naiofy.test.model.Login;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import java.io.IOException;

public class StepInvalidarSesion {
    private Base base = new Base();
    private LoginEndpoint loginEndpoint;
    private InvalidarSesionEndpoint invalidarSesionEndpoint;
    private String tipoUsuario;

    @Dado("que se quiere invalidar la sesion de usuario {string}")
    public void queSeQuiereInvalidarLaSesionDeUsuario(String tipo) throws IOException {
        tipoUsuario = tipo;
        loginEndpoint = new LoginEndpoint(tipoUsuario);
        invalidarSesionEndpoint = new InvalidarSesionEndpoint();
    }

    @Cuando("se consume el sevicio de invalidar sesion")
    public void seConsumeElSevicioDeInvalidarSesion() throws IOException {
        base.setRequest(loginEndpoint.getRequestWithJSONHeaders());
        base.setResponse(loginEndpoint.goLogin(base.getRequest(),new Login(tipoUsuario)));
        String token = loginEndpoint.getTokenAuth(base.getResponse());
        base.setResponse(invalidarSesionEndpoint.goInvalidarSesion(loginEndpoint.getRequestWithToken(token)));
    }

    @Entonces("el servicio debe invalidar la sesion y retornar el{string}")
    public void elServicioDebeInvalidarLaSesionYRetornarEl(String statusCode) {
        int code = Integer.valueOf(statusCode);
        invalidarSesionEndpoint.verifyResponseStatusValue(base.getResponse(), code);
    }

    @Entonces("el servicio no debe invalidar la sesion y retornar statusCode diferente a{string}")
    public void elServicioNoDebeInvalidarLaSesionYRetornarStatusCodeDiferenteA(String statusCode) {
        int code = Integer.valueOf(statusCode);
        invalidarSesionEndpoint.verifyResponseStatusValueRegular(base.getResponse(), code);
    }

    @Cuando("se consume el sevicio de invalidar sesion con token no valido")
    public void seConsumeElSevicioDeInvalidarSesionConTokenNoValido() {
        final String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjE0Nywicm9sZSI6InJlZ3VsYXIiL";
        base.setResponse(invalidarSesionEndpoint.goInvalidarSesion(loginEndpoint.getRequestWithToken(token)));
    }

    @Entonces("el servico de invalidar sesion debe retornar el{string}")
    public void elServicoDeInvalidarSesionDebeRetornarEl(String statusCode) {
        int code = Integer.valueOf(statusCode);
        invalidarSesionEndpoint.verifyResponseStatusValue(base.getResponse(), code);
    }
}


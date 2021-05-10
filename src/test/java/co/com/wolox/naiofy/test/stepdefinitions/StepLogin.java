package co.com.wolox.naiofy.test.stepdefinitions;

import co.com.wolox.naiofy.test.Base.Base;
import co.com.wolox.naiofy.test.endpoints.LoginEndpoint;
import co.com.wolox.naiofy.test.model.Login;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;

import java.io.IOException;

public class StepLogin {

    private Base base = new Base();
    private LoginEndpoint loginEndpoint;
    private String tipoUsuario;


    @Dado("Un cliente que desea loguearse en la web {string}")
    public void unClienteQueDeseaLoguearseEnLaWeb(String tipo) throws IOException {
        loginEndpoint = new LoginEndpoint(tipo);
        tipoUsuario = tipo;
    }

    @Cuando("se consume el sevicio de login")
    public void seConsumeElSevicioDeLogin() throws IOException {
        base.setRequest(loginEndpoint.getRequestWithJSONHeaders());
        base.setResponse(loginEndpoint.goLogin(base.getRequest(),new Login(tipoUsuario)));
    }

    @Entonces("el servico debe retornar un {string}")
    public void elServicoDebeRetornarUn(String statusCode) {
       int code = Integer.valueOf(statusCode);
       loginEndpoint.verifyResponseStatusValue(base.getResponse(), code);
    }

    @Y("debe retornar un token de autorizacion")
    public void debeRetornarUnTokenDeAutorizacion() {
        loginEndpoint.verifyHeaderAuthorization(base.getResponse());
    }

    @Y("debe retornar el atributo user_id")
    public void debeRetornarElAtributoUser_id() {
        loginEndpoint.verifyUserId(tipoUsuario,"user_id", "", base.getResponse());
    }

    @Y("el cuerpo de la respuesta debe ser acorde al formato especificado")
    public void elCuerpoDeLaRespuestaDebeSerAcordeAlFormatoEspecificado() {
        loginEndpoint.verifyFormat(base.getResponse());
    }
}


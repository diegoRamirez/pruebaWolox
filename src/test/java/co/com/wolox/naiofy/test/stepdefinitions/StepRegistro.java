package co.com.wolox.naiofy.test.stepdefinitions;

import co.com.wolox.naiofy.test.Base.Base;
import co.com.wolox.naiofy.test.endpoints.RegistroEndpoint;
import co.com.wolox.naiofy.test.model.Registro;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;

import java.io.IOException;

public class StepRegistro {
    private Base base = new Base();
    private RegistroEndpoint registroEndpoint;

    @Dado("que se desea crear un nuevo usuario")
    public void queSeDeseaCrearUnNuevoUsuario() throws IOException {
        registroEndpoint = new RegistroEndpoint();
    }

    @Cuando("se consume el sevicio de registro")
    public void seConsumeElSevicioDeRegistro() throws IOException {
        base.setRequest(registroEndpoint.getRequestWithJSONHeaders());
        base.setResponse(registroEndpoint.goRegistro(base.getRequest(),new Registro()));
    }

    @Entonces("el servicio debe retornar  {string}")
    public void elServicioDebeRetornar(String statusCode) {
        int code = Integer.valueOf(statusCode);
        registroEndpoint.verifyResponseStatusValue(base.getResponse(), code);
    }

    @Cuando("se ingresa un email con dominio diferente al de wolox")
    public void seIngresaUnEmailConDominioDiferenteAlDeWolox() throws IOException {
        base.setRequest(registroEndpoint.getRequestWithJSONHeaders());
        base.setResponse(registroEndpoint.goRegistro(base.getRequest(),new Registro("dominio")));
    }


    @Cuando("se ingresa un numero en el campo firstName")
    public void seIngresaUnNumeroEnElCampoFirstName() throws IOException {
        base.setRequest(registroEndpoint.getRequestWithJSONHeaders());
        base.setResponse(registroEndpoint.goRegistro(base.getRequest(),new Registro("firstName")));
    }


    @Cuando("se ingresa un numero en el campo lastName")
    public void seIngresaUnNumeroEnElCampoLastName() throws IOException {
        base.setRequest(registroEndpoint.getRequestWithJSONHeaders());
        base.setResponse(registroEndpoint.goRegistro(base.getRequest(),new Registro("lastName")));
    }

    @Cuando("se introducen errores en los tres campos de entrada")
    public void seIntroducenErroresEnLosTresCamposDeEntrada() throws IOException {
        base.setRequest(registroEndpoint.getRequestWithJSONHeaders());
        base.setResponse(registroEndpoint.goRegistro(base.getRequest(),new Registro(true)));
    }

    @Y("se debe retorna un mensaje con los tres errores inducidos")
    public void seDebeRetornaUnMensajeConLosTresErroresInducidos() throws IOException {
        registroEndpoint.verifyErrors(base.getResponse());
    }
}


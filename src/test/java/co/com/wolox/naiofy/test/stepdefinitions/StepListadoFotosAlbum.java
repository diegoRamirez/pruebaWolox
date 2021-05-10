package co.com.wolox.naiofy.test.stepdefinitions;

import co.com.wolox.naiofy.test.Base.Base;
import co.com.wolox.naiofy.test.endpoints.ListadoAlbumesEndpoint;
import co.com.wolox.naiofy.test.endpoints.ListadoFotosAlbumEndpoint;
import co.com.wolox.naiofy.test.endpoints.LoginEndpoint;
import co.com.wolox.naiofy.test.model.Login;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;

import java.io.IOException;

public class StepListadoFotosAlbum {

    private Base base = new Base();
    private LoginEndpoint loginEndpoint;
    private ListadoAlbumesEndpoint listadoAlbumesEndpoint;
    private ListadoFotosAlbumEndpoint listadoFotosAlbumEndpoint;
    private String tipoUsuario;
    private String idAlbum;

    @Dado("que se quiere obtener el listado de fotos de un album con un usuario {string}")
    public void queSeQuiereObtenerElListadoDeFotosDeUnAlbumConUnUsuario(String tipoUsuario) throws IOException {
        loginEndpoint = new LoginEndpoint(tipoUsuario);
        listadoAlbumesEndpoint = new ListadoAlbumesEndpoint();
        listadoFotosAlbumEndpoint = new ListadoFotosAlbumEndpoint();
        this.tipoUsuario = tipoUsuario;
    }

    @Cuando("se consume el sevicio de listado de fotos de un album")
    public void seConsumeElSevicioDeListadoDeFotosDeUnAlbum() throws IOException {
        base.setRequest(loginEndpoint.getRequestWithJSONHeaders());
        base.setResponse(loginEndpoint.goLogin(base.getRequest(),new Login(tipoUsuario)));
        String token = loginEndpoint.getTokenAuth(base.getResponse());
        idAlbum = "1";
        base.setResponse(listadoFotosAlbumEndpoint.goListadoFotosAlbum(loginEndpoint.getRequestWithToken(token), idAlbum));
    }

    @Entonces("el servico debe retornar {string}")
    public void elServicoDebeRetornar(String statusCode) {
        int code = Integer.valueOf(statusCode);
        listadoAlbumesEndpoint.verifyResponseStatusValue(base.getResponse(), code);
    }

    @Y("las fotos retornadas deben tener el formato especificado")
    public void lasFotosRetornadasDebenTenerElFormatoEspecificado() {
        listadoFotosAlbumEndpoint.verifyFormat(base.getResponse());
    }

    @Cuando("se consume el sevicio de listado de fotos de un album con token no valido")
    public void seConsumeElSevicioDeListadoDeFotosDeUnAlbumConTokenNoValido() {
        final String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjE0Nywicm9sZSI6InJlZ3VsYXIiL";
        base.setResponse(listadoFotosAlbumEndpoint.goListadoFotosAlbum(loginEndpoint.getRequestWithToken(token),idAlbum));
    }
}

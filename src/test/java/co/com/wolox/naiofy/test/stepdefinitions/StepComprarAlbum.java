package co.com.wolox.naiofy.test.stepdefinitions;

import co.com.wolox.naiofy.test.Base.Base;
import co.com.wolox.naiofy.test.endpoints.ComprarAlbumEndpoint;
import co.com.wolox.naiofy.test.endpoints.ListadoAlbumesEndpoint;
import co.com.wolox.naiofy.test.endpoints.LoginEndpoint;
import co.com.wolox.naiofy.test.model.Login;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;

import java.io.IOException;

public class StepComprarAlbum {
    private Base base = new Base();
    private LoginEndpoint loginEndpoint;
    private ListadoAlbumesEndpoint listadoAlbumesEndpoint;
    private ComprarAlbumEndpoint comprarAlbumEndpoint;
    private String tipoUsuario;
    private String idAlbum;

    @Dado("que se quiere comprar un album con un usuario {string}")
    public void queSeQuiereComprarUnAlbumConUnUsuario(String tipo) throws IOException {
        tipoUsuario = tipo;
        loginEndpoint = new LoginEndpoint(tipoUsuario);
        comprarAlbumEndpoint = new ComprarAlbumEndpoint();
        listadoAlbumesEndpoint = new ListadoAlbumesEndpoint();
    }

    @Cuando("se consume el sevicio de compra de albumes")
    public void seConsumeElSevicioDeCompraDeAlbumes() throws IOException {
        base.setRequest(loginEndpoint.getRequestWithJSONHeaders());
        base.setResponse(loginEndpoint.goLogin(base.getRequest(),new Login(tipoUsuario)));
        String token = loginEndpoint.getTokenAuth(base.getResponse());

        base.setResponse(listadoAlbumesEndpoint.goListadoAlbumes(loginEndpoint.getRequestWithToken(token)));
        idAlbum = listadoAlbumesEndpoint.getIdAlbum(base.getResponse());

        base.setResponse(comprarAlbumEndpoint.goComprarAlbum(loginEndpoint.getRequestWithToken(token),idAlbum));
    }

    @Entonces("el servico debe retornar el{string}")
    public void elServicoDebeRetornarEl(String statusCode) {
        int code = Integer.valueOf(statusCode);
        comprarAlbumEndpoint.verifyResponseStatusValue(base.getResponse(), code);
    }

    @Cuando("se consume el sevicio de compra de albumes con token no valido")
    public void
    seConsumeElSevicioDeCompraDeAlbumesConTokenNoValido() throws IOException {
        final String badToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjE0Nywicm9sZSI6InJlZ3VsYXIiL";
        base.setRequest(loginEndpoint.getRequestWithJSONHeaders());
        base.setResponse(loginEndpoint.goLogin(base.getRequest(),new Login(tipoUsuario)));
        String token = loginEndpoint.getTokenAuth(base.getResponse());
        base.setResponse(listadoAlbumesEndpoint.goListadoAlbumes(loginEndpoint.getRequestWithToken(token)));
        idAlbum = listadoAlbumesEndpoint.getIdAlbum(base.getResponse());
        base.setResponse(comprarAlbumEndpoint.goComprarAlbum(loginEndpoint.getRequestWithToken(badToken),idAlbum));
    }


    @Cuando("se consume el sevicio de compra dos veces con el mismo usuario y mismo album")
    public void seConsumeElSevicioDeCompraDosVecesConElMismoUsuarioYMismoAlbum() throws IOException {
        base.setRequest(loginEndpoint.getRequestWithJSONHeaders());
        base.setResponse(loginEndpoint.goLogin(base.getRequest(),new Login(tipoUsuario)));
        String token = loginEndpoint.getTokenAuth(base.getResponse());

        base.setResponse(listadoAlbumesEndpoint.goListadoAlbumes(loginEndpoint.getRequestWithToken(token)));
        idAlbum = listadoAlbumesEndpoint.getIdAlbum(base.getResponse());

        base.setResponse(comprarAlbumEndpoint.goComprarAlbum(loginEndpoint.getRequestWithToken(token),idAlbum));
        base.setResponse(comprarAlbumEndpoint.goComprarAlbum(loginEndpoint.getRequestWithToken(token),idAlbum));
    }
}

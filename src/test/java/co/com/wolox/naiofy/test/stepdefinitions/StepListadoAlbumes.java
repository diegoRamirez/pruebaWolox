package co.com.wolox.naiofy.test.stepdefinitions;

import co.com.wolox.naiofy.test.Base.Base;
import co.com.wolox.naiofy.test.endpoints.ListadoAlbumesEndpoint;
import co.com.wolox.naiofy.test.endpoints.LoginEndpoint;
import co.com.wolox.naiofy.test.model.Login;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;

import java.io.IOException;

public class StepListadoAlbumes {
    private Base base = new Base();
    private LoginEndpoint loginEndpoint;
    private ListadoAlbumesEndpoint listadoAlbumesEndpoint;
    private String tipoCliente;

    @Dado("que se quiere obtener el listado de albumes con un usuario {string}")
    public void queSeQuiereObtenerElListadoDeAlbumesConUnUsuario(String tipo) throws IOException {
        loginEndpoint = new LoginEndpoint(tipo);
        listadoAlbumesEndpoint = new ListadoAlbumesEndpoint();
        tipoCliente = tipo;
    }

    @Cuando("se consume el sevicio de listado de albumes")
    public void seConsumeElSevicioDeListadoDeAlbumes() throws IOException {
        base.setRequest(loginEndpoint.getRequestWithJSONHeaders());
        base.setResponse(loginEndpoint.goLogin(base.getRequest(),new Login(tipoCliente)));
        String token = loginEndpoint.getTokenAuth(base.getResponse());
        base.setResponse(listadoAlbumesEndpoint.goListadoAlbumes(loginEndpoint.getRequestWithToken(token)));
    }


    @Y("los albumes retornados deben tener el formato especificado")
    public void losAlbumesRetornadosDebenTenerElFormatoEspecificado() {
        listadoAlbumesEndpoint.verifyFormat(base.getResponse());
    }

    @Cuando("se consume el sevicio de listado de albumes con token no valido")
    public void seConsumeElSevicioDeListadoDeAlbumesConTokenNoValido() {
        final String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjE0Nywicm9sZSI6InJlZ3VsYXIiL";
        base.setResponse(listadoAlbumesEndpoint.goListadoAlbumes(loginEndpoint.getRequestWithToken(token)));
    }

    @Entonces("el servico debe retornar el  {string}")
    public void elServicoDebeRetornarEl(String statusCode) {
        int code = Integer.valueOf(statusCode);
        listadoAlbumesEndpoint.verifyResponseStatusValue(base.getResponse(), code);
    }

    @Y("los albumes retornados no deben tener paginacion")
    public void losAlbumesRetornadosNoDebenTenerPaginacion() {
        listadoAlbumesEndpoint.verifyPage(base.getResponse());
    }
}

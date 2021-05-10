package co.com.wolox.naiofy.test.stepdefinitions;

import co.com.wolox.naiofy.test.Base.Base;
import co.com.wolox.naiofy.test.endpoints.ListadoUsuariosEndpoint;
import co.com.wolox.naiofy.test.endpoints.LoginEndpoint;
import co.com.wolox.naiofy.test.model.Login;
import co.com.wolox.naiofy.test.model.Usuario;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;

import java.io.IOException;

public class StepListadoUsuarios {

    private Base base = new Base();
    private LoginEndpoint loginEndpoint;
    private ListadoUsuariosEndpoint listadoUsuariosEndpoint;
    private String tipoCliente;
    private Usuario usuario;

    @Dado("que se quiere obtener el listado de usuarios con un usuario {string}")
    public void queSeQuiereObtenerElListadoDeUsuariosConUnUsuario(String tipo) throws IOException {
        loginEndpoint = new LoginEndpoint(tipo);
        listadoUsuariosEndpoint = new ListadoUsuariosEndpoint();
        tipoCliente = tipo;
        usuario = new Usuario();
    }

    @Cuando("se consume el sevicio de listado de usuarios")
    public void seConsumeElSevicioDeListadoDeUsuarios() throws IOException {
        base.setRequest(loginEndpoint.getRequestWithJSONHeaders());
        base.setResponse(loginEndpoint.goLogin(base.getRequest(),new Login(tipoCliente)));
        String token = loginEndpoint.getTokenAuth(base.getResponse());
        base.setResponse(listadoUsuariosEndpoint.goListadoUsuarios(loginEndpoint.getRequestWithToken(token)));
    }

    @Entonces("el servico debe retornar  {string}")
    public void elServicoDebeRetornar(String statusCode) {
        int code = Integer.valueOf(statusCode);
        listadoUsuariosEndpoint.verifyResponseStatusValue(base.getResponse(), code);
    }

    @Cuando("se consume el sevicio de listado de usuarios con un token no valido")
    public void seConsumeElSevicioDeListadoDeUsuariosConUnTokenNoValido() {
        final String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjE0Nywicm9sZSI6InJlZ3VsYXIiL";
        base.setResponse(listadoUsuariosEndpoint.goListadoUsuarios(loginEndpoint.getRequestWithToken(token)));
    }

    @Y("los usuarios retornados deben tener el formato especificado")
    public void losUsuariosRetornadosDebenTenerElFormatoEspecificado() {
        listadoUsuariosEndpoint.verifyFormat(base.getResponse(), usuario);
    }

    @Y("se deben traer usuarios de los dos tipos")
    public void seDebenTraerUsuariosDeLosDosTipos() {
        listadoUsuariosEndpoint.verifyUser(tipoCliente,base.getResponse());
    }
}

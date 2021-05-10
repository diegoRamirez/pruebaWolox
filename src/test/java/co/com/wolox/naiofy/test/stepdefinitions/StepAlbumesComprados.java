package co.com.wolox.naiofy.test.stepdefinitions;

import co.com.wolox.naiofy.test.Base.Base;
import co.com.wolox.naiofy.test.endpoints.*;
import co.com.wolox.naiofy.test.model.Login;
import co.com.wolox.naiofy.test.model.Registro;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StepAlbumesComprados {
    private Base base = new Base();
    private LoginEndpoint loginEndpoint;
    private AlbumesCompradosEndpoint albumesCompradosEndpoint;
    private RegistroEndpoint registroEndpoint;
    private ComprarAlbumEndpoint comprarAlbumEndpoint;
    private ListadoAlbumesEndpoint listadoAlbumesEndpoint;
    private String tipoUsuario;
    private String idUsuario;
    private String idAlbum;
    private List<String> listIdAlbum;
    private Registro registro;

    @Dado("que se quiere obtener el listado de albumes comprados con un usuario {string}")
    public void queSeQuiereObtenerElListadoDeAlbumesCompradosConUnUsuario(String tipo) throws IOException {
        tipoUsuario = tipo;
        loginEndpoint = new LoginEndpoint(tipoUsuario);
        albumesCompradosEndpoint = new AlbumesCompradosEndpoint();
        listadoAlbumesEndpoint = new ListadoAlbumesEndpoint();
        comprarAlbumEndpoint = new ComprarAlbumEndpoint();
        registroEndpoint = new RegistroEndpoint();
        listIdAlbum = new ArrayList<String>();
        registro = new Registro();
    }

    @Cuando("se consume el sevicio de listado de albumes comprados")
    public void seConsumeElSevicioDeListadoDeAlbumesComprados() throws IOException {
        base.setRequest(loginEndpoint.getRequestWithJSONHeaders());
        base.setResponse(loginEndpoint.goLogin(base.getRequest(),new Login(tipoUsuario)));
        String token = loginEndpoint.getTokenAuth(base.getResponse());

        idUsuario = loginEndpoint.getUserId(tipoUsuario);
        base.setResponse(albumesCompradosEndpoint.goAlbumesComprados(loginEndpoint.getRequestWithToken(token), idUsuario));
    }

    @Entonces("el servico debe retornar el {string}")
    public void elServicoDebeRetornarEl(String statusCode) {
        int code = Integer.valueOf(statusCode);
        albumesCompradosEndpoint.verifyResponseStatusValue(base.getResponse(), code);
    }

    @Y("los albumes comprados retornados deben tener el formato especificado")
    public void losAlbumesCompradosRetornadosDebenTenerElFormatoEspecificado() {
        albumesCompradosEndpoint.verifyFormat(base.getResponse());
    }

    @Cuando("se consume el sevicio de listado de albumes comprados con token no valido")
    public void seConsumeElSevicioDeListadoDeAlbumesCompradosConTokenNoValido() {
        final String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjE0Nywicm9sZSI6InJlZ3VsYXIiL";
        base.setResponse(albumesCompradosEndpoint.goAlbumesComprados(loginEndpoint.getRequestWithToken(token),idUsuario));
    }

    @Y("debe retornar todos los albumes comprados por el usuario")
    public void debeRetornarTodosLosAlbumesCompradosPorElUsuario() {
        albumesCompradosEndpoint.verifyAlbumesComprados(base.getResponse(),listIdAlbum);
    }

    @Cuando("se realizan compras y se consume el sevicio de listado de albumes comprados")
    public void seRealizanComprasYSeConsumeElSevicioDeListadoDeAlbumesComprados() throws IOException {
        base.setRequest(registroEndpoint.getRequestWithJSONHeaders());
        base.setResponse(registroEndpoint.goRegistro(base.getRequest(),new Registro()));
        registro = registroEndpoint.getDefaultRegistro();

        base.setRequest(loginEndpoint.getRequestWithJSONHeaders());
        base.setResponse(loginEndpoint.goLogin(base.getRequest(),new Login(registro.getEmail(),registro.getPassword())));
        idUsuario = loginEndpoint.getUserId(base.getResponse());
        String token = loginEndpoint.getTokenAuth(base.getResponse());
        base.setResponse(listadoAlbumesEndpoint.goListadoAlbumes(loginEndpoint.getRequestWithToken(token)));
        listIdAlbum = listadoAlbumesEndpoint.getIdAlbumes(base.getResponse());
        for (int i=0; i<5; i++){

            base.setResponse(comprarAlbumEndpoint.goComprarAlbum(loginEndpoint.getRequestWithToken(token),listIdAlbum.get(i)));
            listIdAlbum.add(idAlbum);
        }
        base.setResponse(albumesCompradosEndpoint.goAlbumesComprados(loginEndpoint.getRequestWithToken(token),idUsuario));

    }
}

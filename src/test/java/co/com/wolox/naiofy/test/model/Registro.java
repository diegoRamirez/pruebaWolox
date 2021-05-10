package co.com.wolox.naiofy.test.model;

import co.com.wolox.naiofy.test.utilitys.Utilitys;
import java.io.IOException;

public class Registro {
    private String email;
    private String password;
    private String firstName;
    private String lastName;

    Utilitys utility = new Utilitys();

    public Registro() throws IOException {
        firstName = utility.generarFirstName();
        lastName = utility.generarLastName();
        email =utility.generarCorreoWolox(lastName);
        password = utility.generarPassword();

        setEmail(email);
        setPassword(password);
        setFirstName(firstName);
        setLastName(lastName);
    }

    public Registro(String error) throws IOException {
        if (error.equals("firstName")) {
            firstName = utility.generarFirstNameError();
            lastName = utility.generarLastName();
            email = utility.generarCorreoWolox(lastName);
            password = utility.generarPassword();
        }
        if (error.equals("dominio")){
            firstName = utility.generarFirstName();
            lastName = utility.generarLastName();
            email =utility.generarCorreoNoWolox(lastName);
            password = utility.generarPassword();
        }
        if (error.equals("lastName")) {
            firstName = utility.generarFirstName();
            lastName = utility.generarLastNameError();
            email = utility.generarCorreoWolox(lastName);
            password = utility.generarPassword();
        }

        setEmail(email);
        setPassword(password);
        setFirstName(firstName);
        setLastName(lastName);
    }

    public Registro(boolean flag) throws IOException {
        if (flag) {
            firstName = utility.generarFirstNameError();
            lastName = utility.generarLastNameError();
            email =utility.generarCorreoNoWolox(lastName);
            password = utility.generarPassword();
        }

        setEmail(email);
        setPassword(password);
        setFirstName(firstName);
        setLastName(lastName);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}

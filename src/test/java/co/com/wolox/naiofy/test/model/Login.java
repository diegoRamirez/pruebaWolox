package co.com.wolox.naiofy.test.model;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Login {
    private String email;
    private String password;
    Properties properties = new Properties();

    public Login(String tipo) throws IOException {
        properties.load(new FileReader("src/test/resources/configuration.properties"));
        if (tipo.equals("Admin")){
            email = properties.getProperty("emailAdmin");
            password = properties.getProperty("passwordAdmin");
        }else {
            email = properties.getProperty("emailRegular");
            password = properties.getProperty("passwordRegular");
        }
        setEmail(email);
        setPassword(password);
    }

    public Login(String email, String password) {
        setEmail(email);
        setPassword(password);
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

}

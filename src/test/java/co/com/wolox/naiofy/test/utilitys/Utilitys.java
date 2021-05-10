package co.com.wolox.naiofy.test.utilitys;

import com.github.javafaker.Faker;

import java.util.Locale;
import java.util.Random;

public class Utilitys {

    public String generarCorreoWolox(String lastname) {
            String email = "xxxx";
        try {
            Faker faker = Faker.instance(new Locale("es", "CO"), new Random());
            String sufijo = faker.number().digits(4);
            email = lastname + sufijo + "@wolox.com.ar";

        } catch (Exception e) {
            e.getStackTrace();
        }
        return email;
    }

    public String generarFirstName() {
        String firstName = "xxxx";
        try {
            Faker faker = Faker.instance(new Locale("es", "CO"), new Random());
            firstName = faker.name().firstName();

        } catch (Exception e) {
            e.getStackTrace();
        }
        return firstName;
    }

    public String generarLastName() {
        String lastName = "xxxx";
        try {
            Faker faker = Faker.instance(new Locale("es", "CO"), new Random());
            lastName = faker.name().lastName();

        } catch (Exception e) {
            e.getStackTrace();
        }
        return lastName;
    }

    public String generarPassword() {
        String password = "xxxxxxxx";
        try {
            Faker faker = Faker.instance(new Locale("es", "CO"), new Random());
            password = "abcd" + faker.number().digits(4);

        } catch (Exception e) {
            e.getStackTrace();
        }
        return password;
    }

    public String generarCorreoNoWolox(String lastName) {
        String email = "xxxx";
        try {
            Faker faker = Faker.instance(new Locale("es", "CO"), new Random());
            String sufijo = faker.number().digits(4);
            email = lastName + sufijo + "@mailinator.com";

        } catch (Exception e) {
            e.getStackTrace();
        }
        return email;
    }

    public String generarFirstNameError() {
        String firstName = "xxxx";
        try {
            Faker faker = Faker.instance(new Locale("es", "CO"), new Random());
            firstName = faker.number().digits(4);

        } catch (Exception e) {
            e.getStackTrace();
        }
        return firstName;
    }

    public String generarLastNameError() {
        String lastName = "xxxx";
        try {
            Faker faker = Faker.instance(new Locale("es", "CO"), new Random());
            lastName = faker.number().digits(4);

        } catch (Exception e) {
            e.getStackTrace();
        }
        return lastName;
    }

    public  boolean isNumeric(String cadena){
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }

    public int generarIndice(int tope){
        int indice= 0;
        try {
            Faker faker = Faker.instance(new Locale("es", "CO"), new Random());
            indice = faker.number().numberBetween(0,tope-1);

        } catch (Exception e) {
            e.getStackTrace();
        }
        return indice;

    }
}

package co.com.wolox.naiofy.test.model;


public class Usuario {
    String firstName;
    String lastName;
    String email;
    String role;

    public Usuario() {
        setFirstName("");
        setLastName("");
        setEmail("");
        setRole("");
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

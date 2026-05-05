package models.user;

public class CreateUserRequest {
    private String firstName;
    private String lastName;
    private String email;

    // constructor
    public CreateUserRequest(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}

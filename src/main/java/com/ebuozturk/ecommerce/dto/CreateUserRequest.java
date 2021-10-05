package com.ebuozturk.ecommerce.dto;


public class CreateUserRequest {
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;


    public CreateUserRequest(String firstName, String middleName, String lastName, String email) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

}

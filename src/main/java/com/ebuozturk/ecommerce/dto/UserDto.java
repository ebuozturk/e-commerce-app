package com.ebuozturk.ecommerce.dto;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;

    public UserDto(Long id, String firstName, String middleName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
    }

    public UserDto(String firstName, String middleName, String lastName, String email) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
    }


}

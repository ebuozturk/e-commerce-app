package com.ebuozturk.ecommerce.dto;

import com.ebuozturk.ecommerce.model.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class AddressDto {
    private Long id;
    private String addressName;
    private String phoneNumber;
    private String country;
    private String city;
    private String street;
    private String zipCode;

    public AddressDto(String addressName, String phoneNumber, String country, String city, String street, String zipCode) {
        this.addressName = addressName;
        this.phoneNumber = phoneNumber;
        this.country = country;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
    }

    public AddressDto(Long id, String addressName, String phoneNumber, String country, String city, String street, String zipCode) {
        this.id = id;
        this.addressName = addressName;
        this.phoneNumber = phoneNumber;
        this.country = country;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
    }


}

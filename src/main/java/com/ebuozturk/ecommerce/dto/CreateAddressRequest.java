package com.ebuozturk.ecommerce.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class CreateAddressRequest {
    private String addressName;
    private String phoneNumber;
    private String country;
    private String city;
    private String street;
    private String zipCode;
    private Long userId;

    public CreateAddressRequest(String addressName, String phoneNumber, String country, String city, String street, String zipCode, Long userId) {
        this.addressName = addressName;
        this.phoneNumber = phoneNumber;
        this.country = country;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
        this.userId = userId;
    }



}

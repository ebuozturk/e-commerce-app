package com.ebuozturk.ecommerce.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String addressName;
    private String phoneNumber;
    private String country;
    private String city;
    private String street;
    private String zipCode;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Address(Long id,String addressName, String phoneNumber, String country, String city, String street, String zipCode) {
        this.id = id;
        this.addressName = addressName;
        this.phoneNumber = phoneNumber;
        this.country = country;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
    }

    public Address(String addressName, String phoneNumber, String country, String city, String street, String zipCode, User user) {
        this.addressName = addressName;
        this.phoneNumber = phoneNumber;
        this.country = country;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
        this.user = user;
    }

    public Address(Long id, String addressName, String phoneNumber, String country, String city, String street, String zipCode, User user) {
        this.id = id;
        this.addressName = addressName;
        this.phoneNumber = phoneNumber;
        this.country = country;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
        this.user = user;
    }


}

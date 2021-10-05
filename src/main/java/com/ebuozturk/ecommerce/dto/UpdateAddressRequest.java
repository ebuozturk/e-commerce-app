package com.ebuozturk.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAddressRequest {
    private Long id;
    private String addressName;
    private String phoneNumber;
    private String country;
    private String city;
    private String street;
    private String zipCode;
    private Long userId;

}

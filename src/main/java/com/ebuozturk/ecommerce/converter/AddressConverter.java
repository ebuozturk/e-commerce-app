package com.ebuozturk.ecommerce.converter;

import com.ebuozturk.ecommerce.dto.address.AddressDto;
import com.ebuozturk.ecommerce.model.Address;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AddressConverter {


    public AddressDto convert(Address address){
        return new AddressDto(address.getId(),
                address.getAddressName(),
                address.getPhoneNumber(),
                address.getCountry(),
                address.getCity(),
                address.getStreet(),
                address.getZipCode());
    }

    public List<AddressDto> convert(List<Address> addressList){

        return addressList.stream().map(this::convert).collect(Collectors.toList());
    }
}

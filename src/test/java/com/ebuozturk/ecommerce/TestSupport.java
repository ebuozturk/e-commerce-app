package com.ebuozturk.ecommerce;

import com.ebuozturk.ecommerce.dto.AddressDto;
import com.ebuozturk.ecommerce.dto.UserDto;
import com.ebuozturk.ecommerce.model.Address;
import com.ebuozturk.ecommerce.model.User;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestSupport {

    public static List<User> generateUsers(){
        return IntStream.range(0,5).mapToObj(i-> new User((long)i,
                    "firstName"+i,
                    "middleName"+i,
                    "lastName"+i,
                    "email"+i,
                    new Random(2).nextBoolean())
        ).collect(Collectors.toList());
    }
    public static List<UserDto> generateUserDtoList(List<User> userList){
        return userList.stream().map(user-> new UserDto(user.getId(),user.getFirstName(),
                user.getMiddleName(), user.getLastName(), user.getEmail())).collect(Collectors.toList());
    }
    public static User generateUser(Long id){
       return new User(id,"testName","testName","testName","testMail");
    }
    public static User generateUser(String mail){
        return new User("testName","testName","testName",mail);
    }
    public static UserDto generateUserDto(Long id){
        return new UserDto(id,"testName","testName","testName","testMail");
    }
    public static UserDto generateUserDto(String mail){
        return new UserDto("testName","testName","testName",mail);
    }
    public static List<AddressDto> generateAddressDtoList(List<Address> addressList){
        return addressList.stream().map(address-> new AddressDto(address.getId(),
                address.getAddressName(),
                address.getPhoneNumber(),
                address.getCountry(),
                address.getCity(),
                address.getStreet(),
                address.getZipCode())).collect(Collectors.toList());
    }




}

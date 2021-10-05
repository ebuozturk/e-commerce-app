package com.ebuozturk.ecommerce.service;

import com.ebuozturk.ecommerce.converter.AddressConverter;
import com.ebuozturk.ecommerce.dto.AddressDto;
import com.ebuozturk.ecommerce.dto.CreateAddressRequest;
import com.ebuozturk.ecommerce.dto.UpdateAddressRequest;
import com.ebuozturk.ecommerce.exception.AddressNotFoundException;
import com.ebuozturk.ecommerce.model.Address;
import com.ebuozturk.ecommerce.model.User;
import com.ebuozturk.ecommerce.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final UserService userService;
    private final AddressConverter converter;

    public AddressService(AddressRepository addressRepository, UserService userService, AddressConverter converter) {
        this.addressRepository = addressRepository;
        this.userService = userService;
        this.converter = converter;
    }

    public AddressDto createAddress(final CreateAddressRequest request){
        User user = userService.findById(request.getUserId());
        final Address address = new Address(request.getAddressName(),
                request.getPhoneNumber(),
                request.getCountry(),
                request.getCity(),
                request.getStreet(),
                request.getZipCode(),
                user);

        return converter.convert(addressRepository.save(address));
    }

    public AddressDto updateAddress(final UpdateAddressRequest request){
            User user = userService.findById(request.getUserId());
            findById(request.getId());
            Address updateAddress = new Address(request.getId(),
                    request.getAddressName(),
                    request.getPhoneNumber(),
                    request.getCountry(),
                    request.getCity(),
                    request.getStreet(),
                    request.getZipCode(),
                    user
                    );
            return converter.convert(addressRepository.save(updateAddress));
    }

    public void deleteAddress(Long id){
        if(doesAddressExist(id)){
            addressRepository.deleteById(id);
        }else
            new AddressNotFoundException("Address is not found by following id: "+id);
    }

    public AddressDto getAddressById(Long id){
        return converter.convert(findById(id));
    }

    public List<AddressDto> getAllAddresses(){
        return converter.convert(addressRepository.findAll());
    }
    public List<AddressDto> getAddressesByUserId(Long userId){
        return converter.convert(addressRepository.findAddressByUser_id(userId));
    }
    private Boolean doesAddressExist(Long id){
        return addressRepository.existsById(id);
    }
    protected Address findById(Long id){
        return addressRepository.findById(id).orElseThrow(()-> new AddressNotFoundException("Address is not found by following id: "+id));
    }


}

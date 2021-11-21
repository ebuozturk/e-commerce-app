package com.ebuozturk.ecommerce.service;

import com.ebuozturk.ecommerce.TestSupport;
import com.ebuozturk.ecommerce.converter.AddressConverter;
import com.ebuozturk.ecommerce.dto.address.AddressDto;
import com.ebuozturk.ecommerce.dto.address.CreateAddressRequest;
import com.ebuozturk.ecommerce.dto.address.UpdateAddressRequest;
import com.ebuozturk.ecommerce.exception.AddressNotFoundException;
import com.ebuozturk.ecommerce.exception.UserNotFoundException;
import com.ebuozturk.ecommerce.model.Address;
import com.ebuozturk.ecommerce.model.User;
import com.ebuozturk.ecommerce.repository.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AddressServiceTest extends TestSupport {

    private AddressService service;
    private AddressRepository repository;
    private AddressConverter converter;
    private UserService userService;


    @BeforeEach
    void setUp() {
        repository = mock(AddressRepository.class);
        converter = mock(AddressConverter.class);
        userService = mock(UserService.class);
        service = new AddressService(repository,userService,converter);
    }

    @Test
    public void testCreateAddress_whenUserIdExist_itShouldReturnAddressDto(){
        String userId = "1";
        User user = generateUser(userId);
        CreateAddressRequest request = new CreateAddressRequest("testName","testNumber","testCountry","testCity","testStreet","testZip",userId);
        Address address = new Address("testName","testNumber","testCountry","testCity","testStreet","testZip",user);
        Address savedAddress = new Address("1","testName","testNumber","testCountry","testCity","testStreet","testZip",user);
        AddressDto addressDto = new AddressDto("1","testName","testNumber","testCountry","testCity","testStreet","testZip");
        when(userService.findById(userId)).thenReturn(user);
        when(repository.save(address)).thenReturn(savedAddress);
        when(converter.convert(savedAddress)).thenReturn(addressDto);

        AddressDto result = service.createAddress(request);

        assertEquals(result,addressDto);

        verify(userService).findById(userId);
        verify(repository).save(address);
        verify(converter).convert(savedAddress);

    }
    @Test
    public void testCreateAddress_whenUserIdNotExist_itShouldThrowUserNotFound(){
        String userId = "1";
        CreateAddressRequest request = new CreateAddressRequest("testName","testNumber","testCountry","testCity","testStreet","testZip",userId);
        when(userService.findById(userId)).thenThrow(new UserNotFoundException("sdfsd"));

        assertThrows(UserNotFoundException.class,()-> service.createAddress(request));

        verify(userService).findById(userId);
        verifyNoInteractions(repository);
        verifyNoInteractions(converter);

    }

    @Test
    public void testUpdateAddress_whenAddressIdExistAndUserIdExist_itShouldReturnAddressDto(){
        String userId = "1";
        String addressId="1";
        User user = new User(userId,"createName","createName","createName","createEmail",true);
        UpdateAddressRequest request = new UpdateAddressRequest("updateName","updateNumber","updateCountry","updateCity","updateStreet","updateZip",userId);
        Address address = new Address(addressId,"testName","testNumber","testCountry","testCity","testStreet","testZip",user);
        Address saveAddress = new Address(addressId,"updateName","updateNumber","updateCountry","updateCity","updateStreet","updateZip",user);
        Address updatedAddress = new  Address(addressId,"updateName","updateNumber","updateCountry","updateCity","updateStreet","updateZip",user);
        AddressDto addressDto =  new AddressDto(addressId,"updateName","updateNumber","updateCountry","updateCity","updateStreet","updateZip");

        when(userService.findById(request.getUserId())).thenReturn(user);
        when(repository.findById(addressId)).thenReturn(Optional.of(address));
        when(repository.save(saveAddress)).thenReturn(updatedAddress);
        when(converter.convert(updatedAddress)).thenReturn(addressDto);

        AddressDto result = service.updateAddress(addressId,request);

        assertEquals(result,addressDto);

        verify(userService).findById(userId);
        verify(repository).findById(addressId);
        verify(repository).save(saveAddress);
        verify(converter).convert(updatedAddress);
    }
    @Test
    public void testUpdateAddress_whenAddressIdExistAndUserIdNotExist_itShouldThrowUserNotFoundException(){
        String userId = "1";
        String addressId="1";
        UpdateAddressRequest request = new UpdateAddressRequest("updateName","updateNumber","updateCountry","updateCity","updateStreet","updateZip",userId);

        when(userService.findById(request.getUserId())).thenThrow(new UserNotFoundException("message"));
        assertThrows(UserNotFoundException.class,()-> service.updateAddress(addressId,request));

        verify(userService).findById(userId);
        verifyNoInteractions(repository);
        verifyNoInteractions(converter);
    }

    @Test
    public void testUpdateAddress_whenAddressIdNotExistAndUserIdExist_itShouldThrowAddressNotFoundException(){
        String userId = "1";
        String addressId="1";
        User user = new User(userId,"createName","createName","createName","createEmail",true);
        UpdateAddressRequest request = new UpdateAddressRequest("updateName","updateNumber","updateCountry","updateCity","updateStreet","updateZip",userId);

        when(userService.findById(request.getUserId())).thenReturn(user);
        when(repository.findById(addressId)).thenReturn(Optional.empty());

        assertThrows(AddressNotFoundException.class,()->service.updateAddress(addressId,request));

        verify(userService).findById(userId);
        verify(repository).findById(addressId);
        verifyNoMoreInteractions(repository);
        verifyNoInteractions(converter);
    }

    @Test
    public void testUpdateAddress_whenAddressIdNotExistAndUserIdNotExist_itShouldThrowUserNotFoundException(){
        String userId = "1";
        String addressId="1";
        UpdateAddressRequest request = new UpdateAddressRequest("updateName","updateNumber","updateCountry","updateCity","updateStreet","updateZip",userId);

        when(userService.findById(request.getUserId())).thenThrow(new UserNotFoundException("message"));
        when(repository.findById(addressId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,()-> service.updateAddress(addressId,request));

        verify(userService).findById(userId);
        verifyNoInteractions(repository);
        verifyNoInteractions(converter);
    }

    @Test
    public void testGetAddressById_whenAddressIdExist_itShouldReturnAddressDto(){
        String addressId = "1";
        User user = new User("1","createName","createName","createName","createEmail",true);
        Address address = new Address(addressId,"testName","testNumber","testCountry","testCity","testStreet","testZip",user);
        AddressDto addressDto =  new AddressDto(addressId,"testName","testNumber","testCountry","testCity","testStreet","testZip");

        when(repository.findById(addressId)).thenReturn(Optional.of(address));
        when(converter.convert(address)).thenReturn(addressDto);

        AddressDto result = service.getAddressById(addressId);
        assertEquals(result,addressDto);

        verify(repository).findById(addressId);
        verify(converter).convert(address);


    }

    @Test
    public void testGetAddressById_whenAddressIdNotExist_itShouldThrowAddressNotFoundException(){
        String addressId = "1";

        when(repository.findById(addressId)).thenThrow(new AddressNotFoundException("message"));

        assertThrows(AddressNotFoundException.class,()->service.getAddressById(addressId));

        verify(repository).findById(addressId);
        verifyNoMoreInteractions(repository);
        verifyNoInteractions(converter);


    }

    @Test
    public void testGetAllAddresses_itShouldReturnAddressDtoList(){
        User user = new User("1","createName","createName","createName","createEmail",true);
        List<Address> addressList = IntStream.range(0,5).mapToObj(i->new Address(String.valueOf(i),"testName"+i,"testNumber"+i,"testCountry"+i,"testCity"+i,"testStreet"+i,"testZip"+i,user)).collect(Collectors.toList());
        List<AddressDto> addressDtoList = generateAddressDtoList(addressList);
        when(repository.findAll()).thenReturn(addressList);
        when(converter.convert(addressList)).thenReturn(addressDtoList);

        List<AddressDto> result = service.getAllAddresses();
        assertEquals(result,addressDtoList);

        verify(repository).findAll();
        verify(converter).convert(addressList);


    }

    @Test
    public void testGetAddressesByUserId_itShouldReturnAddressDtoList(){
        User user = new User("1","createName","createName","createName","createEmail",true);
        List<Address> addressList = IntStream.range(0,5).mapToObj(i->new Address(String.valueOf(i),"testName"+i,"testNumber"+i,"testCountry"+i,"testCity"+i,"testStreet"+i,"testZip"+i,user)).collect(Collectors.toList());
        List<AddressDto> addressDtoList = generateAddressDtoList(addressList);
        when(repository.findAddressByUser_id("1")).thenReturn(addressList);
        when(converter.convert(addressList)).thenReturn(addressDtoList);

        List<AddressDto> result = service.getAddressesByUserId("1");
        assertEquals(result,addressDtoList);

        verify(repository).findAddressByUser_id("1");
        verify(converter).convert(addressList);

    }

}
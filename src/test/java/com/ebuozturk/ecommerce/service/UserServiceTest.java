package com.ebuozturk.ecommerce.service;

import com.ebuozturk.ecommerce.TestSupport;
import com.ebuozturk.ecommerce.converter.UserConverter;
import com.ebuozturk.ecommerce.dto.user.CreateUserRequest;
import com.ebuozturk.ecommerce.dto.user.UpdateUserRequest;
import com.ebuozturk.ecommerce.dto.user.UserDto;
import com.ebuozturk.ecommerce.model.User;
import com.ebuozturk.ecommerce.exception.UserNotFoundException;
import com.ebuozturk.ecommerce.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest extends TestSupport {

    private UserConverter converter;
    private UserRepository userRepository;
    private UserService userService;
    private BasketService basketService;
    @BeforeEach
    void setUp() {
       converter = mock(UserConverter.class);
       userRepository = mock(UserRepository.class);
       basketService = mock(BasketService.class);
       userService = new UserService(userRepository,converter, basketService);
    }

    @Test
    public void testGetAllUsers_itShouldReturnUserDtoList(){
        List<User> userList = generateUsers();
        List<UserDto> userDtoList = generateUserDtoList(userList);
        when(userRepository.findAll()).thenReturn(userList);
        when(converter.convert(userList)).thenReturn(userDtoList);

        List<UserDto> result = userService.getAllUsers();

        assertEquals(userDtoList,result);
        verify(userRepository).findAll();
        verify(converter).convert(userList);
    }

    @Test
    public void testGetUserById_whenUserIdExist_itShouldReturnUserDto(){
        String id = "1";
        User user = generateUser(id);
        UserDto userDTO = generateUserDto(id);
        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        when(converter.convert(user)).thenReturn(userDTO);

        UserDto result = userService.getUserById(id);

        assertEquals(result,userDTO);
        verify(userRepository).findById(id);
        verify(converter).convert(user);

    }
    @Test
    public void testGetUserById_whenUserIdNotExist_itShouldThrowUserNotFound(){
        String id = "1";
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,()->
                userService.getUserById(id)
        );
        verify(userRepository).findById(id);
        verifyNoInteractions(converter);

    }

    @Test
    public void testGetUserByMail_whenUserMailExist_itShouldReturnUserDto(){
        String mail = "testmail";
        User user = generateUser(mail);
        UserDto userDTO = generateUserDto(mail);
        when(userRepository.findByEmail(mail)).thenReturn(Optional.of(user));
        when(converter.convert(user)).thenReturn(userDTO);

        UserDto result = userService.getUserByMail(mail);

        assertEquals(result,userDTO);
        verify(userRepository).findByEmail(mail);
        verify(converter).convert(user);

    }

    @Test
    public void testGetUserByMail_whenUserMailNotExist_itShouldThrowUserNotFound(){
        String mail = "testmail";
        when(userRepository.findByEmail(mail)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,()->
                userService.getUserByMail(mail)
        );
        verify(userRepository).findByEmail(mail);
        verifyNoInteractions(converter);

    }

    @Test
    public void testCreateUser_itShouldReturnCreatedUserDto(){
        CreateUserRequest request = new CreateUserRequest("createName","createName","createName","createEmail");
        User user = new User("createName","createName","createName","createEmail");
        User savedUser = new User("1","createName","createName","createName","createEmail",false);
        UserDto userDTO = new UserDto("createName","createName","createName","createEmail");
        when(userRepository.save(user)).thenReturn(savedUser);
        when(converter.convert(savedUser)).thenReturn(userDTO);

        UserDto result = userService.createUser(request);

        assertEquals(result,userDTO);
        verify(userRepository).save(user);
        verify(converter).convert(savedUser);


    }
    @Test
    public void testUpdateUser_itShouldReturnUpdatedUserDto(){
        String id = "1";
        UpdateUserRequest request = new UpdateUserRequest("updateName","updateName","updateName","updateEmail");
        User user = new User(id,"createName","createName","createName","createEmail",false);
        User updateUser = new User(id,"updateName","updateName","updateName","updateEmail");
        User savedUser = new User(id,"updateName","updateName","updateName","updateEmail",false);
        UserDto userDTO = new UserDto("updateName","updateName","updateName","updateEmail");

        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        when(userRepository.save(updateUser)).thenReturn(savedUser);
        when(converter.convert(savedUser)).thenReturn(userDTO);

        UserDto result = userService.updateUser(id,request);

        assertEquals(userDTO,result);
        verify(userRepository).save(updateUser);
        verify(userRepository).findById(id);
        verify(converter).convert(savedUser);


    }

    @Test
    public void testUpdateUser_whenUserIdNotExist_itShouldThrowUserNotFound(){
        String id = "1";
        UpdateUserRequest request = new UpdateUserRequest("updateName","updateName","updateName","updateEmail");

        when(userRepository.findById(id)).thenReturn(Optional.empty());


        assertThrows(UserNotFoundException.class,()->userService.updateUser(id,request));
        verify(userRepository).findById(id);
        verifyNoMoreInteractions(userRepository);
        verifyNoInteractions(converter);


    }

    @Test
    public void testDeleteUser_whenUserIdExist_itShouldReturnVoid(){
        String id = "1";

        when(userRepository.existsById(id)).thenReturn(true);

        userService.deleteUser(id);
        verify(userRepository).existsById(id);
        verify(userRepository).deleteById(id);
    }

    @Test
    public void testDeleteUser_whenUserIdNotExist_itShouldThrowUserNotFound(){
        String id = "1";

        when(userRepository.existsById(id)).thenReturn(false);

        assertThrows(UserNotFoundException.class,()->userService.deleteUser(id));
        verify(userRepository).existsById(id);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void testDeactivateUser_whenUserIdExist_itShouldUpdateUserByNotActive(){
        String id = "1";
        User user = new User(id,"createName","createName","createName","createEmail",true);
        User saveUser = new User(id,"createName","createName","createName","createEmail",false);

        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        userService.deactivateUser(id);

        verify(userRepository).findById(id);
        verify(userRepository).save(saveUser);



    }
    @Test
    public void testDeactivateUser_whenUserIdNotExist_itShouldThrowUserNotFound(){
        String id = "1";

        when(userRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,()->userService.deactivateUser(id));

        verify(userRepository).findById(id);
        verifyNoMoreInteractions(userRepository);

    }

    @Test
    public void testActivateUser_whenUserIdExist_itShouldUpdateUserByActive(){
        String id = "1";
        User user = new User(id,"createName","createName","createName","createEmail",false);
        User saveUser = new User(id,"createName","createName","createName","createEmail",true);

        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        userService.activateUser(id);

        verify(userRepository).findById(id);
        verify(userRepository).save(saveUser);



    }
    @Test
    public void testActivateUser_whenUserIdNotExist_itShouldThrowUserNotFound(){
        String id = "1";
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,()->userService.activateUser(id));

        verify(userRepository).findById(id);
        verifyNoMoreInteractions(userRepository);

    }

}
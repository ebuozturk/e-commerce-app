package com.ebuozturk.ecommerce.service;

import com.ebuozturk.ecommerce.converter.UserConverter;
import com.ebuozturk.ecommerce.dto.user.UserDto;
import com.ebuozturk.ecommerce.model.User;
import com.ebuozturk.ecommerce.exception.UserNotFoundException;
import com.ebuozturk.ecommerce.dto.user.CreateUserRequest;
import com.ebuozturk.ecommerce.dto.user.UpdateUserRequest;
import com.ebuozturk.ecommerce.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserConverter converter;
    private final BasketService basketService;

    public UserService(UserRepository userRepository, UserConverter converter, BasketService basketService) {
        this.userRepository = userRepository;
        this.converter = converter;
        this.basketService = basketService;
    }

    public UserDto createUser(final CreateUserRequest createUser){
        User user = new User(
                createUser.getFirstName(),
                createUser.getMiddleName(),
                createUser.getLastName(),
                createUser.getEmail()
                );
        User savedUser = userRepository.save(user);
        basketService.createBasket(savedUser);
        return converter.convert(savedUser);
    }

    public List<UserDto> getAllUsers(){

        return converter.convert(userRepository.findAll());
    }

    public UserDto updateUser(Long id, UpdateUserRequest request) {
        User user = findById(id);
        User updateUser = new User(user.getId(),
                request.getFirstName(),
                request.getMiddleName(),
                request.getLastName(),
                request.getEmail());
       return converter.convert(userRepository.save(updateUser));
    }

    public void deleteUser(Long id) {
      if(doesUserExist(id)){
          userRepository.deleteById(id);
      }
      else{
          throw new UserNotFoundException("User is not found by following id: "+id);
      }
    }
    public UserDto getUserByMail(String mail){
        return converter.convert(findByMail(mail));
    }

    public UserDto getUserById(Long id) {
        return converter.convert(findById(id));
    }
    public void deactivateUser(Long id){
        changeActivateUser(id,false);
    }

    public void activateUser(Long id) {
        changeActivateUser(id,true);
    }

    public User changeActivateUser(Long id, Boolean isActive){
        User user = findById(id);
        User changedUser = new User(user.getId(),
                user.getFirstName(),
                user.getMiddleName(),
                user.getLastName(),
                user.getEmail(),
                isActive
        );
        return userRepository.save(changedUser);
    }

    protected User findById(Long id){
        return userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("User is not found by following id: "+id));
    }
    protected User findByMail(String mail){
        return userRepository.findByEmail(mail).orElseThrow(()-> new UserNotFoundException("User is not found by following email: "+mail));
    }
    protected boolean doesUserExist(Long id){
        return userRepository.existsById(id);
    }

}

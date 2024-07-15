package com.srivath.blog.app.serviceimpls;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.srivath.blog.app.dtos.UserDto;
import com.srivath.blog.app.entities.User;
import com.srivath.blog.app.exceptions.ResourceNotFoundException;
import com.srivath.blog.app.repositories.UserRepository;
import com.srivath.blog.app.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepo;
    private ModelMapper modelMapper;

    /*
     * Note this is Constructor Injection. @Autowired Annotation is optional when
     * there is only single constructor defined in class. Spring IOC will
     * automatically injects the dependency.
     */
    public UserServiceImpl(UserRepository userRepo, ModelMapper modelMapper) {
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = mapUserDtoToEntity(userDto);
        User savedUser = userRepo.save(user);
        return mapUserEntityToDto(savedUser);

    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {

        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User updatedUser = userRepo.save(user);

        return mapUserEntityToDto(updatedUser);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> listOfUsers = userRepo.findAll();
        List<UserDto> listOfDtos = listOfUsers.stream().map(user -> mapUserEntityToDto(user))
                .collect(Collectors.toList());
        return listOfDtos;
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
        return mapUserEntityToDto(user);
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

        userRepo.delete(user);
    }

    public User mapUserDtoToEntity(UserDto userDto) {

        // method-1 => Manual conversion.

        // return new User(
        // userDto.getId(),
        // userDto.getName(),
        // userDto.getEmail(),
        // userDto.getPassword(),
        // userDto.getAbout());

        // method - 2 => Using Model Mapper Library
        return modelMapper.map(userDto, User.class);
    }

    public UserDto mapUserEntityToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

}

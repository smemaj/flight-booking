package lhind.flights.booking.service.impl;

import lhind.flights.booking.exception.UserNotFoundException;
import lhind.flights.booking.mapper.UserMapper;
import lhind.flights.booking.model.dto.UserDTO;
import lhind.flights.booking.model.entity.User;
import lhind.flights.booking.model.entity.Role;
import lhind.flights.booking.model.enums.RoleEnum;
import lhind.flights.booking.repository.UserRepository;
import lhind.flights.booking.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.userMapper = new UserMapper();
    }

    @Override
    public List<UserDTO> loadAll() {
        return userRepository.findAll().stream().map(userMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public UserDTO loadById(Long id) throws UserNotFoundException {
        return userRepository.findById(id).map(userMapper::toDto).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public UserDTO storeUser(UserDTO userDTO) {
        User user = new User();

        Role traveller = new Role();
        traveller.setId(2);
        traveller.setRole(RoleEnum.TRAVELLER);
        traveller.setDescription("Basic User");

        List<Role> roles = new ArrayList<>();
        roles.add(traveller);

//        if(EmailValidator.getInstance().isValid(userDTO.getUsername())){
//            user.setUsername(userDTO.getUsername());
//        }else {
//
//        }
        user.setFirstName(userDTO.getFirstName());
        user.setMiddleName(userDTO.getMiddleName());
        user.setLastName(userDTO.getLastName());
        user.setUsername(userDTO.getUsername());
        user.setPassword(encoder.encode(userDTO.getPassword()));
        user.setPhone(userDTO.getPhone());
        user.setAddress(userDTO.getAddress());
        user.setRoles(roles);
        //user.setEmail(userDTO.getEmail());

        return new UserDTO(userRepository.save(user));
    }

    @Override
    public void deleteUserByEmail(String email) throws UserNotFoundException {
        User user = userRepository.findByUsername(email).orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
    }

    @Override
    public void deleteUserById(Long id) throws UserNotFoundException {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) throws UserNotFoundException {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        user.setFirstName(userDTO.getFirstName());
        user.setMiddleName(userDTO.getMiddleName());
        user.setLastName(userDTO.getLastName());
        user.setUsername(userDTO.getUsername());
        user.setPassword(encoder.encode(userDTO.getPassword()));
        user.setPhone(userDTO.getPhone());
        user.setAddress(userDTO.getAddress());

        return new UserDTO(userRepository.save(user));
    }

    @Override
    public UserDTO searchUserByEmail(String email) throws UserNotFoundException {
       return userRepository.findByUsername(email).map(userMapper::toDto).orElseThrow(UserNotFoundException::new);
    }
}

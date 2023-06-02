package lhind.flights.booking.service.impl;

import lhind.flights.booking.exception.UserNotFoundException;
import lhind.flights.booking.mapper.UserMapper;
import lhind.flights.booking.model.dto.MyUserDTO;
import lhind.flights.booking.model.entity.MyUser;
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
    public List<MyUserDTO> loadAll() {
        return userRepository.findAll().stream().map(userMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public MyUserDTO loadById(Long id) throws UserNotFoundException {
        return userRepository.findById(id).map(userMapper::toDto).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public MyUserDTO storeUser(MyUserDTO myUserDTO) {
        MyUser user = new MyUser();

        user.setUsername(myUserDTO.getUsername());
        user.setPassword(encoder.encode(myUserDTO.getPassword()));
        user.setRoles(myUserDTO.getRoles());
        //user.setEmail(myUserDTO.getEmail());
        userRepository.save(user);
        return new MyUserDTO(userRepository.save(user));
    }
}

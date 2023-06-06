package lhind.flights.booking.service.impl;

import lhind.flights.booking.exception.BookingNotFoundException;
import lhind.flights.booking.exception.ExistingEmailException;
import lhind.flights.booking.exception.UserNotFoundException;
import lhind.flights.booking.mapper.UserMapper;
import lhind.flights.booking.model.dto.BookingsResponse;
import lhind.flights.booking.model.dto.UserDTO;
import lhind.flights.booking.model.entity.User;
import lhind.flights.booking.model.entity.Role;
import lhind.flights.booking.model.enums.RoleEnum;
import lhind.flights.booking.repository.BookingRepository;
import lhind.flights.booking.repository.UserRepository;
import lhind.flights.booking.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder encoder;

    private final BookingRepository bookingRepository;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder encoder, BookingRepository bookingRepository) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.bookingRepository = bookingRepository;
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
    public UserDTO storeUser(UserDTO userDTO) throws ExistingEmailException {
        if(userRepository.findByUsername(userDTO.getUsername()).isPresent()){
            throw new ExistingEmailException();
        }
        User user = userMapper.toEntity(userDTO);
        user.setPassword(encoder.encode(userDTO.getPassword()));

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
//        user.setFirstName(userDTO.getFirstName());
//        user.setMiddleName(userDTO.getMiddleName());
//        user.setLastName(userDTO.getLastName());
//        user.setUsername(userDTO.getUsername());
//        user.setPassword(encoder.encode(userDTO.getPassword()));
//        user.setPhone(userDTO.getPhone());
//        user.setAddress(userDTO.getAddress());
//        user.setRoles(roles);
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
    public UserDTO updateUser(Long id, UserDTO userDTO) throws UserNotFoundException, ExistingEmailException {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        if(userRepository.findByUsername(userDTO.getUsername()).isPresent()){
            throw new ExistingEmailException();
        }
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

    @Override
    public List<BookingsResponse> loadAllBookingsForLoggedUser() throws BookingNotFoundException, UserNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(UserNotFoundException::new);
        return bookingRepository.findByUserId(user.getId()).stream().map(BookingsResponse::new).collect(Collectors.toList());
    }

    public Map<String, Object> loadAllBookingsForLoggedUserPageable(int page, int size, String sortBy) throws BookingNotFoundException, UserNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(UserNotFoundException::new);

        List<BookingsResponse> bookings = new ArrayList<BookingsResponse>();
        Pageable paging = PageRequest.of(page, size, Sort.by(sortBy));

        Page<BookingsResponse> pageBookings = bookingRepository.findBookingsCustom(user.getId(),paging).map(BookingsResponse::new);
        bookings = pageBookings.getContent();

        Map<String, Object> response = new HashMap<>();
        response.put("bookings", bookings);
        response.put("currentPage", pageBookings.getNumber());
        response.put("totalItems", pageBookings.getTotalElements());
        response.put("totalPages", pageBookings.getTotalPages());

        return response;
    }
}

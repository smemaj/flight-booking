package lhind.flights.booking.controller;

import io.swagger.v3.oas.annotations.media.Schema;
import lhind.flights.booking.exception.BookingNotFoundException;
import lhind.flights.booking.exception.ExistingEmailException;
import lhind.flights.booking.exception.UserNotFoundException;
import lhind.flights.booking.model.dto.BookingsResponse;
import lhind.flights.booking.model.dto.ResponseBody;
import lhind.flights.booking.model.dto.UserDTO;
import lhind.flights.booking.model.entity.Booking;
import lhind.flights.booking.repository.BookingRepository;
import lhind.flights.booking.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping(path = "/user")
@Schema
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize(value = "hasAnyRole('ADMINISTRATOR')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> getUsers() {
        return ResponseEntity.ok(userService.loadAll());
    }

//    @PreAuthorize(value = "hasAnyRole('ADMINISTRATOR')")
//    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
//    public ResponseEntity<UserDTO> getUserById(@PathVariable(value = "id") Long id) throws UserNotFoundException {
//        return ResponseEntity.ok(userService.loadById(id));
//    }

    @PreAuthorize(value = "hasAnyRole('ADMINISTRATOR')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserDTO> storeUser(@RequestBody UserDTO userDTO) throws ExistingEmailException {
        return ResponseEntity.status(201).body(userService.storeUser(userDTO));
    }

    @PreAuthorize(value = "hasAnyRole('ADMINISTRATOR')")
    @RequestMapping(method = RequestMethod.DELETE, path = "/{email}")
    public ResponseEntity<ResponseBody> deleteUserbyEmail(@PathVariable(value = "email") String email) throws UserNotFoundException {
        userService.deleteUserByEmail(email);
        return ResponseEntity.ok(new ResponseBody("User Deleted!"));
    }

//    @PreAuthorize(value = "hasAnyRole('ADMINISTRATOR')")
//    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
//    public ResponseEntity<String> deleteUserbyId(@PathVariable(value = "id") Long id) throws UserNotFoundException {
//        userService.deleteUserById(id);
//        return ResponseEntity.status(202).body("User deleted!");
//    }

    @PreAuthorize(value = "hasAnyRole('ADMINISTRATOR')")
    @RequestMapping(method = RequestMethod.POST, path = "/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable(value = "id") Long id, @RequestBody UserDTO userDTO) throws UserNotFoundException {
        return ResponseEntity.status(202).body(userService.updateUser(id, userDTO));
    }

    @PreAuthorize(value = "hasAnyRole('ADMINISTRATOR')")
    @RequestMapping(method = RequestMethod.GET, path = "/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable(value = "email") String email) throws UserNotFoundException {
        return ResponseEntity.ok(userService.searchUserByEmail(email));
    }
    @PreAuthorize(value = "hasAnyRole('TRAVELLER')")
    @RequestMapping(method = RequestMethod.GET, path = "/booking")
    public ResponseEntity<List<BookingsResponse>> getBookingsForLoggedUser() throws BookingNotFoundException, UserNotFoundException {
        return ResponseEntity.ok(userService.loadAllBookingsForLoggedUser());
    }

    @PreAuthorize(value = "hasAnyRole('TRAVELLER')")
    @RequestMapping(method = RequestMethod.GET, path = "/booking-logged")
    public ResponseEntity<Map<String, Object>> getBookingsPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) throws UserNotFoundException, BookingNotFoundException {
        return ResponseEntity.ok(userService.loadAllBookingsForLoggedUserPageable(page,size,"booking_time"));
    }

}

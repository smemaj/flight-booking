package lhind.flights.booking.controller;

import io.swagger.v3.oas.annotations.media.Schema;
import lhind.flights.booking.exception.UserNotFoundException;
import lhind.flights.booking.model.dto.UserDTO;
import lhind.flights.booking.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public ResponseEntity<UserDTO> storeUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.status(201).body(userService.storeUser(userDTO));
    }

    @PreAuthorize(value = "hasAnyRole('ADMINISTRATOR')")
    @RequestMapping(method = RequestMethod.DELETE, path = "/{email}")
    public ResponseEntity<String> deleteUserbyEmail(@PathVariable(value = "email") String email) throws UserNotFoundException {
        userService.deleteUserByEmail(email);
        return ResponseEntity.status(202).body("User deleted!");
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
}

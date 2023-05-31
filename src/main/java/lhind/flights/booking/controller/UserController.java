package lhind.flights.booking.controller;

import io.swagger.v3.oas.annotations.media.Schema;
import lhind.flights.booking.exception.UserNotFoundException;
import lhind.flights.booking.model.dto.MyUserDTO;
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
    public ResponseEntity<List<MyUserDTO>> getUsers() {
        return ResponseEntity.ok(userService.loadAll());
    }

    @PreAuthorize(value = "hasAnyRole('ADMINISTRATOR')")
    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<MyUserDTO> getUserById(@PathVariable(value = "id") Long id) throws UserNotFoundException {
        return ResponseEntity.ok(userService.loadById(id));
    }

    @PreAuthorize(value = "hasAnyRole('ADMINISTRATOR')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<MyUserDTO> storeUser(@RequestBody MyUserDTO myUserDTO) {
        return ResponseEntity.status(201).body(userService.storeUser(myUserDTO));
    }
}

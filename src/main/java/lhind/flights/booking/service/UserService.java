package lhind.flights.booking.service;

import lhind.flights.booking.exception.UserNotFoundException;
import lhind.flights.booking.model.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> loadAll();

    UserDTO loadById(Long id) throws UserNotFoundException;

    UserDTO storeUser(UserDTO userDTO);

    void deleteUserByEmail(String email) throws UserNotFoundException;

    void deleteUserById(Long id) throws UserNotFoundException;

    UserDTO updateUser(Long id, UserDTO userDTO) throws UserNotFoundException;

    UserDTO searchUserByEmail(String email) throws UserNotFoundException;
}

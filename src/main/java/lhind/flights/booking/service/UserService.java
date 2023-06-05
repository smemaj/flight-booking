package lhind.flights.booking.service;

import lhind.flights.booking.exception.BookingNotFoundException;
import lhind.flights.booking.exception.ExistingEmailException;
import lhind.flights.booking.exception.UserNotFoundException;
import lhind.flights.booking.model.dto.BookingsResponse;
import lhind.flights.booking.model.dto.UserDTO;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<UserDTO> loadAll();

    UserDTO loadById(Long id) throws UserNotFoundException;

    UserDTO storeUser(UserDTO userDTO) throws ExistingEmailException;

    void deleteUserByEmail(String email) throws UserNotFoundException;

    void deleteUserById(Long id) throws UserNotFoundException;

    UserDTO updateUser(Long id, UserDTO userDTO) throws UserNotFoundException;

    UserDTO searchUserByEmail(String email) throws UserNotFoundException;

    List<BookingsResponse> loadAllBookingsForLoggedUser() throws BookingNotFoundException, UserNotFoundException;
    Map<String, Object> loadAllBookingsForLoggedUserPageable(int page, int size, String sortBy)throws BookingNotFoundException, UserNotFoundException;
}

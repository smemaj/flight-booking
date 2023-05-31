package lhind.flights.booking.service;

import lhind.flights.booking.exception.UserNotFoundException;
import lhind.flights.booking.model.dto.MyUserDTO;

import java.util.List;

public interface UserService {

    List<MyUserDTO> loadAll();

    MyUserDTO loadById(Long id) throws UserNotFoundException;

    MyUserDTO storeUser(MyUserDTO myUserDTO);
}

package lhind.flights.booking.service;

import lhind.flights.booking.exception.*;
import lhind.flights.booking.model.dto.BookingDTO;
import lhind.flights.booking.model.dto.BookingsResponse;
import lhind.flights.booking.model.dto.ReasonDTO;

import java.util.List;

public interface BookingService {
    List<BookingsResponse> loadAllBookingsForUser(Long id) throws BookingNotFoundException;

    //UserDTO storeUser(UserDTO userDTO);

    BookingDTO newBooking(BookingDTO bookingDTO);

    void cancelBooking(Long id) throws BookingNotFoundException, UserNotFoundException, NoSuchBookingForUserException, BookingNotInRightStatus;
    void approveCancellation(Long id) throws BookingNotFoundException, CannotApproveRequestExcpetion;
    void declineCancellation(Long id,  ReasonDTO reasonDTO) throws BookingNotFoundException, DeclineReasonIsNullException, CannotDeclineRequestExcpetion;
}

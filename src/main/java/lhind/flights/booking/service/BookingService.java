package lhind.flights.booking.service;

import lhind.flights.booking.exception.BookingNotFoundException;
import lhind.flights.booking.model.dto.BookingsResponse;

import java.util.List;

public interface BookingService {
    List<BookingsResponse> loadAllBookingsForUser(Long id) throws BookingNotFoundException;
}

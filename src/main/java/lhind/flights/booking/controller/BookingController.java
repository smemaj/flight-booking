package lhind.flights.booking.controller;

import io.swagger.v3.oas.annotations.media.Schema;
import lhind.flights.booking.exception.BookingNotFoundException;
import lhind.flights.booking.model.dto.BookingDTO;
import lhind.flights.booking.model.dto.BookingsResponse;
import lhind.flights.booking.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/bookings")
@Schema
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PreAuthorize(value = "hasAnyRole('ADMINISTRATOR')")
    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<List<BookingsResponse>> getBookingsForUser(@PathVariable(value = "id") Long id) throws BookingNotFoundException {
        return ResponseEntity.ok(bookingService.loadAllBookingsForUser(id));
    }
}

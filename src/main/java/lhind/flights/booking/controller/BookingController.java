package lhind.flights.booking.controller;

import io.swagger.v3.oas.annotations.media.Schema;
import lhind.flights.booking.exception.*;
import lhind.flights.booking.model.dto.BookingDTO;
import lhind.flights.booking.model.dto.BookingsResponse;
import lhind.flights.booking.model.dto.ReasonDTO;
import lhind.flights.booking.model.dto.ResponseBody;
import lhind.flights.booking.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @PreAuthorize(value = "hasAnyRole('TRAVELLER')")
    @RequestMapping(method = RequestMethod.POST, path = "/cancel/{id}")
    public ResponseEntity<ResponseBody> cancelBooking(@PathVariable(value = "id") Long id) throws BookingNotFoundException, UserNotFoundException, BookingNotInRightStatus, NoSuchBookingForUserException {
        bookingService.cancelBooking(id);
        return ResponseEntity.ok(new ResponseBody("Request to cancel booking submitted"));
    }

    @PreAuthorize(value = "hasAnyRole('ADMINISTRATOR')")
    @RequestMapping(method = RequestMethod.POST, path = "/approve/{id}")
    public ResponseEntity<ResponseBody> approveCancellation(@PathVariable(value = "id") Long id) throws BookingNotFoundException, CannotApproveRequestExcpetion {
        bookingService.approveCancellation(id);
        return ResponseEntity.ok(new ResponseBody("Request to cancel booking APPROVED!"));
    }

    @PreAuthorize(value = "hasAnyRole('ADMINISTRATOR')")
    @RequestMapping(method = RequestMethod.POST, path = "/decline/{id}")
    public ResponseEntity<ResponseBody> declineCancellation(@PathVariable(value = "id") Long id, @RequestBody ReasonDTO reasonDTO) throws BookingNotFoundException, DeclineReasonIsNullException, CannotDeclineRequestExcpetion {
        bookingService.declineCancellation(id, reasonDTO);
        return ResponseEntity.ok(new ResponseBody("Request to cancel booking DECLINED!"));
    }
}

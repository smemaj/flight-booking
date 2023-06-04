package lhind.flights.booking.service.impl;

import lhind.flights.booking.exception.BookingNotFoundException;
import lhind.flights.booking.mapper.BookingMapper;
import lhind.flights.booking.model.dto.BookingDTO;
import lhind.flights.booking.model.dto.BookingsResponse;
import lhind.flights.booking.repository.BookingRepository;
import lhind.flights.booking.service.BookingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
        this.bookingMapper = new BookingMapper();
    }

    @Override
    public List<BookingsResponse> loadAllBookingsForUser(Long id) throws BookingNotFoundException {
        return bookingRepository.findByUserId(id).stream().map(BookingsResponse::new).collect(Collectors.toList());
    }

    @Override
    public BookingDTO newBooking(BookingDTO bookingDTO) {
        return null;
    }
}

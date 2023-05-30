package lhind.flights.booking.mapper;

import lhind.flights.booking.model.dto.BookingDTO;
import lhind.flights.booking.model.entity.Booking;

public class BookingMapper extends AbstractMapper<Booking, BookingDTO>{
    @Override
    public Booking toEntity(BookingDTO bookingDTO) {
        if(bookingDTO==null){
            return null;
        }

        Booking booking = new Booking();
        booking.setId(bookingDTO.getId());
        booking.setBookingTime(bookingDTO.getBookingTime());
        booking.setUser(bookingDTO.getUser());
        return booking;
    }

    @Override
    public BookingDTO toDto(Booking booking) {
        if(booking==null){
            return null;
        }

        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setId(booking.getId());
        bookingDTO.setBookingTime(booking.getBookingTime());
        bookingDTO.setUser(booking.getUser());
        return bookingDTO;
    }
}

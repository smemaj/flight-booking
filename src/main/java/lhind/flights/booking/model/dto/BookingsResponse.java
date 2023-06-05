package lhind.flights.booking.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lhind.flights.booking.model.entity.Booking;
import lhind.flights.booking.model.entity.Flight;
import lhind.flights.booking.model.enums.BookingStatusEnum;

import java.util.Date;
import java.util.List;

@JsonSerialize
public class BookingsResponse {
    private Long id;
    private Date bookingTime;
    private BookingStatusEnum bookingStatus;

    private List<Flight> flightList;;

    public BookingsResponse(Booking booking) {
        this.setId(booking.getId());
        this.setBookingTime(booking.getBookingTime());
        this.setBookingStatus(booking.getBookingStatus());
        this.setFlightList(booking.getFlights());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(Date bookingTime) {
        this.bookingTime = bookingTime;
    }

    public BookingStatusEnum getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatusEnum bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public List<Flight> getFlightList() {
        return flightList;
    }

    public void setFlightList(List<Flight> flightList) {
        this.flightList = flightList;
    }
}

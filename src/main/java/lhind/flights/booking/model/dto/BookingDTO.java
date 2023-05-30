package lhind.flights.booking.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lhind.flights.booking.model.entity.Booking;
import lhind.flights.booking.model.entity.MyUser;

import java.util.Date;

@JsonSerialize
public class BookingDTO {

    private Long id;
    private Date bookingTime;
    private MyUser user;

    public BookingDTO() {
    }

    public BookingDTO(Booking booking) {
        this.setId(booking.getId());
        this.setBookingTime(booking.getBookingTime());
        this.setUser(booking.getUser());
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

    public MyUser getUser() {
        return user;
    }

    public void setUser(MyUser user) {
        this.user = user;
    }
}

package lhind.flights.booking.model.entity;

import jakarta.persistence.*;
import lhind.flights.booking.model.enums.BookingStatusEnum;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "booking_time", nullable = false)
    @Temporal(value = TemporalType.TIME)
    private Date bookingTime;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "booking_flight",//gabim
            joinColumns = @JoinColumn(name = "booking_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "flight_id", referencedColumnName = "id")
    )
    private List<Flight> flights;
    @Column(name = "booking_status")
    @Enumerated(value = EnumType.STRING)
    private BookingStatusEnum bookingStatus;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Flight> getFlights() {
        if (flights == null) {// same thing for hash map and set
            flights = new ArrayList<>();
        }
        return flights;
    }

    public BookingStatusEnum getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatusEnum bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
}

package lhind.flights.booking.service.impl;

import lhind.flights.booking.exception.*;
import lhind.flights.booking.mapper.BookingMapper;
import lhind.flights.booking.model.dto.BookingDTO;
import lhind.flights.booking.model.dto.BookingsResponse;
import lhind.flights.booking.model.dto.ReasonDTO;
import lhind.flights.booking.model.entity.Booking;
import lhind.flights.booking.model.entity.User;
import lhind.flights.booking.model.enums.BookingStatusEnum;
import lhind.flights.booking.repository.BookingRepository;
import lhind.flights.booking.repository.UserRepository;
import lhind.flights.booking.service.BookingService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final BookingMapper bookingMapper;

    public BookingServiceImpl(BookingRepository bookingRepository, UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
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

    @Override
    public void cancelBooking(Long id) throws BookingNotFoundException, UserNotFoundException, NoSuchBookingForUserException, BookingNotInRightStatus {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(UserNotFoundException::new);

        Booking booking = bookingRepository.findById(id).orElseThrow(BookingNotFoundException::new);

        if(booking.getUser().getId()==user.getId()){
            if(booking.getBookingStatus().equals(BookingStatusEnum.BOOKED)) {
                booking.setBookingStatus(BookingStatusEnum.REQUESTED_FOR_CANCELLATION);
                bookingRepository.save(booking);
            }else {
                throw new BookingNotInRightStatus();
            }
        }else {
            throw new NoSuchBookingForUserException();
        }

    }

    @Override
    public void approveCancellation(Long id) throws BookingNotFoundException, CannotApproveRequestExcpetion {
        Booking booking = bookingRepository.findById(id).orElseThrow(BookingNotFoundException::new);

        if(booking.getBookingStatus().equals(BookingStatusEnum.REQUESTED_FOR_CANCELLATION)) {
            booking.setBookingStatus(BookingStatusEnum.CANCELLED);
            bookingRepository.save(booking);
        }else {
            throw new CannotApproveRequestExcpetion();
        }


    }

    @Override
    public void declineCancellation(Long id, ReasonDTO reasonDTO) throws BookingNotFoundException, DeclineReasonIsNullException, CannotDeclineRequestExcpetion {
        Booking booking = bookingRepository.findById(id).orElseThrow(BookingNotFoundException::new);
        if(reasonDTO.getReason()==null){
            throw new DeclineReasonIsNullException();
        }else {
            if (booking.getBookingStatus().equals(BookingStatusEnum.REQUESTED_FOR_CANCELLATION)) {
                booking.setBookingStatus(BookingStatusEnum.REJECTED_CANCELLATION);
                booking.setDeclineReason(reasonDTO.getReason());
                bookingRepository.save(booking);
            } else {
                throw new CannotDeclineRequestExcpetion();
            }
        }


    }
}

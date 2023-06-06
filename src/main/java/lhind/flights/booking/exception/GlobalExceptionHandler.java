package lhind.flights.booking.exception;

import lhind.flights.booking.model.dto.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = AuthenticationException.class)
    public ResponseEntity<BaseResponse> handleAuthenticationException(AuthenticationException e) {
        LOGGER.info("Invalid login information");
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessages(List.of("Username or password is incorrect"));
        return ResponseEntity.status(403).body(baseResponse);
    }

    @ExceptionHandler(value = FlightIsBookedException.class)
    public ResponseEntity<BaseResponse> handleFlightBookedException(FlightIsBookedException e) {
        LOGGER.info("Flight cannot be deleted as it is already booked");
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessages(List.of("Flight cannot be deleted as it is already booked"));
        return ResponseEntity.status(403).body(baseResponse);
    }

    @ExceptionHandler(value = ExistingEmailException.class)
    public ResponseEntity<BaseResponse> handleEmailException(ExistingEmailException e) {
        LOGGER.info("User with this email already exists");
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessages(List.of("User with this email already exists"));
        return ResponseEntity.status(403).body(baseResponse);
    }

    @ExceptionHandler(value = NoSuchBookingForUserException.class)
    public ResponseEntity<BaseResponse> handleNoSuchBookingException(NoSuchBookingForUserException e) {
        LOGGER.info("There is no such booking for the logged in user");
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessages(List.of("There is no such booking for the logged in user"));
        return ResponseEntity.status(403).body(baseResponse);
    }

    @ExceptionHandler(value = BookingNotInRightStatus.class)
    public ResponseEntity<BaseResponse> handleCancellationRequestException(BookingNotInRightStatus e) {
        LOGGER.info("Cannot request for cancellation. Booking should have status BOOKED");
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessages(List.of("Cannot request for cancellation. Booking should have status BOOKED"));
        return ResponseEntity.status(403).body(baseResponse);
    }

    @ExceptionHandler(value = CannotApproveRequestExcpetion.class)
    public ResponseEntity<BaseResponse> handleCancellationApprovalException(CannotApproveRequestExcpetion e) {
        LOGGER.info("Cannot approve cancellation request. Booking should be in REQUESTED_FOR_CANCELLATION status");
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessages(List.of("Cannot approve cancellation request. Booking should be in REQUESTED_FOR_CANCELLATION status"));
        return ResponseEntity.status(403).body(baseResponse);
    }

    @ExceptionHandler(value = CannotDeclineRequestExcpetion.class)
    public ResponseEntity<BaseResponse> handleCancellationDeclineException(CannotDeclineRequestExcpetion e) {
        LOGGER.info("Cannot decline cancellation request. Booking should be in REQUESTED_FOR_CANCELLATION status");
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessages(List.of("Cannot decline cancellation request. Booking should be in REQUESTED_FOR_CANCELLATION status"));
        return ResponseEntity.status(403).body(baseResponse);
    }

    @ExceptionHandler(value = IncorrectFlightNumberException.class)
    public ResponseEntity<BaseResponse> handleIncorrectFlightNumberException(IncorrectFlightNumberException e) {
        LOGGER.info("Incorrect Flight Number");
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessages(List.of("Incorrect Flight Number"));
        return ResponseEntity.status(403).body(baseResponse);
    }

    @ExceptionHandler(value = DeclineReasonIsNullException.class)
    public ResponseEntity<BaseResponse> handleMissingReasonException(DeclineReasonIsNullException e) {
        LOGGER.info("Cannot decline cancellation as reason for declining is not provided");
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessages(List.of("Cannot decline cancellation as reason for declining is not provided"));
        return ResponseEntity.status(403).body(baseResponse);
    }

    @ExceptionHandler(value = SameOriginAndDestinationException.class)
    public ResponseEntity<BaseResponse> handleOriginAndDestinationException(SameOriginAndDestinationException e) {
        LOGGER.info("Flight cannot have same origin and destination");
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessages(List.of("Flight cannot have same origin and destination"));
        return ResponseEntity.status(403).body(baseResponse);
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<BaseResponse> handleUserNotFoundException(UserNotFoundException e) {
        LOGGER.warn("User was not found");
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessages(List.of("User was not found"));
        return ResponseEntity.status(404).body(baseResponse);
    }

    @ExceptionHandler(value = IncorrectLengthException.class)
    public ResponseEntity<BaseResponse> handleIncorrectLengthException(IncorrectLengthException e) {
        LOGGER.warn("Length must be 3");
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessages(List.of("Length must be 3"));
        return ResponseEntity.status(404).body(baseResponse);
    }

    @ExceptionHandler(value = FlightDateException.class)
    public ResponseEntity<BaseResponse> handleFLightDateException(FlightDateException e) {
        LOGGER.warn("Flight date cannot be before today's date");
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessages(List.of("Flight date cannot be before today's date"));
        return ResponseEntity.status(404).body(baseResponse);
    }

    @ExceptionHandler(value = DepartureTimeException.class)
    public ResponseEntity<BaseResponse> handleDepartureTimeException(DepartureTimeException e) {
        LOGGER.warn("Departure time cannot be earlier than current time");
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessages(List.of("Departure time cannot be earlier than current time"));
        return ResponseEntity.status(404).body(baseResponse);
    }

    @ExceptionHandler(value = BookingNotFoundException.class)
    public ResponseEntity<BaseResponse> handleBookingNotFoundException( BookingNotFoundException e) {
        LOGGER.warn("Booking was not found");
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessages(List.of("Booking was not found"));
        return ResponseEntity.status(404).body(baseResponse);
    }

    @ExceptionHandler(value = FlightNotFoundException.class)
    public ResponseEntity<BaseResponse> handleFlightNotFoundException(FlightNotFoundException e) {
        LOGGER.warn("Flight was not found");
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessages(List.of("Flight was not found"));
        return ResponseEntity.status(404).body(baseResponse);
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<BaseResponse> handleRuntimeException(RuntimeException e) {
        LOGGER.error("", e);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessages(List.of("Something went wrong"));
        return ResponseEntity.status(500).body(baseResponse);
    }


}

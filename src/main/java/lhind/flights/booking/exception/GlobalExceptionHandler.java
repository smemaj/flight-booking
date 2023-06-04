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

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<BaseResponse> handleUserNotFoundException(UserNotFoundException e) {
        LOGGER.warn("User was not found");
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessages(List.of("User was not found"));
        return ResponseEntity.status(404).body(baseResponse);
    }

    @ExceptionHandler(value = BookingNotFoundException.class)
    public ResponseEntity<BaseResponse> handleBookingNotFoundException( BookingNotFoundException e) {
        LOGGER.warn("Booking was not found");
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessages(List.of("Booking was not found"));
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

package lhind.flights.booking.repository;

import lhind.flights.booking.model.dto.TravellerInfo;
import lhind.flights.booking.model.entity.Flight;
import lhind.flights.booking.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {


//    @Query(value = "SELECT C.id, C.first_name, C.last_name, C.email " +
//            "from (booking as A inner join booking_flight as B on A.id = B.booking_id) " +
//            "inner join user as C on C.id = A.user_id where flight_id = :id", nativeQuery = true)
//    List<User> findByFlightId(Long id);
}

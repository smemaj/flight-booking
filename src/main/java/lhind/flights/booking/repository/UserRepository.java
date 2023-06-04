package lhind.flights.booking.repository;


import lhind.flights.booking.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Query(value = "SELECT C.* " +
            "from (booking as A inner join booking_flight as B on A.id = B.booking_id) " +
            "inner join user as C on C.id = A.user_id where flight_id = :id", nativeQuery = true)
    List<User> findByFlightId(Long id);

}

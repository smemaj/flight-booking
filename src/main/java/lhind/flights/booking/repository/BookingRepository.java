package lhind.flights.booking.repository;

import lhind.flights.booking.model.entity.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByUserId(Long id);

    @Query(value = "SELECT * FROM booking where " +
            "user_id = :id ", nativeQuery = true)
    Page<Booking> findBookingsCustom(Long id, Pageable pageable);
}

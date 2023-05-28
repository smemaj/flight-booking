package lhind.flights.booking.model.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "user")
public class MyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "address")
    private String address;

}

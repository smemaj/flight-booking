package lhind.flights.booking.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lhind.flights.booking.model.entity.MyUser;
import lhind.flights.booking.model.entity.Role;

import java.util.List;

@JsonSerialize
public class MyUserDTO {

    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private String address;
    private List<Role> roles;

    public MyUserDTO() {
    }

    public MyUserDTO(MyUser user) {
        this.setId(user.getId());
        this.setFirstName(user.getFirstName());
        this.setMiddleName(user.getMiddleName());
        this.setLastName(user.getLastName());
        this.setEmail(user.getEmail());
        this.setPassword(user.getPassword());
        this.setPhone(user.getPhone());
        this.setAddress(user.getAddress());
        this.setRoles(user.getRoles());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}

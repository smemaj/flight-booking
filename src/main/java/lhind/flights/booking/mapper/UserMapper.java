package lhind.flights.booking.mapper;

import lhind.flights.booking.model.dto.UserDTO;
import lhind.flights.booking.model.entity.User;

public class UserMapper extends AbstractMapper<User, UserDTO>{
    @Override
    public User toEntity(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        User user = new User();
        user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setMiddleName(userDTO.getMiddleName());
        user.setLastName(userDTO.getLastName());
        user.setUsername(userDTO.getUsername());
        //user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setPhone(userDTO.getPhone());
        user.setAddress(userDTO.getAddress());
        user.setRoles(userDTO.getRoles());
        return user;

    }

    @Override
    public UserDTO toDto(User user) {
        if(user == null){
            return null;
        }
        UserDTO userDTO =new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setMiddleName(user.getMiddleName());
        userDTO.setLastName(user.getLastName());
        userDTO.setUsername(user.getUsername());
        //userDTO.setEmail(user.getEmail());
//        userDTO.setPassword(user.getPassword());
        userDTO.setPhone(user.getPhone());
        userDTO.setAddress(user.getAddress());
        userDTO.setRoles(user.getRoles());
        return userDTO;
    }
}

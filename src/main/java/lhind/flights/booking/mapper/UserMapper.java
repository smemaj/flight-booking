package lhind.flights.booking.mapper;

import lhind.flights.booking.model.dto.MyUserDTO;
import lhind.flights.booking.model.entity.MyUser;

public class UserMapper extends AbstractMapper<MyUser, MyUserDTO>{
    @Override
    public MyUser toEntity(MyUserDTO myUserDTO) {
        if (myUserDTO == null) {
            return null;
        }
        MyUser myUser = new MyUser();
        myUser.setId(myUserDTO.getId());
        myUser.setFirstName(myUserDTO.getFirstName());
        myUser.setMiddleName(myUserDTO.getMiddleName());
        myUser.setLastName(myUserDTO.getLastName());
        myUser.setUsername(myUserDTO.getUsername());
        //myUser.setEmail(myUserDTO.getEmail());
        myUser.setPassword(myUserDTO.getPassword());
        myUser.setPhone(myUserDTO.getPhone());
        myUser.setAddress(myUserDTO.getAddress());
        myUser.setRoles(myUserDTO.getRoles());
        return myUser;

    }

    @Override
    public MyUserDTO toDto(MyUser myUser) {
        if(myUser == null){
            return null;
        }
        MyUserDTO myUserDTO =new MyUserDTO();
        myUserDTO.setId(myUser.getId());
        myUserDTO.setFirstName(myUser.getFirstName());
        myUserDTO.setMiddleName(myUser.getMiddleName());
        myUserDTO.setLastName(myUser.getLastName());
        myUserDTO.setUsername(myUser.getUsername());
        //myUserDTO.setEmail(myUser.getEmail());
        myUserDTO.setPassword(myUser.getPassword());
        myUserDTO.setPhone(myUser.getPhone());
        myUserDTO.setAddress(myUser.getAddress());
        myUserDTO.setRoles(myUser.getRoles());
        return myUserDTO;
    }
}

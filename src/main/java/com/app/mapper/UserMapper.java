package com.app.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.app.domain.User;
import com.app.dto.UserDTO;



@Mapper(componentModel = "spring")
public interface UserMapper {
	
	UserDTO userToUserDTO(User user);
	
	List<UserDTO> map(List<User> userList);
	
}

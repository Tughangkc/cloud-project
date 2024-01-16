package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.domain.Role;
import com.app.domain.enums.RoleType;
import com.app.exception.ResourceNotFoundException;
import com.app.exception.message.ErrorMessage;
import com.app.repository.RoleRepository;



@Service
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	public Role findByType(RoleType roleType) {
		Role role =  roleRepository.findByType(roleType).orElseThrow(()->
		       new ResourceNotFoundException(String.format(
		    		   ErrorMessage.ROLE_NOT_FOUND_MESSAGE, roleType.name())));
		
		return role ;
		
	}

}

package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.domain.Role;
import com.app.domain.enums.RoleType;



@Repository
public interface RoleRepository  extends JpaRepository<Role, Integer>{
	
	Optional<Role> findByType(RoleType type);

}

package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.domain.ContactMessage;


@Repository
public interface ContactMessageRepository  extends JpaRepository<ContactMessage, Long>{

}

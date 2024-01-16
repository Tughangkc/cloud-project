package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.domain.ContactMessage;
import com.app.exception.ResourceNotFoundException;
import com.app.exception.message.ErrorMessage;
import com.app.repository.ContactMessageRepository;



@Service
public class ContactMessageService {
	
	private ContactMessageRepository contactMessageRepository;
	
	
@Autowired
	public ContactMessageService(ContactMessageRepository contactMessageRepository) {
		this.contactMessageRepository = contactMessageRepository;
	}



	public void saveMessage(ContactMessage contactMessage) {
		contactMessageRepository.save(contactMessage);
		
	}



	public List<ContactMessage> getAll() {
		return contactMessageRepository.findAll();
	}



	public Page<ContactMessage> getAll(Pageable pageable) {
		 return contactMessageRepository.findAll(pageable);
	}



	public ContactMessage getContactMessage(Long id) {
		return contactMessageRepository.findById(id).orElseThrow( ()-> 
					// new  ResourceNotFoundException("ContactMessage is not foung with id: ")
		          new  ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, id))
		          );
				
	
	}



	public void deleteContactMessage(Long id) {
		ContactMessage foundContactMessage =  getContactMessage(id);
		
		contactMessageRepository.delete(foundContactMessage);
		
		
	}



	public void updateContactMessage(Long id, ContactMessage contactMessage) {
		ContactMessage foundContactMessage =  getContactMessage(id);
		
		foundContactMessage.setName( contactMessage.getName());
		foundContactMessage.setBody(contactMessage.getBody());
		foundContactMessage.setEmail(contactMessage.getEmail());
		foundContactMessage.setSubject(contactMessage.getSubject());
		
		contactMessageRepository.save(foundContactMessage);
		
		
	}
}

package com.app.dto;

import java.time.LocalDateTime;

import com.app.domain.enums.ReservationStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ReservationDTO {
	
	private Long id;
	
	
	private CarDTO car; // araç bilgisi DTO olarak dönecek
	
	
	private Long userId ;
	
	
	private LocalDateTime pickUpTime;
	
	
	private LocalDateTime dropOffTime;
	
	
	private String pickUpLocation;
	
	
	private String dropOffLocation;
	
	
	private ReservationStatus status;
	

	private Double totalPrice;
	

}

package com.app.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.domain.Car;
import com.app.domain.Reservation;
import com.app.domain.User;
import com.app.exception.message.ErrorMessage;
import com.app.report.ExcellReporter;


@Service
public class ReportService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CarService carService;
	
	@Autowired
	private ReservationService reservationService;

	
	
	// **************************** USER *********************
	
	public ByteArrayInputStream getUserReport() {
		
		List<User> users =  userService.getUsers();
		
		try {
			return ExcellReporter.getUserExcelReport(users);
		} catch (IOException e) {
			 throw new RuntimeException(ErrorMessage.EXCEL_REPORT_ERROR_MESSAGE);
		}
	
	
	}

//************************* CAR *****************************

	public ByteArrayInputStream getCarReport() {
		
		List<Car> cars = carService.getAllCar();
		
		try {
			return ExcellReporter.getCarExcelReport(cars);
		} catch (IOException e) {
			throw new RuntimeException(ErrorMessage.EXCEL_REPORT_ERROR_MESSAGE);
		}
	}

	
	//*******************RESERVATION ****************
	
	public ByteArrayInputStream getReservationReport() {
		
		List<Reservation> reservations = reservationService.getAll();
		
		try {
			return ExcellReporter.getReservationExcelReport(reservations);
		} catch (IOException e) {
			throw new RuntimeException(ErrorMessage.EXCEL_REPORT_ERROR_MESSAGE);
		}
	}

}

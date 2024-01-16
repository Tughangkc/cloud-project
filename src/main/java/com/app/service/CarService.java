package com.app.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.domain.Car;
import com.app.domain.ImageFile;
import com.app.dto.CarDTO;
import com.app.exception.BadRequestException;
import com.app.exception.ConflictException;
import com.app.exception.ResourceNotFoundException;
import com.app.exception.message.ErrorMessage;
import com.app.mapper.CarMapper;
import com.app.repository.CarRepository;



@Service
public class CarService {

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private ImageFileService imageFileService;

	@Autowired
	private CarMapper carMapper;
	
	@Autowired
	private ReservationService reservationService;

	// *************************************

	public void saveCar(String imageId, CarDTO carDTO) {

		// image ID imageRepo da var mı ?
		ImageFile imageFile = imageFileService.findImageById(imageId);
		// imageId daha önce başka bir araç ile eşleşmiş mi
		Integer usedCarCount = carRepository.findCarCountByImageId(imageFile.getId());

		if (usedCarCount > 0) {
			throw new ConflictException(ErrorMessage.IMAGE_USED_MESSAGE);
		}

		// mapper işlemi
		Car car = carMapper.carDTOToCar(carDTO);

		Set<ImageFile> imFiles = new HashSet<>();
		imFiles.add(imageFile);

		car.setImage(imFiles);

		carRepository.save(car);

	}

	// ********************************

	public List<CarDTO> getAllCars() {

		List<Car> carList = carRepository.findAll();
		return carMapper.map(carList);

	}

	// ***********************************

	public Page<CarDTO> findAllWithPage(Pageable pageable) {

		Page<Car> carPage = carRepository.findAll(pageable);
		Page<CarDTO> carPageDTO = carPage.map(new Function<Car, CarDTO>() {
			@Override
			public CarDTO apply(Car car) {
				return carMapper.carToCarDTO(car);
			}
		});
		return carPageDTO;
	}

	// *************************************
	public CarDTO findById(Long id) {

		Car car = getCar(id);

		return carMapper.carToCarDTO(car);

	}

	public Car getCar(Long id) {
		Car car = carRepository.findCarById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, id)));
		return car;
	}

	// *********************************************************************************

	public void updateCar(Long id, String imageId, CarDTO carDTO) {
		
		Car car = getCar(id);
		
		if(car.getBuiltIn()) { // builtIn kontrolu
			throw new BadRequestException(ErrorMessage.NOT_PERMITTED_METHOD_MESSAGE);
		}
		
		ImageFile imageFile  =  imageFileService.findImageById(imageId);
		
		// burada amaç, verilen image daha önce başka araç için kullanılmış mı ?
		List<Car> carList = carRepository.findCarsByImageId(imageFile.getId());
		
		
		for(Car c: carList) {
			// bana gelen car Id si ile yukardakiList türündeki car Id leri eşit olmaları lazım,
			//eğer eşit değilse girilenm image başka bir araç için yüklenmiş
			if(car.getId().longValue()!=c.getId().longValue()) {
				throw new ConflictException(ErrorMessage.IMAGE_USED_MESSAGE);
			}
			
		}
		
		car.setAge(carDTO.getAge());
		car.setAirConditioning(carDTO.getAirConditioning());
		car.setBuiltIn(carDTO.getBuiltIn());
		car.setDoors(carDTO.getDoors());
		car.setFuelType(carDTO.getFuelType());
		car.setLuggage(carDTO.getLuggage());
		car.setModel(carDTO.getModel());
		car.setPricePerHour(carDTO.getPricePerHour());
		car.setSeats(carDTO.getSeats());
		car.setTransmission(carDTO.getTransmission());
		
		car.getImage().add(imageFile);
		
		carRepository.save(car);

	}

	//******************** DELETE**********************
	
	public void removeById(Long id) {
		
		Car car = getCar(id);
		
		if(car.getBuiltIn()) { // builtIn kontrolu
			throw new BadRequestException(ErrorMessage.NOT_PERMITTED_METHOD_MESSAGE);
		}
		
		 boolean exist =  reservationService.existsByCar(car);
		 
		 // reservasyon kontrolü
		 if(exist) {
			 throw new BadRequestException(ErrorMessage.CAR_USED_BY_RESERVATION_MESSAGE);
		 }
		
		
		carRepository.delete(car);
	
		
	}
	
	// ---> EKLENDİ
	public Car getCarById(Long id) {
		Car car= carRepository.findById(id).orElseThrow(()->new 
				ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, id)));
		return car;
	}

	public List<Car> getAllCar() {
		return carRepository.getAllBy();
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

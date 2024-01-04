package com.driver.Service;

import com.driver.Repository.PassengerRepository;
import com.driver.model.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PassengerService {
    @Autowired
    PassengerRepository passengerRepository;

    public void addPassenger(Passenger passenger){
        passengerRepository.addPassenger(passenger);
    }
}

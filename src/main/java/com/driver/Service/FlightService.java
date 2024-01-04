package com.driver.Service;

import com.driver.Repository.FlightRepository;
import com.driver.model.City;
import com.driver.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class FlightService {
    @Autowired
    FlightRepository flightRepository;

    public void addFlight(Flight flight){
        flightRepository.addFlight(flight);
    }

    public String bookATicket(Integer flightId,Integer passengerId){
       return flightRepository.bookATicket(flightId,passengerId);
    }

    public String cancelATicket(int flightId,int passengerId){
        return flightRepository.cancelATicket(flightId,passengerId);
    }

    public int countOfBookingsDoneByPassengerAllCombined(Integer passengerId){
        return flightRepository.countOfBookingsDoneByPassengerAllCombined(passengerId);
    }

    public int getRevenueOfAFlight(Integer flightId){
        return flightRepository.getRevenueOfAFlight(flightId);
    }

    public String getAirportNameFromFlightId(Integer flightId){
        return flightRepository.getAirportNameFromFlightId(flightId);
    }

    public int calculateFlightFare(Integer flightId){
        return flightRepository.calculateFlightFare(flightId);
    }

    public int getNumberOfPeopleOn(Date date , String airPortName){
        return flightRepository.getNumberOfPeopleOn(date,airPortName);
    }

    public double getShortestDurationOfPossibleBetweenTwoCities(City from , City to){
        return flightRepository.getShortestDurationOfPossibleBetweenTwoCities(from,to);
    }

}

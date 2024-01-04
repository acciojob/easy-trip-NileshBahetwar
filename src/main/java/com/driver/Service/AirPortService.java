package com.driver.Service;

import com.driver.Repository.AirportRepository;
import com.driver.model.Airport;
import com.driver.model.City;
import com.driver.model.Flight;
import com.driver.model.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

@Service
public class AirPortService {
    @Autowired
    AirportRepository airportRepository;

    public void addAirport(Airport airport){
        airportRepository.addAirport(airport);
    }
    public void addPassenger(Passenger passenger){
        airportRepository.addPassenger(passenger);
    }

    public String getLargestAirportName(){
        HashMap<String,Airport>map=airportRepository.getLargestAirportName();
        String airPortName="";
        int terminal=0;
        for(String key : map.keySet()) {
            int currTerminal=map.get(key).getNoOfTerminals();
            if(currTerminal>terminal){
                airPortName=key;
                terminal=currTerminal;
            }
            else if(currTerminal==terminal){

                String s1=airPortName;
                String s2=map.get(key).getAirportName();
                if(s1Smaller(s1,s2)){
                    airPortName=s1;
                    terminal=currTerminal;
                }
                else{
                    airPortName=s2;
                    terminal=currTerminal;
                }

            }
        }
        return airPortName;
    }
    public boolean s1Smaller(String s1,String s2){
        int len=Math.min(s1.length(),s2.length());
        for(int i=0;i<len;i++){
            char c1=s1.charAt(i);
            char c2=s2.charAt(i);

            if(c1==c2)  continue;
            if(c1<c2)  return true;
        }
        return false;
    }


    public void addFlight(Flight flight){
        airportRepository.addFlight(flight);
    }

    public String bookATicket(Integer flightId,Integer passengerId){
        return airportRepository.bookATicket(flightId,passengerId);
    }

    public String cancelATicket(int flightId,int passengerId){
        return airportRepository.cancelATicket(flightId,passengerId);
    }

    public int countOfBookingsDoneByPassengerAllCombined(Integer passengerId){
        return airportRepository.countOfBookingsDoneByPassengerAllCombined(passengerId);
    }

    public int getRevenueOfAFlight(Integer flightId){
        return airportRepository.getRevenueOfAFlight(flightId);
    }

    public String getAirportNameFromFlightId(Integer flightId){
        return airportRepository.getAirportNameFromFlightId(flightId);
    }

    public int calculateFlightFare(Integer flightId){
        return airportRepository.calculateFlightFare(flightId);
    }

    public int getNumberOfPeopleOn(Date date , String airPortName){
        return airportRepository.getNumberOfPeopleOn(date,airPortName);
    }

    public double getShortestDurationOfPossibleBetweenTwoCities(City from , City to){
        return airportRepository.getShortestDurationOfPossibleBetweenTwoCities(from,to);
    }

}

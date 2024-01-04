package com.driver.Repository;

import com.driver.model.Airport;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class AirportRepository {
    HashMap<String,Airport> airportDB;

    public AirportRepository(){
        this.airportDB=new HashMap<>();
    }


    public void addAirport(Airport airport){
       String airPortName= airport.getAirportName();
       airportDB.put(airPortName,airport);
    }

    public HashMap<String,Airport> getLargestAirportName(){
        return airportDB;
    }
}

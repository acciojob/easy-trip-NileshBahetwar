package com.driver.Repository;

import com.driver.model.Passenger;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class PassengerRepository {
    HashMap<Integer,Passenger>passengerDB;

    public PassengerRepository(){
        passengerDB=new HashMap<>();
    }

    public void addPassenger(Passenger passenger){
        int passengerId=passenger.getPassengerId();
        passengerDB.put(passengerId,passenger);
    }


}

package com.driver.Repository;

import com.driver.model.Airport;
import com.driver.model.City;
import com.driver.model.Flight;
import com.driver.model.Passenger;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Repository
public class AirportRepository {
    HashMap<String,Airport> airportDB;
    HashMap<Integer, Flight>flightDB;
    HashMap<Integer, List<Integer>> ticketDb;
    HashMap<Integer, Passenger>passengerDB;

    public AirportRepository(){
        this.flightDB=new HashMap<>();
        this.ticketDb=new HashMap<>();
        this.airportDB=new HashMap<>();
        this.passengerDB=new HashMap<>();
    }


    public void addAirport(Airport airport){
       String airPortName= airport.getAirportName();
       airportDB.put(airPortName,airport);
    }

    public HashMap<String,Airport> getLargestAirportName(){
        return airportDB;
    }

    public double getShortestDurationOfPossibleBetweenTwoCities(City from ,City to){
        double ans = Double.MAX_VALUE;
        for(Integer i : flightDB.keySet()){
            Flight f  = flightDB.get(i);
            double time  = f.getDuration();
            City fromcity = f.getFromCity();
            City toCity  = f.getToCity();
            if(fromcity==from && toCity==to){
                if(ans>time)
                    ans  = time;
            }
        }
        return ans==Double.MAX_VALUE?-1:ans;
    }

    public int getNumberOfPeopleOn(Date date , String airPortName){
        int pans  = 0;
        for(String a : airportDB.keySet()){
            if(a.equals(airPortName)){
                Airport airport  = airportDB.get(a);
                City city  = airport.getCity();
                for(Integer i : ticketDb.keySet()){
                    Flight f = flightDB.get(i);
                    if(f.getToCity()==city||f.getFromCity()==city){
                        if(date.compareTo(f.getFlightDate())==0) {
                            List<Integer> al  = ticketDb.get(i);
                            pans += al.size();
                        }
                    }
                }
            }
        }
        return pans;
    }

    public int calculateFlightFare(Integer flightId){
        List<Integer> temp  = ticketDb.get(flightId);
        int n  = temp.size();
        return 3000+n*50;
    }

    public String bookATicket(Integer flightId,Integer passengerId){
        Flight flight=flightDB.get(flightId);
        int maxCapacity=flight.getMaxCapacity();

        List<Integer>currTicketBook=ticketDb.getOrDefault(flightId,new ArrayList<>());
        if(currTicketBook.size()>maxCapacity)   return "FAILURE";

        if(currTicketBook.size()>0) {
            for (Integer passenger : currTicketBook) {
                if (passenger == passengerId)
                    return "FAILURE";
            }
        }

        currTicketBook.add(passengerId);
        ticketDb.put(flightId,currTicketBook);
        return "SUCCESS";

    }
    public String cancelATicket(Integer flightId,Integer passengerId ){
        if(flightDB.containsKey(flightId)) return "FAILURE";

        List<Integer>currTicketBook=ticketDb.getOrDefault(flightId,new ArrayList<>());
        int s=-1;
        if(currTicketBook.size()>0){
            for (Integer i : currTicketBook) {
                if (i == passengerId) {
                    s = passengerId;
                    break;
                }
            }
            if (s == -1) return "FAILURE";
        }
        else{
            currTicketBook.remove(passengerId);
            ticketDb.put(flightId , currTicketBook);
        }
        return "SUCCESS";
    }


    public int countOfBookingsDoneByPassengerAllCombined(Integer passengerId){
        int cnt=0;
        for(Integer f : ticketDb.keySet()){
            List<Integer> temp = ticketDb.get(f);
            for(Integer i : temp){
                if(i==passengerId)
                    cnt++;
            }
        }
        return cnt;
    }

    public void addFlight(Flight flight){
        int flightId=flight.getFlightId();
        flightDB.put(flightId,flight);
    }

    public String getAirportNameFromFlightId(Integer flightId){
        if(!flightDB.containsKey(flightId)) return null;

        Flight flight  = flightDB.get(flightId);
        City city  = flight.getFromCity();
        for(String name : airportDB.keySet()){
            Airport a  = airportDB.get(name);
            City acity  = a.getCity();
            if(acity==city)
                return name;
        }
        return null;
    }



    public int getRevenueOfAFlight(Integer flightId){
        int pans  = 0;
        List<Integer> temp  = ticketDb.get(flightId);
        if(temp.size()==0){
            return 0;
        }
        for(int i = 0;i<temp.size();i++){
            pans  += 3000+i*50;
        }
        return pans;
    }


    public void addPassenger(Passenger passenger){
        int passengerId=passenger.getPassengerId();
        passengerDB.put(passengerId,passenger);
    }


}

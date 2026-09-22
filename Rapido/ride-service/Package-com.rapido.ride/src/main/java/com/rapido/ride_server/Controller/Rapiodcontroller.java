package com.rapido.ride_server.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rapido.ride_server.Client.CaptainClient;
import com.rapido.ride_server.Dto.CaptainDto;
import com.rapido.ride_server.Entity.Ride;
import com.rapido.ride_server.Service.Rideservice;
@RestController
public class Rapiodcontroller {

	
	@Autowired
    private  Rideservice rideService;
    private  CaptainClient captainClient;


    // Create a new ride
    @PostMapping("/rides")
    public Ride createRide(@RequestBody Ride ride) {
        return rideService.createRide(ride);
    }

    // Get all rides
    @GetMapping("/rides")
    public List<Ride> getAllRides() {
        return rideService.getAllRides();
    }

    @GetMapping("/rides/captains")
    public List<CaptainDto> getCaptains() {
        return captainClient.getAvailableCaptains();
    }

    // usercan(help)-Get ride by ID
    @GetMapping("/rides/{id}")
    public Ride getRideById(@PathVariable Long id) {
        return rideService.getRideById(id);
    }
 // Accept a ride
    
@PutMapping("/rides/{id}/accept")
    public Ride acceptRide(
           
    		@PathVariable Long id,
      @RequestParam Long captainId) {

        return rideService.acceptRide(id, captainId);
   }
    // Delete ride
    @DeleteMapping("/rides/{id}")
    public String deleteRide(@PathVariable Long id) {
        rideService.deleteRide(id);
        return "Ride deleted successfully";
    }
}
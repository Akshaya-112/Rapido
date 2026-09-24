

package com.rapido.ride_server.Service;

import java.time.LocalDateTime;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rapido.ride_server.Client.CaptainClient;
import com.rapido.ride_server.Entity.Ride;
import com.rapido.ride_server.Repository.Riderepository;

@Service
public class Rideservice {

	
		@Autowired
    private Riderepository rideRepository;
    private  CaptainClient captainClient;

   

    // User books a ride
   
 public Ride createRide(Ride ride) {

            // Calculate distance
            long distance = Math.round(calculateDistance(
                    ride.getPickupLatitude(),
                    ride.getPickupLongitude(),
                    ride.getDropLatitude(),
                    ride.getDropLongitude()
            ));

            ride.setDistance(distance);

            // Calculate fare
            long fare = distance * 15;
            ride.setFare(fare);

            // Set ride details
            ride.setStatus("REQUESTED");
            ride.setCreatedAt(LocalDateTime.now());

            return rideRepository.save(ride);
        }

        // Calculate distance
        private double calculateDistance(
                double lat1, double lon1,
                double lat2, double lon2) {

            double lat = lat2 - lat1;
            double lon = lon2 - lon1;

            return Math.sqrt(lat * lat + lon * lon) * 111;
        }
    

    

    // Captain accepts the ride
    public Ride acceptRide(Long rideId, Long captainId) {

        // Find the ride
        Ride ride = rideRepository.findById(rideId)
                .orElseThrow(() -> new RuntimeException("Ride not found"));

        // Only requested rides can be accepted
        if (!"REQUESTED".equalsIgnoreCase(ride.getStatus())) {
            throw new RuntimeException(
                    "Ride is already accepted or unavailable");
        }

        // Assign captain to the ride
        ride.setCaptainId(captainId);

        // Update ride status
        ride.setStatus("ACCEPTED");

        // Update captain status in Captain Service
        captainClient.updateCaptainStatus(captainId, "BUSY");

        // Save updated ride
        return rideRepository.save(ride);
    }

    // Get all rides
    public List<Ride> getAllRides() {
        return rideRepository.findAll();
    }

    // Get ride by ID
    public Ride getRideById(Long id) {
        return rideRepository.findById(id).orElse(null);
    }

    // Delete ride
    public void deleteRide(Long id) {
        rideRepository.deleteById(id);
    }
}

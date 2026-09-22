package com.rapido.ride_server.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rapido.ride_server.Client.CaptainClient;
import com.rapido.ride_server.Entity.Ride;
import com.rapido.ride_server.Repository.Riderepository;

@Service
public class Rideservice {

    private final Riderepository rideRepository;
    private final CaptainClient captainClient;

    public Rideservice(
            Riderepository rideRepository,
            CaptainClient captainClient) {

        this.rideRepository = rideRepository;
        this.captainClient = captainClient;
    }

    // User books a ride
    public Ride createRide(Ride ride) {

        // Captain is not assigned immediately
        ride.setCaptainId(null);

        // Initial ride status
        ride.setStatus("REQUESTED");

        // Booking time
        ride.setCreatedAt(LocalDateTime.now());

        return rideRepository.save(ride);
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
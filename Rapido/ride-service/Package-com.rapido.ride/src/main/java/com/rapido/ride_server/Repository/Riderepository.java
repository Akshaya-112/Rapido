
package com.rapido.ride_server.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rapido.ride_server.Entity.Ride;



public interface Riderepository extends JpaRepository<Ride, Long> {

}
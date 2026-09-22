package com.rapido.captain_server.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rapido.captain_server.Entity.Captain;
import com.rapido.captain_server.Repository.CaptainRepository;

@Service
public class Captainservice {

    private final CaptainRepository captainRepository;

    public Captainservice(CaptainRepository captainRepository) {
        this.captainRepository = captainRepository;
    }

    
   
    public Captain createCaptain(Captain captain) {
        return captainRepository.save(captain);
    }

    public List<Captain> getAllCaptains() {
        return captainRepository.findAll();
    }

    public Captain getCaptainById(Long id) {
        return captainRepository.findById(id).orElse(null);
    }

    public void deleteCaptain(Long id) {
        captainRepository.deleteById(id);
    }
    
    public List<Captain> getAvailableCaptains() {
        return captainRepository.findByStatus("AVAILABLE");
    }
    public Captain updateCaptainStatus(Long id, String status) {

        Captain captain = captainRepository.findById(id).orElse(null);

        if (captain == null) {
            return null;
        }

        captain.setStatus(status);

        return captainRepository.save(captain);
    }
    
}
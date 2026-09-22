package com.rapido.captain_server.Controller;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestParam;

import com.rapido.captain_server.Entity.Captain;
import com.rapido.captain_server.Service.Captainservice;


@RestController
public class Captaincontroller {

    private final Captainservice captainService;

    public Captaincontroller(Captainservice captainService) {
        this.captainService = captainService;
    }
    @GetMapping("/captains/available")
    public List<Captain> getAvailableCaptains() {
        return captainService.getAvailableCaptains();
    }

    @PostMapping("/captains")
    public Captain createCaptain(@RequestBody Captain captain) {
        return captainService.createCaptain(captain);
    }

    @GetMapping("/captains")
    public List<Captain> getAllCaptains() {
        return captainService.getAllCaptains();
    }

    @GetMapping("/captains/{id}")
    public Captain getCaptainById(@PathVariable Long id) {
        return captainService.getCaptainById(id);
    }

    @DeleteMapping("/captains/{id}")
    public String deleteCaptain(@PathVariable Long id) {
        captainService.deleteCaptain(id);
        return "Captain deleted successfully";
    }
    @PutMapping("/captains/{id}/status")
    public Captain updateCaptainStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        return captainService.updateCaptainStatus(id, status);
    }
}
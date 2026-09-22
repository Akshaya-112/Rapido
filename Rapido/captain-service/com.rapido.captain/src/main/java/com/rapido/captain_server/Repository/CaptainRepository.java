package com.rapido.captain_server.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rapido.captain_server.Entity.Captain;

public interface CaptainRepository extends JpaRepository<Captain, Long>{

    List<Captain> findByStatus(String status);
}




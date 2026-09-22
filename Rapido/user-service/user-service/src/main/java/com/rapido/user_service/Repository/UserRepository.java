package com.rapido.user_service.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rapido.user_service.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
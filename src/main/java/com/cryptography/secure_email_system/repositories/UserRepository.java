package com.cryptography.secure_email_system.repositories;

import com.cryptography.secure_email_system.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    List<User> findByIdNot(Long id); // Bu metod, belirli bir id'yi hariç tutarak tüm kullanıcıları getirir.

}

package com.hospital.system.medicarex.repository;

import com.hospital.system.medicarex.enums.Role;
import com.hospital.system.medicarex.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    long countByRole(Role role); // ✅ used in dashboard stats

    boolean existsByEmail(String email);        // ✅ add this for emailExists check

}


package com.hospital.system.medicarex.repository;

import com.hospital.system.medicarex.model.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuranceRepository extends JpaRepository<Insurance,Long> {
    Insurance findByInsuranceNo(String insuranceNo);

}


package com.heliumhealth.repository;

import com.heliumhealth.models.IPAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPAddressRepository extends JpaRepository<IPAddress, String> {
    Optional<IPAddress> findByAddress(String ipAddress);
}

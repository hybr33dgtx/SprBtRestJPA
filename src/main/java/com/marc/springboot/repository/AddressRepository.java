package com.marc.springboot.repository;

import com.marc.springboot.entity.Address;
import com.marc.springboot.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findByStreetAndCity(String street, String city);
}

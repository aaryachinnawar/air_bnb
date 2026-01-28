package com.airbnb.repository;

import com.airbnb.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
    List<Property> findByCity(String city);
    List<Property> findByCountry(String country);
    List<Property> findByHostId(Long hostId);
}

package com.airbnb.service;

import com.airbnb.model.Property;
import com.airbnb.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropertyService {
    
    private final PropertyRepository propertyRepository;
    
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }
    
    public Property getPropertyById(Long id) {
        return propertyRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Property not found"));
    }
    
    public List<Property> getPropertiesByCity(String city) {
        return propertyRepository.findByCity(city);
    }
    
    public Property createProperty(Property property) {
        return propertyRepository.save(property);
    }
    
    public Property updateProperty(Long id, Property propertyDetails) {
        Property property = getPropertyById(id);
        property.setTitle(propertyDetails.getTitle());
        property.setDescription(propertyDetails.getDescription());
        property.setAddress(propertyDetails.getAddress());
        property.setCity(propertyDetails.getCity());
        property.setCountry(propertyDetails.getCountry());
        property.setImageUrl(propertyDetails.getImageUrl());
        property.setPricePerNight(propertyDetails.getPricePerNight());
        property.setMaxGuests(propertyDetails.getMaxGuests());
        property.setBedrooms(propertyDetails.getBedrooms());
        property.setBathrooms(propertyDetails.getBathrooms());
        property.setPropertyType(propertyDetails.getPropertyType());
        return propertyRepository.save(property);
    }
    
    public void deleteProperty(Long id) {
        propertyRepository.deleteById(id);
    }
}

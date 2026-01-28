package com.airbnb.controller;

import com.airbnb.model.Property;
import com.airbnb.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/properties")
@RequiredArgsConstructor
public class PropertyController {
    
    private final PropertyService propertyService;
    
    @GetMapping
    public ResponseEntity<List<Property>> getAllProperties() {
        return ResponseEntity.ok(propertyService.getAllProperties());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Property> getPropertyById(@PathVariable Long id) {
        return ResponseEntity.ok(propertyService.getPropertyById(id));
    }
    
    @GetMapping("/city/{city}")
    public ResponseEntity<List<Property>> getPropertiesByCity(@PathVariable String city) {
        return ResponseEntity.ok(propertyService.getPropertiesByCity(city));
    }
    
    @PostMapping
    public ResponseEntity<Property> createProperty(@RequestBody Property property) {
        return ResponseEntity.ok(propertyService.createProperty(property));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Property> updateProperty(@PathVariable Long id, @RequestBody Property property) {
        return ResponseEntity.ok(propertyService.updateProperty(id, property));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id) {
        propertyService.deleteProperty(id);
        return ResponseEntity.noContent().build();
    }
}

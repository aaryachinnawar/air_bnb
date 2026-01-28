package com.airbnb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "properties")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Property {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String title;
    
    @Column(length = 2000)
    private String description;
    
    @Column(nullable = false)
    private String address;
    
    @Column(nullable = false)
    private String city;
    
    @Column(nullable = false)
    private String country;
    
    private String imageUrl;
    
    @DecimalMin(value = "0.01", message = "Price per night must be greater than 0")
    @Column(nullable = false)
    private BigDecimal pricePerNight;
    
    private Integer maxGuests;
    
    private Integer bedrooms;
    
    private Integer bathrooms;
    
    @Enumerated(EnumType.STRING)
    private PropertyType propertyType;
    
    @ManyToOne
    @JoinColumn(name = "host_id")
    private User host;
    
    @JsonIgnore
    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    private Set<Booking> bookings = new HashSet<>();
    
    @JsonIgnore
    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    private Set<Review> reviews = new HashSet<>();
    
    public enum PropertyType {
        APARTMENT, HOUSE, VILLA, COTTAGE, CABIN
    }
}

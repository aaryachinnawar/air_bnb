package com.airbnb.model;

import jakarta.persistence.*;
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
    
    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    private Set<Booking> bookings = new HashSet<>();
    
    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    private Set<Review> reviews = new HashSet<>();
    
    public enum PropertyType {
        APARTMENT, HOUSE, VILLA, COTTAGE, CABIN
    }
}

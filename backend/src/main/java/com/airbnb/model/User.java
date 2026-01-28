package com.airbnb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    @JsonIgnore
    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false)
    private String firstName;
    
    @Column(nullable = false)
    private String lastName;
    
    private String phoneNumber;
    
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.GUEST;
    
    @JsonIgnore
    @OneToMany(mappedBy = "host", cascade = CascadeType.ALL)
    private Set<Property> properties = new HashSet<>();
    
    @JsonIgnore
    @OneToMany(mappedBy = "guest", cascade = CascadeType.ALL)
    private Set<Booking> bookings = new HashSet<>();
    
    public enum UserRole {
        GUEST, HOST, ADMIN
    }
}

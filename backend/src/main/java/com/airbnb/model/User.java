package com.airbnb.model;

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
    
    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false)
    private String firstName;
    
    @Column(nullable = false)
    private String lastName;
    
    private String phoneNumber;
    
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.GUEST;
    
    @OneToMany(mappedBy = "host", cascade = CascadeType.ALL)
    private Set<Property> properties = new HashSet<>();
    
    @OneToMany(mappedBy = "guest", cascade = CascadeType.ALL)
    private Set<Booking> bookings = new HashSet<>();
    
    public enum UserRole {
        GUEST, HOST, ADMIN
    }
}

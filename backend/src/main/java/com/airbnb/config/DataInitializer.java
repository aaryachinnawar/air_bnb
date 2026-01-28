package com.airbnb.config;

import com.airbnb.model.Property;
import com.airbnb.model.User;
import com.airbnb.repository.PropertyRepository;
import com.airbnb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {
    
    private final UserRepository userRepository;
    private final PropertyRepository propertyRepository;
    private final PasswordEncoder passwordEncoder;
    
    @Bean
    CommandLineRunner initDatabase() {
        return args -> {
            // Create sample users
            User host1 = new User();
            host1.setEmail("host@example.com");
            host1.setPassword(passwordEncoder.encode("password123"));
            host1.setFirstName("John");
            host1.setLastName("Host");
            host1.setPhoneNumber("123-456-7890");
            host1.setRole(User.UserRole.HOST);
            userRepository.save(host1);
            
            User guest1 = new User();
            guest1.setEmail("guest@example.com");
            guest1.setPassword(passwordEncoder.encode("password123"));
            guest1.setFirstName("Jane");
            guest1.setLastName("Guest");
            guest1.setPhoneNumber("098-765-4321");
            guest1.setRole(User.UserRole.GUEST);
            userRepository.save(guest1);
            
            // Create sample properties
            Property property1 = new Property();
            property1.setTitle("Cozy Downtown Apartment");
            property1.setDescription("Beautiful apartment in the heart of the city with amazing views.");
            property1.setAddress("123 Main Street");
            property1.setCity("New York");
            property1.setCountry("USA");
            property1.setImageUrl("https://images.unsplash.com/photo-1522708323590-d24dbb6b0267");
            property1.setPricePerNight(new BigDecimal("150.00"));
            property1.setMaxGuests(4);
            property1.setBedrooms(2);
            property1.setBathrooms(2);
            property1.setPropertyType(Property.PropertyType.APARTMENT);
            property1.setHost(host1);
            propertyRepository.save(property1);
            
            Property property2 = new Property();
            property2.setTitle("Beach House Paradise");
            property2.setDescription("Stunning beach house with private access to the ocean.");
            property2.setAddress("456 Ocean Drive");
            property2.setCity("Miami");
            property2.setCountry("USA");
            property2.setImageUrl("https://images.unsplash.com/photo-1499793983690-e29da59ef1c2");
            property2.setPricePerNight(new BigDecimal("300.00"));
            property2.setMaxGuests(6);
            property2.setBedrooms(3);
            property2.setBathrooms(3);
            property2.setPropertyType(Property.PropertyType.HOUSE);
            property2.setHost(host1);
            propertyRepository.save(property2);
            
            Property property3 = new Property();
            property3.setTitle("Mountain Cabin Retreat");
            property3.setDescription("Peaceful cabin nestled in the mountains, perfect for a quiet getaway.");
            property3.setAddress("789 Mountain Road");
            property3.setCity("Aspen");
            property3.setCountry("USA");
            property3.setImageUrl("https://images.unsplash.com/photo-1449158743715-0a90ebb6d2d8");
            property3.setPricePerNight(new BigDecimal("200.00"));
            property3.setMaxGuests(4);
            property3.setBedrooms(2);
            property3.setBathrooms(1);
            property3.setPropertyType(Property.PropertyType.CABIN);
            property3.setHost(host1);
            propertyRepository.save(property3);
            
            System.out.println("Sample data initialized successfully!");
        };
    }
}

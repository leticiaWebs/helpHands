package com.helpHands.helpHands.domain;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Service {

    Long id;
    private User provider;
    private String title;
    private String description;
    private String category;
    private BigDecimal basePrice;
    private Integer estimatedDuration;
    private String serviceImage;
    private BigDecimal rating = BigDecimal.ZERO;
    private Integer totalReviews = 0;
    private Boolean isActive = true;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private List<Booking> bookings = new ArrayList<>();
    private List<ServiceAvailability> availabilities = new ArrayList<>();
    private List<Review> reviews = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}


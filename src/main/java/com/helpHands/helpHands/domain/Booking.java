package com.helpHands.helpHands.domain;

import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tbBooking")
public class Booking {

    @Id
    private Long id;
    private Service service;
    private Costumer client;
    private Provider provider;
    private LocalDate bookingDate;
    private LocalTime bookingTime;
    private LocalDateTime bookingDateTime;

    private BigDecimal totalPrice;
    private String notes;
    private String cancellationReason;
    private LocalDateTime completedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Payment payment;


    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        bookingDateTime = LocalDateTime.of(bookingDate, bookingTime);
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}

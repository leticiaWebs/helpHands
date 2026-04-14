package com.helpHands.helpHands.domain;

import ch.qos.logback.core.net.server.Client;
import com.helpHands.helpHands.enums.BookingStatus;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class Booking {

    @Id
    private Long id;
    private Service service;
//    private Client client;
//    private Provider provider;
    private LocalDate bookingDate;
    private LocalTime bookingTime;
    private LocalDateTime bookingDateTime;
    private BookingStatus status = BookingStatus.PENDING;
    private BigDecimal totalPrice;
    private String notes;
    private String cancellationReason;
    private LocalDateTime completedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Payment payment;
    private List<Message> messages = new ArrayList<>();

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

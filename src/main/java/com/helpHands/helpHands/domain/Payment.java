package com.helpHands.helpHands.domain;

import com.helpHands.helpHands.enums.PaymentMethod;
import com.helpHands.helpHands.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tbPayment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Booking booking;
    private Costumer client;
    private Provider provider;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private PaymentStatus status = PaymentStatus.PENDING;
    private String transactionId;
    private String externalReference;
    private LocalDateTime paymentDate;
    private LocalDateTime refundDate;
    private String refundReason;
//    private String gatewayResponse;
    private BigDecimal fee = BigDecimal.ZERO;
    private BigDecimal netAmount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        netAmount = amount.subtract(fee != null ? fee : BigDecimal.ZERO);
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
        netAmount = amount.subtract(fee != null ? fee : BigDecimal.ZERO);
    }
}

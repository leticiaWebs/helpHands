package com.helpHands.helpHands.domain;

import com.helpHands.helpHands.enums.BookingStatus;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tbServiceOrders")
public class ServiceOrders {

    private int openServicesOrders;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private BookingStatus status = BookingStatus.PENDING;

}

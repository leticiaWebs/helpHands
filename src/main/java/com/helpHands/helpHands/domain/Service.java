package com.helpHands.helpHands.domain;

import com.helpHands.helpHands.domain.users.UserCustomers;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tbService")
public class Service {

    Long id;
    private UserCustomers provider;
    private String title;
    private String description;
    private String category;
    private BigDecimal basePrice;
    private Integer estimatedDuration;
    private String serviceImage;
    private BigDecimal rating = BigDecimal.ZERO;
    private Integer totalReviews = 0;
    private Boolean isActive = true;

}


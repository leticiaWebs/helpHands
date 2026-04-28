package com.helpHands.helpHands.domain;


import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tbCostumer")
public class Costumer {

    private User user;
    private ServiceOrders order;

}

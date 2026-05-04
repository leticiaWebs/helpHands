package com.helpHands.helpHands.domain.users;


import com.helpHands.helpHands.domain.ServiceOrders;
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

    private UserCustomers userCustomers;
    private ServiceOrders order;

}

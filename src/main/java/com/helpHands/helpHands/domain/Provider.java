package com.helpHands.helpHands.domain;

import com.helpHands.helpHands.domain.users.UserCustomers;
import com.helpHands.helpHands.enums.ServiceCategory;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tbProvider")
public class Provider {

    private UserCustomers userCustomers;
    private ServiceCategory serviceCategory;


}

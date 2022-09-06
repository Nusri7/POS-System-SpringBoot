package com.pos.kuppiya.pointofsale.dto.request;

import lombok.*;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data


public class CustomerUpdateRequestDTO {
    private int customerId;
    private String customerName;
    private String customerAddress;
    private ArrayList contactNumbers;
    private String NIC;
    private boolean activeState;


}



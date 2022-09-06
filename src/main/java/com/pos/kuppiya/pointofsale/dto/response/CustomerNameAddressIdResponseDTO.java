package com.pos.kuppiya.pointofsale.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CustomerNameAddressIdResponseDTO {

    private String customerName;
    private String customerAddress;
    private String NIC;
}

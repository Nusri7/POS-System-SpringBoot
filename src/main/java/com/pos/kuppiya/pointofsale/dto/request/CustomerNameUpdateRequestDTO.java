package com.pos.kuppiya.pointofsale.dto.request;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CustomerNameUpdateRequestDTO {
    private int customerId;
    private String customerName;

}

package com.pos.kuppiya.pointofsale.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ItemNameBalQtyStateUpdateDTO {
    private String itemName;
    private double balanceQty;
    private boolean activeState;
}

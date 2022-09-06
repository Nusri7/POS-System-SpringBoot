package com.pos.kuppiya.pointofsale.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data


public class ItemPriceBalQtyUpdateDTO {
    private double balanceQty;
    private double sellingPrice;

}

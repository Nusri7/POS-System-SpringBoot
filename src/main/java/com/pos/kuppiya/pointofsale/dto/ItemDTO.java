package com.pos.kuppiya.pointofsale.dto;

import com.pos.kuppiya.pointofsale.entity.enums.MeasuringTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data


public class ItemDTO {
    private int itemId;
    private String itemName;
    private MeasuringTypes measuringUnit;
    private double balanceQty;
    private double supplierPrice;
    private double sellingPrice;
    private boolean activeState;

}
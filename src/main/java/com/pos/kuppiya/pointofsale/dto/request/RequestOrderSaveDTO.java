package com.pos.kuppiya.pointofsale.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestOrderSaveDTO {
    private String itemName;
    private double qty;
    private double amount;
    private int orders;
    private int items;

}

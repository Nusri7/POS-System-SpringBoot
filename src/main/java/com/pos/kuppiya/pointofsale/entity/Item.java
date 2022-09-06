package com.pos.kuppiya.pointofsale.entity;

import com.pos.kuppiya.pointofsale.entity.enums.MeasuringTypes;
import com.vladmihalcea.hibernate.type.json.JsonType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "item")
@TypeDefs({@TypeDef(name = "json",typeClass = JsonType.class)})

public class Item {
    @Id
    @Column(name = "item_id", length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int itemId;

    @Column(name = "item_name", length = 100, nullable = false)
    private String itemName;

    @Enumerated(EnumType.STRING)
    @Column(name = "mesuring_unit", nullable = false)
    private MeasuringTypes measuringUnit;

    @Column(name = "balance_qty", nullable = false)
    private double balanceQty;

    @Column(name = "supplier_price",nullable = false,length = 100)
    private double supplierPrice;

    @Column(name = "selling_price",nullable = false,length = 100)
    private double sellingPrice;

    @Column(name = "active_state",columnDefinition = "TINYINT default 1" )
    private boolean activeState;


}

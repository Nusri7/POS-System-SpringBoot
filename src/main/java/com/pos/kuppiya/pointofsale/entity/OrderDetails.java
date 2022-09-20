package com.pos.kuppiya.pointofsale.entity;

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
@Table(name = "orders_details")
@TypeDefs({@TypeDef(name = "json",typeClass = JsonType.class)})

public class OrderDetails {
    @Id
    @Column(name = "order_details_id",length = 40)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int OrderDetailsId;

    @Column(name = "item_name", length = 100, nullable = false)
    private String itemName;

    @Column(name = "qty", nullable = false)
    private double qty;

    @Column(name = "amount", nullable = false)
    private double amount;

    @ManyToOne
    @JoinColumn(name = "order_id",nullable = false)
    private Order orders;

    @ManyToOne
    @JoinColumn(name = "item_id",nullable = false)
    private Item items;

}

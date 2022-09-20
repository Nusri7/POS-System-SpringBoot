package com.pos.kuppiya.pointofsale.entity;

import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data


@Entity
@Table(name = "orders")
@TypeDefs({@TypeDef(name = "json",typeClass = JsonType.class)})
public class Order {
    @Id
    @Column(name = "order_id", length = 40)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderId;

    @ManyToOne
    @JoinColumn(name = "customer_id",nullable = false)
    private Customer customer;

    @Column(name = "order_date", columnDefinition = "DATETIME")
    private Date date;

    @Column(name = "total", nullable = false)
    private double total;

    @OneToMany(mappedBy = "orders")
    private Set<OrderDetails> orderDetails;

    public Order(Customer customer, Date date, double total) {
        this.customer = customer;
        this.date = date;
        this.total = total;
    }
}

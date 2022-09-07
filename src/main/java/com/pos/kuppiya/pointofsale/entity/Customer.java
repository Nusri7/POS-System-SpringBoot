package com.pos.kuppiya.pointofsale.entity;

import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "customer")
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonType.class)
})
public class Customer {
    @Id
    @Column(name = "customer_id", length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int customerId;

    @Column(name = "customer_name", length = 100, nullable = false)
    private String customerName;

    @Column(name = "customer_address", length = 150)
    private String customerAddress;

    @Type(type = "json")
    @Column(name = "contact_numbers", columnDefinition = "json")
    private ArrayList contactNumbers;

    @Column(name = "nic", length = 20, unique = true)
    private String NIC;

    @Column(name = "active_state", columnDefinition = "TINYINT default 1")
    private boolean activeState;

    @OneToMany(mappedBy = "customer")
    private Set<Order> orders;

    public Customer(int customerId, String customerName, String customerAddress, ArrayList contactNumbers, String nic, boolean activeState) {
    }
}

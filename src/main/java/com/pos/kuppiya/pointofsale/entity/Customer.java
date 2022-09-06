package com.pos.kuppiya.pointofsale.entity;

import com.vladmihalcea.hibernate.type.json.JsonType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "customer")
@TypeDefs({
        @TypeDef(name = "json",typeClass = JsonType.class)
})
public class Customer {
    @Id
    @Column(name = "customer_id",length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int customerId;

    @Column(name = "customer_name",length = 100,nullable = false)
    private String customerName;

    @Column(name = "customer_address", length = 150)
    private String customerAddress;

    @Type(type = "json")
    @Column(name = "contact_numbers", columnDefinition = "json")
    private ArrayList contactNumbers;

    @Column(name = "nic", length = 20, unique = true)
    private String NIC;

    @Column(name = "active_state",columnDefinition = "TINYINT default 1" )
    private boolean activeState;

    public Customer() {
    }

    public Customer(int customerId, String customerName, String customerAddress, ArrayList contactNumbers, String NIC, boolean activeState) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.contactNumbers = contactNumbers;
        this.NIC = NIC;
        this.activeState = activeState;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public ArrayList getContactNumbers() {
        return contactNumbers;
    }

    public void setContactNumbers(ArrayList contactNumbers) {
        this.contactNumbers = contactNumbers;
    }

    public String getNIC() {
        return NIC;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    public boolean isActiveState() {
        return activeState;
    }

    public void setActiveState(boolean activeState) {
        this.activeState = activeState;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", contactNumbers=" + contactNumbers +
                ", NIC='" + NIC + '\'' +
                ", activeState=" + activeState +
                '}';
    }
}

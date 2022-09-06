package com.pos.kuppiya.pointofsale.dto;

import java.util.ArrayList;

public class CustomerDTO {
    private int customerId;
    private String customerName;
    private String customerAddress;
    private ArrayList contactNumbers;
    private String NIC;
    private boolean activeState;

    public CustomerDTO() {
    }

    public CustomerDTO(int customerId, String customerName, String customerAddress, ArrayList contactNumbers, String NIC, boolean activeState) {
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
        return "CustomerDTO{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", contactNumbers=" + contactNumbers +
                ", NIC='" + NIC + '\'' +
                ", activeState=" + activeState +
                '}';
    }
}

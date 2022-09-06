package com.pos.kuppiya.pointofsale.service;

import com.pos.kuppiya.pointofsale.dto.CustomerDTO;
import com.pos.kuppiya.pointofsale.dto.request.CustomerNameUpdateRequestDTO;
import com.pos.kuppiya.pointofsale.dto.request.CustomerUpdateRequestDTO;
import com.pos.kuppiya.pointofsale.dto.response.CustomerNameAddressIdResponseDTO;
import javassist.NotFoundException;

import java.util.List;

public interface CustomerService {
    String test(CustomerDTO customerDTO);

    String addCustomer(CustomerDTO customerDTO);

    String updateCustomer(CustomerUpdateRequestDTO customerUpdateRequestDTO);

    CustomerDTO customerGetById( int id);

    List<CustomerDTO> getAllCustomers();

    boolean deleteCustomer(int id) throws NotFoundException;

    List<CustomerDTO> getByName(String customerName) throws NotFoundException;

    String updateCustomerName(CustomerNameUpdateRequestDTO customerNameUpdateRequestDTO) throws NotFoundException;

    CustomerDTO getByNic(String nic) throws NotFoundException;

    CustomerNameAddressIdResponseDTO getNameAddressById(int id) throws NotFoundException;

    String updateCustomerNameAddressNic(int id, CustomerNameAddressIdResponseDTO customerNameAddressIdResponseDTO) throws NotFoundException;

    CustomerDTO getActiveCustomer(int id) throws NotFoundException;

    List<CustomerDTO> getCustomersbystate(String state) throws NotFoundException;
}

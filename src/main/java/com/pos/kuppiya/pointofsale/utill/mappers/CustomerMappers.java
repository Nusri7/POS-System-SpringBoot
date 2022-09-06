package com.pos.kuppiya.pointofsale.utill.mappers;

import com.pos.kuppiya.pointofsale.dto.CustomerDTO;
import com.pos.kuppiya.pointofsale.entity.Customer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMappers {
    CustomerDTO entityToDto(Customer customer);
    List<CustomerDTO> listEntityToListDto(List<Customer> customers);
}

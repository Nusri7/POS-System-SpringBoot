package com.pos.kuppiya.pointofsale.service.impl;

import com.pos.kuppiya.pointofsale.dto.CustomerDTO;
import com.pos.kuppiya.pointofsale.dto.request.CustomerNameUpdateRequestDTO;
import com.pos.kuppiya.pointofsale.dto.request.CustomerUpdateRequestDTO;
import com.pos.kuppiya.pointofsale.dto.response.CustomerNameAddressIdResponseDTO;
import com.pos.kuppiya.pointofsale.entity.Customer;
import com.pos.kuppiya.pointofsale.repository.CustomerRepo;
import com.pos.kuppiya.pointofsale.service.CustomerService;
import com.pos.kuppiya.pointofsale.utill.mappers.CustomerMappers;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerServiceIMPL implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomerMappers customerMappers;

    public String test(CustomerDTO customerDTO) {
        return null;
    }

    @Override
    public String addCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer(
                customerDTO.getCustomerId(),
                customerDTO.getCustomerName(),
                customerDTO.getCustomerAddress(),
                customerDTO.getContactNumbers(),
                customerDTO.getNIC(),
                customerDTO.isActiveState()

        );
        if (!customerRepo.existsById(customer.getCustomerId())) {
            customerRepo.save(customer);
            return customer.getNIC() + " Saved";
        }
        return "Customer already exist!!";
    }

    @Override
    public String updateCustomer(CustomerUpdateRequestDTO customerUpdateRequestDTO) {
        if (customerRepo.existsById(customerUpdateRequestDTO.getCustomerId())) {
            Customer customer = customerRepo.getById(customerUpdateRequestDTO.getCustomerId());
            customer.setCustomerName(customerUpdateRequestDTO.getCustomerName());
            customer.setCustomerAddress(customerUpdateRequestDTO.getCustomerAddress());
            customer.setContactNumbers(customerUpdateRequestDTO.getContactNumbers());
            customer.setNIC(customerUpdateRequestDTO.getNIC());
            customer.setActiveState(customerUpdateRequestDTO.isActiveState());

            customerRepo.save(customer);
            return null;
        } else {
            System.out.println("this customer not in database");
            return null;
        }


    }

    @Override
    public CustomerDTO customerGetById(int id) {
        Optional<Customer> customer = customerRepo.findById(id);
        if (customer.isPresent()) {
//                    CustomerDTO customerDTO = new CustomerDTO(
//                            customer.get().getCustomerId(),
//                            customer.get().getCustomerName(),
//                            customer.get().getCustomerAddress(),
//                            customer.get().getContactNumbers(),
//                            customer.get().getNIC(),
//                            customer.get().isActiveState()
//                    );
//            CustomerDTO customerDTO = modelMapper.map(customer.get(), CustomerDTO.class);
            CustomerDTO customerDTO = customerMappers.entityToDto(customer.get());
            return customerDTO;
        } else {
            return null;
        }


    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> getAllCustomers = customerRepo.findAll();
        List<CustomerDTO> allCustomerDTOList = new ArrayList<>();

//        for(Customer c:getAllCustomers){
//            CustomerDTO customerDTO = new CustomerDTO(
//                    c.getCustomerId(),
//                    c.getCustomerName(),
//                    c.getCustomerAddress(),
//                    c.getContactNumbers(),
//                    c.getNIC(),
//                    c.isActiveState()
//            );
//            allCustomerDTOList.add(customerDTO);
//        }
//        List<CustomerDTO> customerDTOS = modelMapper.
//                map(getAllCustomers, new TypeToken<List<CustomerDTO>>() {
//                }.getType());
        List<CustomerDTO> customerDTO = customerMappers.listEntityToListDto(getAllCustomers);
        return customerDTO;
    }

    @Override
    public boolean deleteCustomer(int id) throws NotFoundException {
        if (customerRepo.existsById(id)) {
            customerRepo.deleteById(id);
        } else {
            throw new NotFoundException("Customer not found fot this ID!!");
        }
        return true;
    }

    @Override
    public List<CustomerDTO> getByName(String customerName) throws NotFoundException {
        List<Customer> customers = customerRepo.findAllByCustomerNameEquals(customerName);
        if (customers.size() != 0) {
            List<CustomerDTO> customerDTOS = modelMapper.
                    map(customers, new TypeToken<List<CustomerDTO>>() {
                    }.getType());
            return customerDTOS;
        } else {
            throw new NotFoundException("No results!!!");
        }
    }

    @Override
    public String updateCustomerName(CustomerNameUpdateRequestDTO customerNameUpdateRequestDTO) throws NotFoundException {
        if (customerRepo.existsById(customerNameUpdateRequestDTO.getCustomerId())) {
            Customer customer = customerRepo.getById(customerNameUpdateRequestDTO.getCustomerId());
            customer.setCustomerName(customerNameUpdateRequestDTO.getCustomerName());
            customerRepo.save(customer);
            return "Success!!!!";
        } else {
            throw new NotFoundException("Customer not found fot this ID!!");
        }

    }

    @Override
    public CustomerDTO getByNic(String nic) throws NotFoundException {
        Optional<Customer> customer = customerRepo.findAllByNICEquals(nic);
        if (customer.isPresent()) {
            CustomerDTO customerDTO = customerMappers.entityToDto(customer.get());
            return customerDTO;
        } else {
            throw new NotFoundException("Customer not found for this NIC!!!");
        }

    }

    @Override
    public CustomerNameAddressIdResponseDTO getNameAddressById(int id) throws NotFoundException {
        Optional<Customer> customer = customerRepo.findById(id);
        if (customer.isPresent()) {
            CustomerNameAddressIdResponseDTO getNameAddress = modelMapper.
                    map(customer.get(), new TypeToken<CustomerNameAddressIdResponseDTO>() {
                    }.getType());
            return getNameAddress;
        } else {
            throw new NotFoundException("Customer Not Found for this ID!!!");
        }

    }

    @Override
    public String updateCustomerNameAddressNic(int id, CustomerNameAddressIdResponseDTO customerNameAddressIdResponseDTO) throws NotFoundException {
        if (customerRepo.existsById(id)) {
            customerRepo.updateCustomerbyid(customerNameAddressIdResponseDTO.getCustomerName(), customerNameAddressIdResponseDTO.getCustomerAddress(), customerNameAddressIdResponseDTO.getNIC(), id);
            return "Update Successfull!!!";
        } else {
            throw new NotFoundException("Id not found!!!");
        }

    }

    @Override
    public CustomerDTO getActiveCustomer(int id) throws NotFoundException {

        if (customerRepo.existsById(id)) {
            Customer customer = customerRepo.findByActiveStateEqualsAndCustomerIdEquals(true, id);
            Customer customer1 = customerRepo.getById(id);

            if (customer1.isActiveState()) {
                CustomerDTO customerDTO = customerMappers.entityToDto(customer);
                return customerDTO;
            } else {
                throw new NotFoundException("This is inactive customer!!!");
            }
        } else {
            throw new NotFoundException("id not found!!!");
        }

    }

    @Override
    public List<CustomerDTO> getCustomersbystate(String state) throws NotFoundException {
        List<Customer> customer1 = customerRepo.findAllByActiveStateEquals(true);
        List<Customer> customer2 = customerRepo.findAllByActiveStateEquals(false);
        List<Customer> customer3 = customerRepo.findAll();
        if (Objects.equals(state, "active")){
            List<CustomerDTO> customerDTOS = customerMappers.listEntityToListDto(customer1);
            return customerDTOS;
        }else if (state.equals("inactive")){
            List<CustomerDTO> customerDTOS = customerMappers.listEntityToListDto(customer2);
            return customerDTOS;
        }else if(state.equals("all")) {
            List<CustomerDTO> customerDTOS = customerMappers.listEntityToListDto(customer3);
            return customerDTOS;
        }else {
           throw new com.pos.kuppiya.pointofsale.exceptions.NotFoundException("Not Found");
        }
        }
    }


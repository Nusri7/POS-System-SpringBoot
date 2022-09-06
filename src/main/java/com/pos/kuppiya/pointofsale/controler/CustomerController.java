package com.pos.kuppiya.pointofsale.controler;

import com.pos.kuppiya.pointofsale.dto.CustomerDTO;
import com.pos.kuppiya.pointofsale.dto.request.CustomerNameUpdateRequestDTO;
import com.pos.kuppiya.pointofsale.dto.request.CustomerUpdateRequestDTO;

import com.pos.kuppiya.pointofsale.dto.response.CustomerNameAddressIdResponseDTO;
import com.pos.kuppiya.pointofsale.service.CustomerService;
import com.pos.kuppiya.pointofsale.utill.StandardResponse;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
@CrossOrigin
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping(path = "/add")
    public ResponseEntity<StandardResponse> addCustomer(@RequestBody CustomerDTO customerDTO) {
        String id = customerService.addCustomer(customerDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Successfully added",id), HttpStatus.CREATED
        );
    }

    @PutMapping(path = "/update")
    public ResponseEntity<StandardResponse> updateCustomer(@RequestBody CustomerUpdateRequestDTO customerUpdateRequestDTO) {
        String updated = customerService.updateCustomer(customerUpdateRequestDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,"Updated Successfully",updated),HttpStatus.OK
        );
    }

    @GetMapping(path = "/getById", params = "id")
    public ResponseEntity<StandardResponse> customerGetById(@RequestParam(value = "id") int id) {
        CustomerDTO customerDTO = customerService.customerGetById(id);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"",customerDTO),HttpStatus.OK
        );
    }

    @GetMapping(path = "/get-all-customers")
    public ResponseEntity<StandardResponse> getAllCustomers() {
        List<CustomerDTO> allCustomers = customerService.getAllCustomers();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"All Customers",allCustomers),HttpStatus.OK
        );
    }

    @DeleteMapping(path = "/delete-customer/{id}")
    public ResponseEntity<StandardResponse> deleteCustomer(@PathVariable(value = "id") int id) throws NotFoundException {
        customerService.deleteCustomer(id);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Successfully Deleted!!",null),HttpStatus.OK
        );
    }

    @GetMapping(path = "/get-by-name",
            params = "name")
    public ResponseEntity<StandardResponse> customerGetByName(@RequestParam(value = "name") String customerName) throws NotFoundException {
        List<CustomerDTO> getCustomer = customerService.getByName(customerName);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"",getCustomer),HttpStatus.OK
        );
    }
    @PutMapping(path = "/update-customer-name")
    public ResponseEntity<StandardResponse> updateName( @RequestBody CustomerNameUpdateRequestDTO customerNameUpdateRequestDTO) throws NotFoundException {
        customerService.updateCustomerName(customerNameUpdateRequestDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Successfully Updated!!!",null),HttpStatus.OK
        );
    }

    @GetMapping(path = "/get-by-nic/{nic}")
    public ResponseEntity<StandardResponse> getCustomerByNic(@PathVariable(value = "nic")String nic) throws NotFoundException {
        CustomerDTO customerDTO = customerService.getByNic(nic);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"",customerDTO),HttpStatus.OK
        );
    }
    @GetMapping(path = "/get-name-address-by-id",
                params = "id"
               )
    public ResponseEntity<StandardResponse> getNameAddressById(@RequestParam(value = "id")int id) throws NotFoundException {
        CustomerNameAddressIdResponseDTO getNameAddress = customerService.getNameAddressById(id);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"",getNameAddress),HttpStatus.OK
        );
    }

    @PutMapping(path = "/update-name-address-nic", params = "id")
    public ResponseEntity<StandardResponse> updateCustomerNameAddressNic(@RequestParam(value = "id")int id,@RequestBody CustomerNameAddressIdResponseDTO customerNameAddressIdResponseDTO) throws NotFoundException {
        customerService.updateCustomerNameAddressNic(id,customerNameAddressIdResponseDTO);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Successfully updated\" + id","Updated"),HttpStatus.OK
        );
    }

    @GetMapping(path = "/get-state-true/{id}")
    public ResponseEntity<StandardResponse> getActiveCustomer(@PathVariable(value = "id")int id) throws NotFoundException {
        CustomerDTO customerDTO = customerService.getActiveCustomer(id);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"",customerDTO),HttpStatus.OK
        );
    }
    @GetMapping(path = "/get-state-by-opinion/{state}")
    public ResponseEntity<StandardResponse> getCustomersByState(@PathVariable(value = "state")  String state) throws NotFoundException {
        List<CustomerDTO> customerDTO = customerService.getCustomersbystate(state);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"",customerDTO),HttpStatus.OK
        );
    }
    

}

package com.pos.kuppiya.pointofsale.service.impl;

import com.pos.kuppiya.pointofsale.dto.CustomerDTO;
import com.pos.kuppiya.pointofsale.dto.request.RequestOrderDetailsSaveDTO;
import com.pos.kuppiya.pointofsale.entity.Order;
import com.pos.kuppiya.pointofsale.entity.OrderDetails;
import com.pos.kuppiya.pointofsale.exceptions.NotFoundException;
import com.pos.kuppiya.pointofsale.repository.CustomerRepo;
import com.pos.kuppiya.pointofsale.repository.ItemRepo;
import com.pos.kuppiya.pointofsale.repository.OrderDetailsRepo;
import com.pos.kuppiya.pointofsale.repository.OrderRepo;
import com.pos.kuppiya.pointofsale.service.OrderService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceIMPL implements OrderService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private OrderDetailsRepo orderDetailsRepo;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public String saveOrder(RequestOrderDetailsSaveDTO requestOrderDetailsSaveDTO) {
        Order order = new Order(
                customerRepo.getById(requestOrderDetailsSaveDTO.getCustomer()),
                requestOrderDetailsSaveDTO.getDate(),
                requestOrderDetailsSaveDTO.getTotal()
        );
        orderRepo.save(order);
        if (orderRepo.existsById(order.getOrderId())){
            List<OrderDetails> orderDetails = modelMapper.
                    map(requestOrderDetailsSaveDTO.getRequestOrderSaveDTOS(), new TypeToken<List<OrderDetails>>() {
                    }.getType());
            for (int i= 0; i< orderDetails.size();i++){
                orderDetails.get(i).setOrders(order);
                orderDetails.get(i).setItems(itemRepo.getById(requestOrderDetailsSaveDTO.getCustomer()));
            }
            if (orderDetails.size()>0){
                orderDetailsRepo.saveAll(orderDetails);
            }
            return "Saved!";
        }
            throw new NotFoundException("id does not exist!!!");
        }


}

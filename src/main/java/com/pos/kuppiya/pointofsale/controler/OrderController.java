package com.pos.kuppiya.pointofsale.controler;

import com.pos.kuppiya.pointofsale.dto.request.ItemSaveRequestDTO;
import com.pos.kuppiya.pointofsale.dto.request.RequestOrderDetailsSaveDTO;
import com.pos.kuppiya.pointofsale.service.OrderService;
import com.pos.kuppiya.pointofsale.utill.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping(path = "/save")
    public ResponseEntity<StandardResponse> saveItem(@RequestBody RequestOrderDetailsSaveDTO requestOrderDetailsSaveDTO) {
        String id = orderService.saveOrder(requestOrderDetailsSaveDTO);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "Sucesssed", id), HttpStatus.OK
        );
    }

}

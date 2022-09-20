package com.pos.kuppiya.pointofsale.service;

import com.pos.kuppiya.pointofsale.dto.request.RequestOrderDetailsSaveDTO;

public interface OrderService {
    String saveOrder(RequestOrderDetailsSaveDTO requestOrderDetailsSaveDTO);
}

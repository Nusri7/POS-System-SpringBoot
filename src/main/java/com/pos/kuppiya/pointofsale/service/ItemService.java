package com.pos.kuppiya.pointofsale.service;

import com.pos.kuppiya.pointofsale.dto.ItemDTO;
import com.pos.kuppiya.pointofsale.dto.paginated.PaginatedResponseItemDTO;
import com.pos.kuppiya.pointofsale.dto.request.ItemNameBalQtyStateUpdateDTO;
import com.pos.kuppiya.pointofsale.dto.request.ItemPriceBalQtyUpdateDTO;
import com.pos.kuppiya.pointofsale.dto.request.ItemSaveRequestDTO;

import java.util.List;

public interface ItemService {
    String addItem(ItemSaveRequestDTO itemSaveRequestDTO);

    List<ItemDTO> getItems();

    String updateItemPriceBalQty(int id, ItemPriceBalQtyUpdateDTO itemPriceBalQtyUpdateDTO);



    List<ItemSaveRequestDTO> getItemsByStatus(boolean status);

    void deleteItemById(int id);

    void updateNameBalQtyState(int id, ItemNameBalQtyStateUpdateDTO itemNameBalQtyStateUpdateDTO);

    int getCountOfActiveItems();

    long getCountItemByState(String state);

    PaginatedResponseItemDTO getAllItems(int page, int size);

    PaginatedResponseItemDTO getItemsByState(int page, int size, boolean status);
}

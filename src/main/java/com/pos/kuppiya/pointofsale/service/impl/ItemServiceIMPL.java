package com.pos.kuppiya.pointofsale.service.impl;


import com.pos.kuppiya.pointofsale.dto.ItemDTO;
import com.pos.kuppiya.pointofsale.dto.paginated.PaginatedResponseItemDTO;
import com.pos.kuppiya.pointofsale.dto.request.ItemNameBalQtyStateUpdateDTO;
import com.pos.kuppiya.pointofsale.dto.request.ItemPriceBalQtyUpdateDTO;
import com.pos.kuppiya.pointofsale.dto.request.ItemSaveRequestDTO;

import com.pos.kuppiya.pointofsale.entity.Item;
import com.pos.kuppiya.pointofsale.exceptions.EntryDuplicateException;
import com.pos.kuppiya.pointofsale.exceptions.NotFoundException;

import com.pos.kuppiya.pointofsale.repository.ItemRepo;
import com.pos.kuppiya.pointofsale.service.ItemService;

import com.pos.kuppiya.pointofsale.utill.mappers.ItemMappers;
import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ItemServiceIMPL implements ItemService {
    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ItemMappers itemMappers;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public String addItem(ItemSaveRequestDTO itemSaveRequestDTO) {
//        Item item = itemMappers.RequestDtoToEntity(itemSaveRequestDTO);
        Item item = modelMapper.map(itemSaveRequestDTO, Item.class);
        item.setActiveState(true);
        if (!itemRepo.existsById(item.getItemId())) return itemRepo.save(item).getItemName();
        else throw new EntryDuplicateException("id already exists!!!");

    }

    @Override
    public List<ItemDTO> getItems() {
        List<Item> getItems = itemRepo.findAll();


//        List<ItemDTO> itemDTOS = modelMapper.
//                map(getItems, new TypeToken<List<ItemDTO>>() {
//                }.getType());

        List<ItemDTO> itemDTO = itemMappers.listEntityToListDto(getItems);
        return itemDTO;
    }

    @Override
    public String updateItemPriceBalQty(int id, ItemPriceBalQtyUpdateDTO itemPriceBalQtyUpdateDTO) {
        if (itemRepo.existsById(id)) {
            Item item = itemRepo.getById(id);
//            item.setBalanceQty(itemPriceBalQtyUpdateDTO.getBalanceQty());
//            item.setSellingPrice(itemPriceBalQtyUpdateDTO.getSellingPrice());
//
//            itemRepo.save(item);
//            return item.getItemName();
            itemRepo.updateBalQtySellingPrice(itemPriceBalQtyUpdateDTO.getBalanceQty(), itemPriceBalQtyUpdateDTO.getSellingPrice(), id);

            return item.getItemName();
        }
        throw new NotFoundException("Id does not exist");
    }

    @Override
    public List<ItemSaveRequestDTO> getItemsByStatus(boolean status) {
        List<Item> item = itemRepo.findAllByActiveStateEquals(status);
        List<ItemSaveRequestDTO> itemSaveRequestDTOS = itemMappers.listEntityToListDtos(item);
        return itemSaveRequestDTOS;
    }

    @Override
    public void deleteItemById(int id) {
        if (itemRepo.existsById(id)){
            itemRepo.deleteById(id);



        }else {
            throw new NotFoundException("Item id does not exist!!!");
        }

    }

    @Override
    public void updateNameBalQtyState(int id, ItemNameBalQtyStateUpdateDTO itemNameBalQtyStateUpdateDTO) {
        if (itemRepo.existsById(id)){

            itemRepo.updateNameBalQtyState(itemNameBalQtyStateUpdateDTO.getItemName(),itemNameBalQtyStateUpdateDTO.getBalanceQty(),itemNameBalQtyStateUpdateDTO.isActiveState(),id);

        }else {
            throw new NotFoundException("id dors not exist!!!");
        }
    }

    @Override
    public int getCountOfActiveItems() {
        int item = itemRepo.countAllByActiveStateEquals(true);
        return item;
    }

    @Override
    public long getCountItemByState(String state) {
        if (state.equalsIgnoreCase("active") | state.equalsIgnoreCase("inactive")){
            boolean status = false;
            status= state.equalsIgnoreCase("active");
            long itemCount = itemRepo.countAllByActiveStateEquals(status);

            return itemCount;
        }else {
            return itemRepo.count();
        }


    }

    @Override
    public PaginatedResponseItemDTO getAllItems(int page, int size) {
        Page<Item> getAllItems = itemRepo.findAll(PageRequest.of(page, size));

        return new PaginatedResponseItemDTO(
                itemMappers.pageToListDto(getAllItems),
                itemRepo.count()
        );
    }

    @Override
    public PaginatedResponseItemDTO getItemsByState(int page, int size, boolean status) {
        Page<Item> getItemsByState = itemRepo.findAllByActiveStateEquals(status,PageRequest.of(page, size));
        return new PaginatedResponseItemDTO(
                itemMappers.pageToListDto(getItemsByState),
                20
        );
    }



}

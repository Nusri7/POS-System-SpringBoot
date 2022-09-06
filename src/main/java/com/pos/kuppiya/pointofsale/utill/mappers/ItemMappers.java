package com.pos.kuppiya.pointofsale.utill.mappers;

import com.pos.kuppiya.pointofsale.dto.ItemDTO;


import com.pos.kuppiya.pointofsale.dto.request.ItemSaveRequestDTO;
import com.pos.kuppiya.pointofsale.entity.Item;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;


import java.util.List;


@Mapper(componentModel = "spring")
public interface ItemMappers {


    List<ItemDTO>  listEntityToListDto(List<Item> getItems);
    List<ItemSaveRequestDTO>  listEntityToListDtos(List<Item> getItems);
    List<ItemDTO> pageToListDto(Page<Item> page);

}

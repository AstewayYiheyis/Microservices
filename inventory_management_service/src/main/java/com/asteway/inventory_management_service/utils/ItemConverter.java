package com.asteway.inventory_management_service.utils;

import com.asteway.inventory_management_service.dtos.ItemDTO;
import com.asteway.inventory_management_service.entities.Item;
import org.springframework.beans.BeanUtils;

public class ItemConverter {
    public static ItemDTO getItemDTO(Item item){
        ItemDTO itemDTO = new ItemDTO();

        BeanUtils.copyProperties(item, itemDTO);

        return itemDTO;
    }

    public static Item getItemFromDTO(ItemDTO itemDTO){
        Item item = new Item();

        BeanUtils.copyProperties(itemDTO, item);

        return item;
    }
}

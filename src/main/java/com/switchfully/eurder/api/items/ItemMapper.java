package com.switchfully.eurder.api.items;

import com.switchfully.eurder.domain.items.Item;
import com.switchfully.eurder.service.ItemService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ItemMapper {
    private final ItemService itemService;

    public ItemMapper(ItemService itemService) {
        this.itemService = itemService;
    }

    public Item mapCreateItemDTOToItem(CreateItemDTO createItemDTO) {
        return new Item(createItemDTO.getName(), createItemDTO.getDescription(), createItemDTO.getPrice(), createItemDTO.getStock());
    }

    public Item mapUpdateItemDTOToItem(UpdateItemDTO updateItemDTO, UUID id) {
        Item itemToUpdate = itemService.getItemById(id);
        itemToUpdate.setStock(updateItemDTO.getStock());
        itemToUpdate.setDescription(updateItemDTO.getDescription());
        itemToUpdate.setPrice(updateItemDTO.getPrice());
        itemToUpdate.setName(updateItemDTO.getName());
        return itemToUpdate;
    }
}

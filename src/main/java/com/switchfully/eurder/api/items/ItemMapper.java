package com.switchfully.eurder.api.items;

import com.switchfully.eurder.domain.items.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {
    public Item mapCreateItemDTOToItem(CreateItemDTO createItemDTO) {
        return new Item(createItemDTO.getName(), createItemDTO.getDescription(), createItemDTO.getPrice(), createItemDTO.getStock());
    }
}

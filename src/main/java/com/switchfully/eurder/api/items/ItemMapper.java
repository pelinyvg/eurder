package com.switchfully.eurder.api.items;

import com.switchfully.eurder.domain.items.Item;
import com.switchfully.eurder.service.ItemService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    public ItemDTO mapToItemDTOList(Item item) {
        return new ItemDTO(item.getId().toString(), item.getName(),item.getDescription(),item.getPrice(),item.getStock(),item.getStockAvailability());
    }

    public List<ItemDTO> mapToItemDTOList(List<Item> items) {
        return items.stream().map(this::mapToItemDTOList).collect(Collectors.toList());
    }
}

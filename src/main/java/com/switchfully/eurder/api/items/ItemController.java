package com.switchfully.eurder.api.items;

import com.switchfully.eurder.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
public class ItemController {
    private final ItemService service;
    private final ItemMapper itemMapper;

    public ItemController(ItemService service, ItemMapper itemMapper) {
        this.service = service;
        this.itemMapper = itemMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addItem(@RequestBody CreateItemDTO createItemDTO) {
        service.createItem(itemMapper.mapCreateItemDTOToItem(createItemDTO));
    }
}

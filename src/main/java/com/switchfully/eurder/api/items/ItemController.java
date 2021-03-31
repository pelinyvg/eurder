package com.switchfully.eurder.api.items;

import com.switchfully.eurder.service.ItemService;
import com.switchfully.eurder.service.SecurityService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;
    private final ItemMapper itemMapper;
    private final SecurityService securityService;

    public ItemController(ItemService itemService, ItemMapper itemMapper, SecurityService securityService) {
        this.itemService = itemService;
        this.itemMapper = itemMapper;
        this.securityService = securityService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addItem(@RequestBody CreateItemDTO createItemDTO,
                        @RequestHeader(value = "Authorization", required = false) String userId)
            throws IllegalAccessException {
        securityService.throwExceptionIfNotAdmin(userId);
        itemService.createItem(itemMapper.mapCreateItemDTOToItem(createItemDTO));
    }
}

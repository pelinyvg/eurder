package com.switchfully.eurder.api.items;

import com.switchfully.eurder.service.ItemService;
import com.switchfully.eurder.service.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/items")
public class ItemController {
    final static Logger logger = LoggerFactory.getLogger(ItemController.class);
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
        logger.info("New item is created: " + createItemDTO.toString());
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateItem(@RequestBody UpdateItemDTO updateItemDTO,
                           @PathVariable UUID id,
                           @RequestHeader(value = "Authorization", required = false) String userId) throws IllegalAccessException {
        securityService.throwExceptionIfNotAdmin(userId);
        itemService.updateItem(id, itemMapper.mapUpdateItemDTOToItem(updateItemDTO, id));
        logger.info("Item " + updateItemDTO.getName() + " is updated: " + updateItemDTO.toString());
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ItemDTO> getOverviewItemsWithStocks(@RequestHeader(value = "Authorization", required = false) String userId,
                                                    @RequestParam(required = false) Integer filter) throws IllegalAccessException {
        securityService.throwExceptionIfNotAdmin(userId);
        return itemMapper.mapToItemDTOList(itemService.getOverviewItems(filter));
    }
}

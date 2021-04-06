package com.switchfully.eurder.service;

import com.switchfully.eurder.domain.items.Item;
import com.switchfully.eurder.domain.items.ItemRepository;
import com.switchfully.eurder.infrastructure.util.ValidationUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ItemService {
    private final ItemRepository repository;

    public ItemService(ItemRepository repository) {
        this.repository = repository;
    }

    public void createItem(Item item) {
        repository.saveItem(item);
    }

    public Item getItemById(UUID id) {
        Item item = repository.getItemById(id);
        if (ValidationUtil.isNull(item)) {
            throw new IllegalArgumentException("No item found!");
        }
        return item;
    }

    public void updateItem(UUID id, Item item) {
        repository.updateItem(id, item);
    }

    public List<Item> getOverviewItems(Integer filter) {
        return repository.getItemsByStockAvailability(filter);
    }
}

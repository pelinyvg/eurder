package com.switchfully.eurder.domain.items;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ItemRepository {
    private final Map<UUID, Item> itemRepo;

    public ItemRepository() {
        this.itemRepo = new HashMap<>();
    }

    public void saveItem(Item item) {
        itemRepo.put(item.getId(), item);
    }

    public List<Item> getItems() {
        return new ArrayList<>(itemRepo.values());
    }
}

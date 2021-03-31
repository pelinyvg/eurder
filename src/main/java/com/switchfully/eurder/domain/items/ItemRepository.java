package com.switchfully.eurder.domain.items;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ItemRepository {
    private final Map<UUID, Item> itemRepo;

    public ItemRepository() {
        this.itemRepo = new HashMap<>();
        Item item = new Item(UUID.fromString("256f5696-e93d-56e8-8d90-745850123805"), "bread", "white", new Price(2.4, Currency.getInstance("EUR")), 5);
        itemRepo.put(item.getId(), item);
    }

    public void saveItem(Item item) {

        itemRepo.put(item.getId(), item);
    }

    public List<Item> getItems() {
        return new ArrayList<>(itemRepo.values());
    }

    public Item getItemById(UUID itemId) {
        return itemRepo.get(itemId);
    }

    public void updateItem(UUID id, Item item) {
        itemRepo.put(id, item);
    }
}

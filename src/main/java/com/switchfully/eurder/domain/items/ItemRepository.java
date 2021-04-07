package com.switchfully.eurder.domain.items;

import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class ItemRepository {
    private final static Predicate<Item> STOCK_LOW = item -> item.getStock() < 5;
    private final static Predicate<Item> STOCK_MEDIUM = item -> item.getStock() < 10;
    private final static Predicate<Item> STOCK_HIGH = item -> item.getStock() >= 10;

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

    public List<Item> getItemsByStockAvailability(Integer filter) {
        setStockAvailabilities();
        if (filter == null) {
            return itemRepo.values().stream().sorted(Comparator.comparing(Item::getStockAvailability)).collect(Collectors.toList());
        }
        return itemRepo.values().stream().filter(item -> item.getStockAvailability().ordinal() == filter).collect(Collectors.toList());
    }

    private void setStockAvailabilities() {
        itemRepo.values().stream().filter(STOCK_HIGH).forEach(item -> item.setStockInfo(StockAvailability.STOCK_HIGH));
        itemRepo.values().stream().filter(STOCK_MEDIUM).forEach(item -> item.setStockInfo(StockAvailability.STOCK_MEDIUM));
        itemRepo.values().stream().filter(STOCK_LOW).forEach(item -> item.setStockInfo(StockAvailability.STOCK_LOW));
    }
}

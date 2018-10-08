package com.rnaomix.itemmanagement.service;

import com.rnaomix.itemmanagement.model.ItemHistoryDetail;

import java.util.Map;

public interface ShoppingCartService {

    Map<Integer, Integer> getItemsInCart();

    Integer getTotal();

    void addShoppingCart(Integer itemId, Integer amount);

    void removeShoppingCart(Integer itemId);
}

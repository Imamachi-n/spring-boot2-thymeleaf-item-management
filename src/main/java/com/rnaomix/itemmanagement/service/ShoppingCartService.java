package com.rnaomix.itemmanagement.service;

import com.rnaomix.itemmanagement.model.ItemHistoryDetail;

import java.util.List;
import java.util.Map;

public interface ShoppingCartService {

    Map<Integer, Integer> getItemsInCart();

    List<Integer> getItemIdListInCart();

    Integer getTotal();

    void addShoppingCart(Integer itemId, Integer amount);

    void removeShoppingCart(Integer itemId);

    void clearCart();
}

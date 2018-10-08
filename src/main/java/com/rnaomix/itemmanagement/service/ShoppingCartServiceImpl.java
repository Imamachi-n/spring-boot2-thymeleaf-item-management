package com.rnaomix.itemmanagement.service;

import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import javax.transaction.Transactional;
import java.util.*;

@Service
@SessionScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService{

    // itemId -> amount cartList
    private Map<Integer, Integer> cartList;

    public ShoppingCartServiceImpl(){
        this.cartList = new HashMap<>();
    }

    // 編集不可能なMapを返す
    @Override
    public Map<Integer, Integer> getItemsInCart(){
        return Collections.unmodifiableMap(cartList);
    }

    // 編集不可能なitemIdのArrayListを返す
    @Override
    public List<Integer> getItemIdListInCart(){
        return Collections.unmodifiableList(new ArrayList<>(cartList.keySet()));
    }

    @Override
    public Integer getTotal(){
        return cartList.size();
    }

    @Override
    public void addShoppingCart(Integer itemId, Integer amount){
        if (cartList.containsKey(itemId)){
            cartList.replace(itemId, cartList.get(itemId) + amount);
        }else{
            cartList.put(itemId, amount);
        }
    }

    @Override
    public void removeShoppingCart(Integer itemId){

    }
}

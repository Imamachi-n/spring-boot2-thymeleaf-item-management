package com.rnaomix.itemmanagement.service;

import com.rnaomix.itemmanagement.model.ItemHistoryDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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

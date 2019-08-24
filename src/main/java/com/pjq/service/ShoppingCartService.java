package com.pjq.service;

import com.pjq.dao.ShoppingCartDao;
import com.pjq.pojo.ShoppingCart;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("shoppingCartService")
@Scope("prototype")
public class ShoppingCartService {

    @Resource
    private ShoppingCartDao shoppingCartMapper;

    public void addToShoppingCart(String username,String product_name,double price){
        ShoppingCart shoppingCart=shoppingCartMapper.selectProduct(username, product_name);
        if(shoppingCart!=null)
        {
            int number=shoppingCart.getNumber();
            number+=1;
            shoppingCartMapper.insertIntoBuplicates(username, product_name,number);
        }
        else
        {
            shoppingCartMapper.insertIntoShoppingCart(username, product_name, price);
        }

    }

    public List<ShoppingCart> showShoppingCart(String username){
        return shoppingCartMapper.selectShoppingCart(username);
    }

    public void cutProduct_name(String username,String product_name){
        ShoppingCart shoppingCart=shoppingCartMapper.selectProduct(username, product_name);
        if(shoppingCart.getNumber()>1)
        {
            int number=shoppingCart.getNumber()-1;
            shoppingCartMapper.cutProduct(username,product_name,number);
        }
        else
        {
            shoppingCartMapper.delteProduct(username, product_name);
        }
    }

}

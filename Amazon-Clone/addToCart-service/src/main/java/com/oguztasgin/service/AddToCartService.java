package com.oguztasgin.service;

import com.oguztasgin.manager.IProductManager;
import com.oguztasgin.repository.IAddToCartRepository;
import com.oguztasgin.repository.entity.Cart;
import com.oguztasgin.repository.entity.CartDetails;
import com.oguztasgin.repository.entity.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class AddToCartService {
    private final IAddToCartRepository addToCartRepository;
    private final IProductManager productManager;

    public AddToCartService(IAddToCartRepository addToCartRepository,IProductManager productManager) {
        this.addToCartRepository = addToCartRepository;
        this.productManager = productManager;
    }

    public void addItemToCartService(Cart cart) {
        addToCartRepository.save(cart);
    }

    public void removeItemFromCartService(Cart cart) {
        addToCartRepository.delete(cart);
    }

    public CartDetails displayAllProductsInCart(UUID userId) {
        CartDetails cartDetails=new CartDetails();
        cartDetails.setUserId(userId);

        List<Cart> cartList = addToCartRepository.findByUserId(userId);
        List<Product> productList=new ArrayList<Product>();

        for(int i=0;i<cartList.size();i++) {
            ResponseEntity<Product> product=productManager.getProductById(cartList.get(i).getProductId());
            productList.add(product.getBody());
        }

        cartDetails.setList(productList);
        return cartDetails;
    }
}

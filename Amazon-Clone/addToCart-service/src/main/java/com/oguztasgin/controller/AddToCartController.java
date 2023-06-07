package com.oguztasgin.controller;

import com.oguztasgin.repository.entity.Cart;
import com.oguztasgin.repository.entity.CartDetails;
import com.oguztasgin.service.AddToCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.oguztasgin.constants.ApiUrls.ADDTOCART;

@RestController
@RequiredArgsConstructor
@RequestMapping(ADDTOCART)
public class AddToCartController {
    private final AddToCartService addToCartService;

    @PostMapping("/add")
    public void addToCart(@RequestBody Cart cart) {
        addToCartService.addItemToCartService(cart);
    }
    @DeleteMapping("/remove")
    public void removeFromCart(@RequestBody Cart cart) {
        addToCartService.removeItemFromCartService(cart);
    }

    @GetMapping("/show/{userId}")
    public CartDetails showItems(@PathVariable UUID userId) {
        return addToCartService.displayAllProductsInCart(userId);
    }


}

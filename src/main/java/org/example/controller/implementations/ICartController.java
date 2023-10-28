package org.example.controller.implementations;

import java.io.IOException;

public interface ICartController {
    void addToCart(int productId) throws IOException;

    void printCart();
}

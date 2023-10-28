package org.example.controller;

import org.example.controller.implementations.ICartController;
import org.example.models.Cart;
import org.example.models.CartProduct;
import org.example.models.Product;
import org.example.models.User;
import org.example.utils.AppException;
import org.example.utils.StringUtils;
import org.example.view.CartPage;

import java.io.IOException;
import java.util.ArrayList;

import static org.example.utils.AppInput.enterInt;
import static org.example.utils.LoadUtils.getProducts;
import static org.example.utils.UserUtils.getLoggedInUser;
import static org.example.utils.UserUtils.setLoggedInUser;
import static org.example.utils.Utils.println;

public class CartController implements ICartController {

    private final HomeController homeController;
    private final OrderController orderController;
    private final CartPage cartPage;

    public CartController(HomeController homeController) {
        this.homeController = homeController;
        orderController = new OrderController(homeController);
        cartPage = new CartPage();
    }


    @Override
    public void addToCart (int productId) throws IOException {
        User loggedInUser = getLoggedInUser();
        ArrayList<Product> products = getProducts();

        Product userProduct = null;
        for (Product product : products) {
            if (product.getId() == productId) {
                userProduct = product;
                break;
            }
        }

        if (loggedInUser.getUserCart() != null) {
            Cart cart = loggedInUser.getUserCart();

            boolean isFound = false;

                for (CartProduct cartProduct : cart.getCartProducts()) {
                    if (cartProduct.getProduct().getId() == productId) {
                        cartProduct.setCount(cartProduct.getCount() + 1);
                        isFound = true;
                    }

            }

            if (!isFound) {
                cart.getCartProducts().add(new CartProduct(userProduct, 1));
            }

            loggedInUser.setUserCart(cart);
        } else {
            Cart cart = new Cart();
            ArrayList<CartProduct> cartProducts = new ArrayList<>();
            cartProducts.add(new CartProduct(userProduct, 1));
            cart.setCartProducts(cartProducts);
            loggedInUser.setUserCart(cart);
        }
        setLoggedInUser(loggedInUser);
    }
    private void checkout() {
        orderController.checkout();
    }

    private void invalidChoice(AppException appException) {
        println(appException.getMessage());
        printCart();
    }

    @Override
    public void printCart ( ) {
        User loggedInUser = getLoggedInUser();
        if (loggedInUser.getUserCart() == null) {
            cartPage.printEmptyCart();
            homeController.printHomeMenu ();
        } else {
            ArrayList<CartProduct> cartProducts = loggedInUser.getUserCart().getCartProducts();
            cartPage.printCart(cartProducts);

            cartPage.printCheckout();
            cartPage.printBack();

            try {
                int choice = enterInt( StringUtils.ENTER_CHOICE);
                if (choice == 88) {
                    checkout();
                } else if (choice == 00) {
                    homeController.printHomeMenu ();
                } else {
                    invalidChoice(new AppException(StringUtils.INVALID_CHOICE));
                }
            } catch (AppException appException) {
                invalidChoice(appException);
            }

        }
    }
}

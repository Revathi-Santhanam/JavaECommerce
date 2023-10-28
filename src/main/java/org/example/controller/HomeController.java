package org.example.controller;

import org.example.controller.implementations.IHomeController;
import org.example.utils.AppException;
import org.example.utils.StringUtils;
import org.example.view.HomePage;

import java.io.IOException;

import static org.example.utils.AppInput.enterInt;
import static org.example.utils.UserUtils.setLoggedInUser;
import static org.example.utils.Utils.println;

public class HomeController implements IHomeController {
    private  final HomePage homePage;
    private final AuthController authController;
    private final CategoryController categoryController;
    private final ProductController productController;
    private final  CartController cartController;
    private final OrderController orderController;

    public HomeController ( AuthController authController) {
        homePage=new HomePage ();
        this.authController=authController;
        productController = new ProductController ( this );
        categoryController=new CategoryController ( this );
        cartController=new CartController (this);
        orderController=new OrderController (this);

    }

    @Override
    public void printHomeMenu ( ) {
        homePage.printHomeMenu();
        try {
            int choice = enterInt( StringUtils.ENTER_CHOICE);
            if (choice == 1) {
                categoryController.printCategoryMenu ();
            } else if (choice == 2) {
                productController.showProducts(0);
            } else if (choice == 3) {
                cartController.printCart();
            } else if (choice == 4) {
                orderController.printOrders();
                printHomeMenu ();
            } else if (choice == 5) {
                setLoggedInUser(null);
                authController.authMenu();
            } else {
                invalidChoice(new AppException(StringUtils.INVALID_CHOICE));
            }
        } catch (AppException appException) {
            invalidChoice(appException);
        } catch (IOException e) {
            throw new RuntimeException ( e );
        }

    }



    private void invalidChoice(AppException appException) {
        println(appException.getMessage());
        printHomeMenu ();
    }
}

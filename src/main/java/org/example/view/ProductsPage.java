package org.example.view;

import org.example.models.Product;
import org.example.utils.StringUtils;

import java.util.ArrayList;

import static org.example.utils.Utils.println;

public class ProductsPage {

    public void printProductsMenu (ArrayList<Product> products) {

        try {
            println ( "#---------------------#" );
            println ( StringUtils.PRODUCT_MENU );
            println ( "#---------------------#" );
            Thread.sleep ( 1000 );
        } catch (InterruptedException e) {
            throw new RuntimeException ( e );
        }
        for (Product product : products) {
            println ( product.getId ( ) + ". " + product.getTitle ( ) + " - Rs." + product.getPrice ( ) );
        }
        println ( StringUtils.BACK_OPTION );
    }

    public void printSuccess ( ) {
        try {
            println ( "#---------------------#" );
            println ( StringUtils.CART_SUCCESS );
            println ( "#---------------------#" );
            Thread.sleep ( 1000 );
        } catch (InterruptedException e) {
            throw new RuntimeException ( e );
        }
    }

}

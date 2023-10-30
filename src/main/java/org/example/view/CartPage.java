package org.example.view;

import org.example.models.CartProduct;
import org.example.utils.StringUtils;

import java.io.*;
import java.util.ArrayList;

import static org.example.utils.FileUtil.getCartFile;
import static org.example.utils.FileUtil.getProductsFile;
import static org.example.utils.UserUtils.loggedInUser;
import static org.example.utils.Utils.print;
import static org.example.utils.Utils.println;

public class CartPage {
    public void printEmptyCart ( ) {
        println( StringUtils.EMPTY_CART);
    }

    public void printCart (ArrayList<CartProduct> cartProducts) {
        println(StringUtils.CART);
        double total = 0;
        for (CartProduct cartProduct : cartProducts) {
            total += cartProduct.getCount() * cartProduct.getProduct().getPrice();
            println(cartProduct.getProduct().getTitle() + " x " + cartProduct.getCount());
        }
        println(StringUtils.TOTAL_PRICE + total);
        addCartToFile ();
    }
    private void addCartToFile ( ) {
        try {
            FileWriter fileWriter = new FileWriter ( getCartFile ( ), true );
            fileWriter.write ( "\n" );
            fileWriter.write ( loggedInUser.getName ( ) + "," + loggedInUser.getEmail ( ) );
            for (CartProduct cartProduct : loggedInUser.getUserCart ( ).getCartProducts ( )) {
                fileWriter.write ( "," + cartProduct.getProduct ( ).getId ( ) + "," + cartProduct.getProduct ( ).getTitle ( ) + "," + cartProduct.getCount ( ) + "," + cartProduct.getCount ( ) * cartProduct.getProduct ( ).getPrice ( ) );

            }
            fileWriter.flush ( );
            fileWriter.close ( );

        } catch (IOException e) {
            throw new RuntimeException ( e );
        }
    }

    public void printCheckout ( ) {
        println("#---------------------#");
        println(StringUtils.PRINT_CHECKOUT);

    }
    public void printCartFromFile() throws IOException {
        String line = "";
        BufferedReader br = new BufferedReader ( new FileReader ( getCartFile () ) );
        while ((line = br.readLine ( )) != null) {
            if (!line.startsWith ( loggedInUser.getName () )) {
                String[] cartArray = line.split ( "," );
                print(cartArray[2] );
            }
        }

    }

    public void printBack ( ) {
        println(StringUtils.BACK_OPTION);
    }
}

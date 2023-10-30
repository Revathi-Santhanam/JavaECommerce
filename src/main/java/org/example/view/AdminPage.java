package org.example.view;

import org.example.utils.StringUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static org.example.utils.FileUtil.getCredentialsFile;
import static org.example.utils.Utils.println;

public class AdminPage {
    public void printAdminPage ( ) {
        println( StringUtils.WELCOME_ADMIN_MESSAGE);
        println ( StringUtils.ADMIN_MENU );
    }


    public void productDeletedMessage ( ) {
        println ( StringUtils.PRODUCT_DELETED );
    }
}

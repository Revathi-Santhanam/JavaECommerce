package org.example.view;

import org.example.utils.StringUtil;

import static org.example.utils.Utils.println;

public class WelcomePage {
    public void welcome ( ) {
       try {
           println( StringUtil.WELCOME_MESSAGE );
           Thread.sleep ( 1000 );
       } catch (InterruptedException e) {
           throw new RuntimeException ( e );
       }
    }

    public void printAuthMenu ( ) {
        println ( StringUtil.AUTH_MENU );
    }
}

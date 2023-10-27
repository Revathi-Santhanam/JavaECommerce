package org.example.view;

import org.example.utils.StringUtil;

import static org.example.utils.Utils.println;

public class RegisterPage {
    public void printRegisterSuccess ( ) {
        try {

            println("#---------------------#");
            println( StringUtil.REGISTRATION_SUCCESSFUL );
            println("#---------------------#");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void passwordMisMatch ( ) {
        try {
            println("#---------------------#");
            println(StringUtil.PASSWORD_MISMATCH);
            println("#---------------------#");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

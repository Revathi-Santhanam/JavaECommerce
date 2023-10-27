package org.example.utils;

import java.util.InputMismatchException;

import static org.example.utils.AppScanner.getScanner;
import static org.example.utils.Utils.print;

public class AppInput {
    public static int enterInt (String msg) throws AppException {
        print ( msg );
        int input = 0;
        try {
            input = Integer.parseInt ( getScanner ().nextLine () );
        } catch (Exception e) {
            throw new AppException ( StringUtil.INVALID_CHOICE);
        }
        return input;
    }

    public static String enterString (String msg) {
        print ( msg );
        return getScanner ( ).nextLine ( );
    }
}

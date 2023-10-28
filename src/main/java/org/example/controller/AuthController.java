package org.example.controller;

import org.example.controller.implementations.IAuthController;
import org.example.models.Role;
import org.example.models.User;
import org.example.utils.AppException;
import org.example.utils.StringUtils;
import org.example.view.AuthPage;
import org.example.view.LoginPage;
import org.example.view.RegisterPage;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static org.example.utils.AppInput.enterInt;
import static org.example.utils.AppInput.enterString;
import static org.example.utils.FileUtil.getCredentialsFile;
import static org.example.utils.UserUtils.setLoggedInUser;
import static org.example.utils.Utils.println;

public class AuthController implements IAuthController {

    private final HomeController homeController;
    private final AuthPage authPage;
    private final LoginPage loginPage;
    private final RegisterPage registerPage;

    public AuthController () {
        authPage=new AuthPage ();
        homeController = new HomeController ( this);
        loginPage = new LoginPage ( );
        registerPage = new RegisterPage ( );
    }

    @Override
    public void authMenu ( ) {
        authPage.printAuthMenu ( );
        int choice = 0;
        try {
            choice = enterInt ( StringUtils.ENTER_CHOICE );
            if (choice==99){
                authPage.printThankYou();
                System.exit(0);
            }else{
                if (choice == 1) {
                    login ( );
                } else if (choice == 2) {
                    register ( );
                } else {
                    invalidChoice ( new AppException ( StringUtils.INVALID_CHOICE ) );

                }
            }


        } catch (AppException appException) {
            invalidChoice ( appException );
        }

    }
    @Override
    public void login ( ) {
        String email, password;
        email = enterString ( StringUtils.ENTER_EMAIL );
        password = enterString ( StringUtils.ENTER_PASSWORD );
        User user = validateUser ( email, password );
        if (user != null) {
            setLoggedInUser(user);
            homeController.printHomeMenu ( );
        } else {
            loginPage.printInvalidCredentials ( );
            authMenu ( );
        }
    }



    @Override
    public void register ( ) {
        String name, email, password, confirmPassword;
        name = enterString ( StringUtils.ENTER_NAME );
        email = enterString ( StringUtils.ENTER_EMAIL );
        password = enterString ( StringUtils.ENTER_PASSWORD );
        confirmPassword = enterString ( StringUtils.ENTER_PASSWORD_AGAIN );
        if (password.equals ( confirmPassword )) {
            try {
                FileWriter fileWriter = new FileWriter ( getCredentialsFile ( ), true );
                int id = (int) (Math.random ( ) * 100);
                fileWriter.append ( "\n" );
                fileWriter.append ( id + "," + name + "," + email + "," + password );
                fileWriter.flush ( );
                fileWriter.close ( );
                registerPage.printRegisterSuccess ( );

            } catch (IOException e) {
                throw new RuntimeException ( e );
            }
        } else {
            registerPage.passwordMisMatch ( );
        }
        authMenu ( );

    }
    @Override
    public void logout ( ) {

    }
    private User validateUser (String email, String password) {
        try {
            Scanner scanner = new Scanner ( getCredentialsFile ( ) );
            while (scanner.hasNext ( )) {
                String val = scanner.next ( ).trim ( );
                if (!val.startsWith ( "id" )) {
                    String[] userArr = val.split ( "," );
                    if (userArr[2].equals ( email ) && userArr[3].equals ( password )) {
                        User user = new User ( );
                        user.setId ( Integer.parseInt ( userArr[0] ) );
                        user.setName ( userArr[1] );
                        user.setEmail ( userArr[2] );
                        user.setPassword ( userArr[3] );
                        if (user.getEmail ( ).equals ( "admin@123.com" )) user.setRole ( Role.ADMIN );
                        else user.setRole ( Role.USER );
                        return user;
                    }
                }
            }
            scanner.close ( );
        } catch (FileNotFoundException e) {
            e.printStackTrace ( );
        }
        return null;
    }
    private void invalidChoice (AppException e) {
        println ( e.getMessage ( ) );
        authMenu ( );
    }
}

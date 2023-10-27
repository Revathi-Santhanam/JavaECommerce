package org.example.controller;

import org.example.controller.implementations.IAppController;
import org.example.utils.StringUtil;
import org.example.utils.Utils;
import org.example.view.WelcomePage;

import static org.example.utils.Utils.println;


public class AppController implements IAppController {
    private final WelcomePage welcomePage;
    private final AuthController authController;
    public AppController(){
        welcomePage=new WelcomePage ();
        authController=new AuthController (this );
    }
    @Override
    public void init ( ) {
       welcomePage.welcome();
       authController.authMenu ();
    }

    @Override
    public void printAuthMenu ( ) {
      welcomePage.printAuthMenu();
    }


}

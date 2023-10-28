package org.example.controller;

import org.example.controller.implementations.IAppController;
import org.example.view.WelcomePage;


public class AppController implements IAppController {
    private final WelcomePage welcomePage;
    private final AuthController authController;
    public AppController(){
        welcomePage=new WelcomePage ();
        authController=new AuthController ();
    }
    @Override
    public void init ( ) {
       welcomePage.welcome();
       authController.authMenu ();
    }




}

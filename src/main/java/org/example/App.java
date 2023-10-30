package org.example;
import org.example.controller.AdminController;
import org.example.controller.AppController;
import org.example.utils.AppException;

import java.io.IOException;


public class App {
    public static void main (String[] args) throws AppException, IOException {
        AppController appController = new AppController ();
        AdminController adminController=new AdminController ();
        adminController.editProducts ();
//        appController.init ();

    }
}
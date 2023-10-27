package org.example.controller;

import org.example.controller.implementations.IHomeController;

import static org.example.utils.Utils.println;

public class HomeController implements IHomeController {
    @Override
    public void printMenu ( ) {
        println("====================");
        println ( "Login Successfull" );
        println("=====================");
    }
}

package org.example.controller.implementations;

import com.opencsv.exceptions.CsvException;
import org.example.utils.AppException;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IAdminController {
    void addProducts() throws AppException, IOException;
    void editProducts() throws AppException;
    void deleteProducts() throws AppException, IOException, CsvException;
    void viewProducts();
    void viewUserDetails() throws IOException, InterruptedException;
    void viewOrderDetails() throws IOException, InterruptedException;
    void printAdminMenu ( );
}

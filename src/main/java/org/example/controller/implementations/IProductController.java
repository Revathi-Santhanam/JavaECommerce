package org.example.controller.implementations;

import org.example.utils.AppException;

import java.io.IOException;

public interface IProductController {
    void showProducts(int categoryId) throws IOException, AppException;
}

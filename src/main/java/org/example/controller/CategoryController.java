package org.example.controller;

import org.example.controller.implementations.ICategoryController;
import org.example.models.Category;
import org.example.utils.AppException;
import org.example.utils.StringUtils;
import org.example.view.CategoryPage;

import java.io.IOException;
import java.util.ArrayList;

import static org.example.utils.AppInput.enterInt;
import static org.example.utils.LoadUtils.getCategories;
import static org.example.utils.Utils.println;

public class CategoryController implements ICategoryController {
    private final CategoryPage categoryPage;
    private final ProductController productController;
    private final HomeController homeController;

    public CategoryController(HomeController homeController){
        categoryPage=new CategoryPage ();
        productController= new ProductController (homeController);
        this.homeController=homeController;
    }


    @Override
    public void printCategoryMenu ( ) throws IOException {
        ArrayList<Category> categories = getCategories();
        categoryPage.printCategoryMenu(categories);
        try {
            int choice = enterInt( StringUtils.ENTER_CHOICE);

            if (choice == 00) {
                homeController.printHomeMenu ();
            } else {
                int validCategoryId = 0;

                for (Category category : categories) {
                    if (category.getId() == choice) {
                        validCategoryId = category.getId();
                        break;
                    }
                }

                if (validCategoryId != 0) {
                    productController.showProducts(validCategoryId);
                } else {
                    invalidChoice(new AppException(StringUtils.INVALID_CHOICE));
                }
            }
        } catch (AppException appException) {
            invalidChoice(appException);
        }

    }

    private void invalidChoice (AppException appException) throws IOException {
        println(appException.getMessage ());
        printCategoryMenu ();
    }
}

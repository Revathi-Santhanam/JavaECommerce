package org.example.view;

import org.example.models.Category;
import org.example.utils.StringUtils;

import java.util.ArrayList;

import static org.example.utils.Utils.println;

public class CategoryPage {

    public void printCategoryMenu (ArrayList<Category> categories) {
        try {
            println("#---------------------#");
            println( StringUtils.CATEGORY_MENU );
            println("#---------------------#");
            Thread.sleep ( 1000 );
        } catch (InterruptedException e) {
            throw new RuntimeException ( e );
        }

        for (Category category : categories) {
            println(category.getId() + ". " + category.getCategoryName());
        }

        println ( StringUtils.BACK_OPTION );
//        categories.clear ();
    }
}

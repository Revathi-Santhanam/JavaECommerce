package org.example.utils;

import org.example.models.Category;
import org.example.models.Product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static org.example.utils.FileUtil.getProductsFile;
import static org.example.utils.FileUtil.getcategoriesFile;

public class LoadUtils {
    private static ArrayList<Category> categories = new ArrayList<> ( );
    private static ArrayList<Product> products = new ArrayList<> ( );

    public static ArrayList<Category> getCategories ( ) throws IOException {
        categories.clear ();
        String line = "";
        BufferedReader br = new BufferedReader ( new FileReader ( getcategoriesFile ( ) ) );
        while ((line = br.readLine ( )) != null) {
            if (!line.startsWith ( "id" )) {
                String[] categoriesArray = line.split ( "," );
//                     System.out.println ( categoriesArray[0] + " " + categoriesArray[1] );
//                Category category = new Category ( Integer.parseInt ( categoriesArray[0] ), categoriesArray[1] );
//                categories.add ( category );
                if(categoriesArray[1]=="Women's Fashion"){
                    Category womenFashionCategory = new Category ( Integer.parseInt ( categoriesArray[0] ),categoriesArray[1] );
                    categories.add ( womenFashionCategory );
                } else if (categoriesArray[1]=="Men's Fashion") {
                    Category menFashionCategory = new Category ( Integer.parseInt ( categoriesArray[0] ),categoriesArray[1] );
                    categories.add ( menFashionCategory );
                } else if (categoriesArray[1]=="Electronic Appliances") {
                    Category electronicCategory = new Category ( Integer.parseInt ( categoriesArray[0] ),categoriesArray[1] );
                    categories.add ( electronicCategory );
                } else if (categoriesArray[1]=="Women's Accessories") {
                    Category womenAccessoryCategory = new Category ( Integer.parseInt ( categoriesArray[0] ),categoriesArray[1] );
                    categories.add ( womenAccessoryCategory );
                }else{
                    Category menAccessoryCategory = new Category ( Integer.parseInt ( categoriesArray[0] ),categoriesArray[1] );
                    categories.add ( menAccessoryCategory );
                }
            }
        }
        return categories;
    }


    public static ArrayList<Product> getProducts ( ) throws IOException {
        products.clear ();
        String line = "";
        BufferedReader br = new BufferedReader ( new FileReader ( getProductsFile ( ) ) );
        while ((line = br.readLine ( )) != null) {
            if (!line.startsWith ( "id" )) {
                String[] productsArray = line.split ( "," );
//                    System.out.println ( productsArray[0] + " " + productsArray[1] );
//                      Product product=new Product ( Integer.parseInt ( productsArray[0] ),productsArray[1],productsArray[2],Double.parseDouble ( productsArray[3] ),Integer.parseInt ( productsArray[4] ),new Category ( Integer.parseInt ( productsArray[6] ),productsArray[5] ) );
//                      products.add ( product );
                if (productsArray[6] == "1") {
                    Category womenFashionCategory = new Category ( Integer.parseInt ( productsArray[6] ), productsArray[5] );
                    Product womenFashionProduct = new Product ( Integer.parseInt ( productsArray[0] ), productsArray[1], productsArray[2], Double.parseDouble ( productsArray[3] ), Integer.parseInt ( productsArray[4] ), womenFashionCategory );
                    products.add ( womenFashionProduct );
                } else if (productsArray[6] == "2") {
                    Category menFashionCategory = new Category ( Integer.parseInt ( productsArray[6] ), productsArray[5] );
                    Product menFashionProduct = new Product ( Integer.parseInt ( productsArray[0] ), productsArray[1], productsArray[2], Double.parseDouble ( productsArray[3] ), Integer.parseInt ( productsArray[4] ), menFashionCategory );
                    products.add ( menFashionProduct );
                } else if (productsArray[6] == "3") {
                    Category electronicCategory = new Category ( Integer.parseInt ( productsArray[6] ), productsArray[5] );
                    Product electronicProduct = new Product ( Integer.parseInt ( productsArray[0] ), productsArray[1], productsArray[2], Double.parseDouble ( productsArray[3] ), Integer.parseInt ( productsArray[4] ), electronicCategory );
                    products.add ( electronicProduct );
                } else if (productsArray[6] == "4") {
                    Category womenAccessoryCategory = new Category ( Integer.parseInt ( productsArray[6] ), productsArray[5] );
                    Product womenAccessoryProduct = new Product ( Integer.parseInt ( productsArray[0] ), productsArray[1], productsArray[2], Double.parseDouble ( productsArray[3] ), Integer.parseInt ( productsArray[4] ), womenAccessoryCategory );
                    products.add ( womenAccessoryProduct );
                } else {
                    Category menAccessoryCategory = new Category ( Integer.parseInt ( productsArray[6] ), productsArray[5] );
                    Product menAccessoryProduct = new Product ( Integer.parseInt ( productsArray[0] ), productsArray[1], productsArray[2], Double.parseDouble ( productsArray[3] ), Integer.parseInt ( productsArray[4] ), menAccessoryCategory );
                    products.add ( menAccessoryProduct );
                }
            }
        }
        return products;
    }

}






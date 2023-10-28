package org.example.utils;

import java.io.File;

public class FileUtil {
    private static File credentialsFile;
    private static File categoriesFile;
    private static File productsFile;
    public static File getCredentialsFile(){
        if(credentialsFile==null)
            credentialsFile=new File ( "src/main/java/org/example/assets/credentials.csv" );
        return credentialsFile;
    }
    public static File getcategoriesFile(){
        if(categoriesFile==null)
            categoriesFile=new File ( "src/main/java/org/example/assets/category.csv" );
        return categoriesFile;
    }
    public static File getProductsFile(){
        if(productsFile==null)
            productsFile=new File ( "src/main/java/org/example/assets/products.csv" );
        return productsFile;
    }
    public static String getFilePath() {
        return "src/main/java/org/example/assets/";
    }
}

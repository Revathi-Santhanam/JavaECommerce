package org.example.controller;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import org.example.controller.implementations.IAdminController;
import org.example.models.Product;
import org.example.utils.AppException;
import org.example.utils.StringUtils;
import org.example.view.AdminPage;
import org.example.view.ProductsPage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.example.utils.AppInput.*;
import static org.example.utils.FileUtil.*;
import static org.example.utils.LoadUtils.getProducts;
import static org.example.utils.Utils.println;

public class AdminController implements IAdminController {
    private final AdminPage adminPage;
    private final ProductsPage productsPage;


    public AdminController ( ) {
        adminPage = new AdminPage ( );
        productsPage = new ProductsPage ( );
    }

    @Override
    public void addProducts ( ) throws AppException {
        String productTitle, description, category, categoryId;
        int stocks, id;
        double price;
        id = enterInt ( StringUtils.ENTER_PRODYCT_ID );
        productTitle = enterString ( StringUtils.ENTER_PRODUCT_TITLE );
        description = enterString ( StringUtils.ENTER_DESCRIPTION );
        price = enterDouble ( StringUtils.ENTER_PRICE );
        stocks = enterInt ( StringUtils.ENTER_STOCK );
        category = enterString ( StringUtils.ENTER_CATEGORY );
        categoryId = enterString ( StringUtils.ENTER_CATEGORY_ID );
        try {
            FileWriter fileWriter = new FileWriter ( getProductsFile ( ), true );
            fileWriter.write ( "\n" );
            fileWriter.write ( id + "," + productTitle + "," + description + "," + price + "," + stocks + "," + category + "," + categoryId );
            fileWriter.flush ( );
            fileWriter.close ( );
            println ( StringUtils.PRODUCT_ADDED );
        } catch (IOException e) {
            throw new RuntimeException ( e );
        }
        printAdminMenu ( );
    }

    @Override
    public void editProducts ( ) throws AppException {
        int id;
        id = enterInt ( StringUtils.ID_TO_EDIT );
        String productName = enterString ( StringUtils.PRODUCT_EDIT );
        String productDesc=enterString ( StringUtils.PRODUCT_DESC );
        double price=enterDouble ( StringUtils.EDIT_PRICE );
        int stocks=enterInt ( StringUtils.EDIT_STOCKS );
        String categoryName=enterString ( StringUtils.EDIT_CATEGORY_NAME );
        int categoryId=enterInt ( StringUtils.EDIT_CATEGORY_ID );

        List<String[]> productArr = new ArrayList<> ( );
        try {
            CSVReader csvFileReader = new CSVReader ( new FileReader ( getProductsFile ( ) ) );
            productArr = csvFileReader.readAll ( );

        } catch (CsvException | FileNotFoundException e) {
            throw new RuntimeException ( e );
        } catch (IOException e) {
            throw new RuntimeException ( e );
        }
        productArr.get ( id-1 )[1]=productName;
        productArr.get ( id-1 )[2]=productDesc;
        productArr.get ( id-1 )[3]= String.valueOf ( price );
        productArr.get ( id-1 )[4]= String.valueOf ( stocks );
        productArr.get ( id-1 )[5]=categoryName;
        productArr.get ( id-1 )[6]= String.valueOf ( categoryId );

        try (CSVWriter writer = new CSVWriter ( new FileWriter ( getProductsFile ( ), false ), CSVWriter.DEFAULT_SEPARATOR,
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.RFC4180_LINE_END )) {
            writer.writeAll ( productArr);
        } catch (IOException e) {
            throw new RuntimeException ( e );
        }
        println ( "----------------Edited Successfully-------------------" );
    }

    @Override
    public void deleteProducts ( ) throws AppException, IOException {

        int id = enterInt ( StringUtils.ID_TO_DELETE );

        List<String[]> productArr = new ArrayList<> ( );
        try {
            CSVReader csvFileReader = new CSVReader ( new FileReader ( getProductsFile ( ) ) );
            productArr = csvFileReader.readAll ( );

        } catch (CsvException e) {
            throw new RuntimeException ( e );
        }
        List<String[]> delProductArr = new ArrayList<> ( );
        for (String[] product : productArr) {
            try {
                int productFromArr = Integer.parseInt ( product[0].trim ( ) );
                if (productFromArr != id) {
                    delProductArr.add ( product );
                }

            } catch (NumberFormatException ex) {
            }
        }
        try (CSVWriter writer = new CSVWriter ( new FileWriter ( getProductsFile ( ), false ), CSVWriter.DEFAULT_SEPARATOR,
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.RFC4180_LINE_END )) {
            writer.writeAll ( delProductArr );
        } catch (IOException e) {
            throw new RuntimeException ( e );
        }

        adminPage.productDeletedMessage ( );
    }

    @Override
    public void viewProducts ( ) {
        try {
            ArrayList<Product> products = getProducts ( );
            productsPage.printProductsMenu ( products );
            int choice = enterInt ( StringUtils.ENTER_CHOICE );
            if (choice == 00) {
                printAdminMenu ( );
            }
        } catch (IOException e) {
            println ( e.getMessage ( ) );
            viewProducts ( );
        } catch (AppException e) {
            throw new RuntimeException ( e );
        }
    }

    @Override
    public void viewUserDetails ( ) throws IOException, InterruptedException {
        String line = "";
        BufferedReader br = new BufferedReader ( new FileReader ( getCredentialsFile ( ) ) );
        while ((line = br.readLine ( )) != null) {
            if (!line.startsWith ( "id" )) {
                String[] userArray = line.split ( "," );
                println ( "User name: " + userArray[1] + "\n" + "User email id: " + userArray[2] + "\n" );
                Thread.sleep ( 500 );
            }
        }
        printAdminMenu ( );
    }

    @Override
    public void viewOrderDetails ( ) throws IOException, InterruptedException {
        String line = "";
        BufferedReader br = new BufferedReader ( new FileReader ( getOrderFile ( ) ) );
        int i = 1;
        println ( "User:" + i + "\n" );
        while ((line = br.readLine ( )) != null) {
            println ( line );
            Thread.sleep ( 500 );
            i++;
        }
        printAdminMenu ( );
    }

    @Override
    public void printAdminMenu ( ) {
        adminPage.printAdminPage ( );
        try {
            int choice = enterInt ( StringUtils.ENTER_CHOICE );
            if (choice == 0) {

            } else if (choice == 1) {
                viewProducts ( );
            } else if (choice == 2) {
                addProducts ( );
            } else if (choice == 3) {
                deleteProducts ( );
                printAdminMenu ( );
            } else if (choice == 4) {
                editProducts ( );
            } else if (choice == 5) {
                viewUserDetails ( );
            } else if (choice == 6) {
                viewOrderDetails ( );
            } else {
                invalidChoice ( new AppException ( StringUtils.INVALID_CHOICE ) );
                printAdminMenu ( );
            }

        } catch (AppException | IOException | InterruptedException appException) {
            throw new RuntimeException ( appException );
        }
    }

    private void invalidChoice (AppException appException) {
        println ( appException.getMessage ( ) );
        printAdminMenu ( );
    }
}

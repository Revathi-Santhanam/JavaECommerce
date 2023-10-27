package org.example.utils;

import java.io.File;

public class FileUtil {
    private static File credentialsFile;
    public static File getCredentialsFile(){
        if(credentialsFile==null)
            credentialsFile=new File ( "src/main/java/org/example/assets/credentials.csv" );
        return credentialsFile;
    }
}

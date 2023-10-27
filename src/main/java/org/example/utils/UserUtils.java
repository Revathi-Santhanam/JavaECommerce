package org.example.utils;

import org.example.models.User;

public class UserUtils {

    public static User loggedInUser;
    public static User getLoggedInUser ( ) {
        return loggedInUser;
    }

    public static void setLoggedInUser (User loggedInUser) {
        UserUtils.loggedInUser = loggedInUser;
    }


}

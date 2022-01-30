package model;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class UserDataGenerator {
    public static User getRandom(){
        final String userEmail = randomAlphabetic(10) + "@yandex.ru";
        final String userPassword = randomAlphabetic(10);
        final String userName = randomAlphabetic(10);
        return new User(userEmail, userPassword, userName);
    }

    public static String getUserEmail(){
        return randomAlphabetic(10) + "@yandex.ru";
    }

    public static String getUserPassword(){
        return randomAlphabetic(10);
    }

    public static String getUserName(){
        return randomAlphabetic(10);
    }
}

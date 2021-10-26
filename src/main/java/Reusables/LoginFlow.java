package Reusables;

import Base.Driver;
import Env_Configuration.Config;
import Env_Configuration.User;
import PageObjects.FrontEndPageObjects.PageObjects;

public class LoginFlow {

    private static String username;
    private static String password;

    public static void LoginUsernameAndPassword(String url, String username, String password, Driver driver) {
        driver.navigateToURL(url);

    }

    public static void Login(User user, String URL, Driver driver) {
        setUserCredentials(user);

        driver.navigateToURL(URL);
        driver.waitForElementVisible(PageObjects.LoginButton());
        driver.enterTextByXpath(PageObjects.LoginUsernameField(), username);
        driver.enterTextByXpath(PageObjects.LoginToBookStoreLandingPageHeader(), password);
    }


    private static void setUserCredentials(User user) {
        Config.ReadPropertiesFile();
        switch (user) {
            case GARRETH:
                username = Config.getPropertiesFile().getProperty("Garreth_Username");
                password = Config.getPropertiesFile().getProperty("Garreth_Password");
                break;
        }
    }

    private static String getUsername() {
        return username;
    }

    private static String getPassword() {
        return password;
    }
}

package helpers;


import models.LoginResponseModel;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static tests.TestData.*;


public class WithLoginExtension implements BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) {
        if (context.getTestMethod()
                .filter(m -> m.isAnnotationPresent(WithLogin.class))
                .isPresent()) {
            loginAndSetCookies();
        }
    }

    private void loginAndSetCookies() {

        LoginResponseModel auth = ApiHelpersLogin.loginRequest();
        userId = auth.getUserId();
        token = auth.getToken();
        expires = auth.getExpires();

        // Установка кук
        open("/favicon.ico");
        getWebDriver().manage().deleteAllCookies();

        getWebDriver().manage().addCookie(new Cookie("userID", userId));
        getWebDriver().manage().addCookie(new Cookie("token", token));
        getWebDriver().manage().addCookie(new Cookie("expires", expires));
    }
}
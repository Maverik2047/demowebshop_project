package demowebshop;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import pages.RegistrationPage;

public class TestBase {
    RegistrationPage registrationPage = new RegistrationPage();
    TestData testData = new TestData();

    @BeforeAll
    static void beforeAll() {

        Configuration.baseUrl = "http://demowebshop.tricentis.com";
        Configuration.browserSize = "1800x900";
        Configuration.holdBrowserOpen = true;
    }
}

package demowebshop;

import config.CredConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;


public class DemoWebShopTests extends TestBase {
    CredConfig config = ConfigFactory.create(CredConfig.class);
    String password = config.password();
    String email = config.email();

    @Test
    @Tag("demowebshop1")
    @DisplayName("User registration UI Page Object tests")
    void userRegistrationTest() {
        registrationPage.openPage()
                .setUserGender()
                .setFirstName(testData.firstName)
                .setUserLastName(testData.lastName)
                .setUserEmail(testData.email)
                .setUserPassword(password)
                .setConfirmUserPassword(password)
                .setUserRegister()
                .checkResult();
    }

    @Test
    @Tag("demowebshop2")
    @DisplayName("Add to shopping cart API test")
    void addToCart() {

        String cookieName = "Nop.customer=2af5756a-b74d-4a31-b86d-95d5b0be322a;";
        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .cookie(cookieName)
                .body("addtocart_31.EnteredQuantity=1")
                .log().all()
                .when()
                .post("http://demowebshop.tricentis.com/addproducttocart/details/31/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"));

    }

    @Test
    @Tag("demowebshop3")
    @DisplayName("Successfully authorized user API+UI test")
    void userAuthTest() {
        String cookieName = "NOPCOMMERCE.AUTH";
        String authCookieValue = given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .formParam("Email", email)
                .formParam("Password", password)
                .log().all()
                .when()
                .post("/login")
                .then()
                .log().all()
                .statusCode(302)
                .extract()
                .cookie(cookieName);

        open("/login");
        Cookie authCookie = new Cookie(cookieName, authCookieValue);
        getWebDriver().manage().addCookie(authCookie);
        open("");
        $(".account").shouldHave(text(email));

    }

    @Test
    @Tag("demowebshop4")
    @DisplayName("Edit user account API+UI lambda step tests")
    void userAccountEditTest() {
        String cookieName = "NOPCOMMERCE.AUTH";
        step("Open registered user account through API", () -> {
            String authCookieValue = given()
                    .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                    .formParam("Email", email)
                    .formParam("Password", password)
                    .log().all()
                    .when()
                    .post("/login")
                    .then()
                    .log().all()
                    .statusCode(302)
                    .extract()
                    .cookie(cookieName);
            step("Open any content to set up cookie", () ->
                    open("/login"));
            step("Set up cookie to browser", () ->
                    getWebDriver().manage().addCookie(
                            new Cookie(cookieName, authCookieValue)));
        });
        step("Open user info page", () ->
                open("/customer/info"));
        step("Edit user first name", () ->
                $("#FirstName").setValue("Alex Malkovich"));
        step("Edit user last name", () ->
                $("#LastName").setValue("Ferher"));
        step("Save details", () ->
                $("[name=save-info-button]").click());
        step("Open user addresses page", () ->
                $(".inactive").click());
        step("Add new address button", () ->
                $(".add-button").click());
        step("Set first name", () ->
                $("#Address_FirstName").setValue("Jason Born"));
        step("Set last name", () ->
                $("#Address_LastName").setValue("Smith"));
        step("Set email", () ->
                $("#Address_Email").setValue(email));
        step("Set country", () ->
                $("#Address_CountryId").$(byText("Canada")).click());
        step("Set city", () ->
                $("#Address_City").setValue("Woodshed"));
        step("Set address", () ->
                $("#Address_Address1").setValue("1 New Line Street"));
        step("Set zip code", () ->
                $("#Address_ZipPostalCode").setValue("567243"));
        step("Set phone number", () ->
                $("#Address_PhoneNumber").setValue("1034568760"));
        step("Save details", () ->
                $(".button-1.save-address-button").click());
        step("Check account info updated", () ->
                $(".page-body").shouldHave(text("Jason Born Vladovich")));

    }


}

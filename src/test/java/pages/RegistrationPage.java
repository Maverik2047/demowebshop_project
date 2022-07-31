package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationPage {

    SelenideElement userGender = $("#gender-male"),
            userFirstName = $("#FirstName"),
            userLastName = $("#LastName"),
            userEmail = $("#Email"),
            userPassword = $("#Password"),
            confirmUserPassword = $("#ConfirmPassword"),
            userRegister = $("#register-button");



    public RegistrationPage openPage() {
        open("/register");
        return this;

    }

    public RegistrationPage setFirstName(String value) {
        userFirstName.setValue(value);
        return this;
    }

    public RegistrationPage setUserGender() {
        userGender.click();
        return this;
    }

    public RegistrationPage setUserLastName(String value) {
        userLastName.setValue(value);
        return this;
    }

    public RegistrationPage setUserEmail(String value) {
        userEmail.setValue(value);
        return this;
    }

    public RegistrationPage setUserPassword(String value) {
        userPassword.setValue(value);
        return this;
    }

    public RegistrationPage setConfirmUserPassword(String value) {
        confirmUserPassword.setValue(value);
        return this;
    }

    public RegistrationPage setUserRegister() {
        userRegister.click();
        return this;
    }

    public void checkResult() {
        $(".result").shouldHave(text("Your registration completed"));

    }
}

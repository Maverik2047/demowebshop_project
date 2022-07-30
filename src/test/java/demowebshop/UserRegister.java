package demowebshop;

import config.CredConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;



public class UserRegister extends TestBase {
    CredConfig config = ConfigFactory.create(CredConfig.class);
    String password = config.password();


    @Test
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

}

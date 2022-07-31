package demowebshop;

import com.github.javafaker.Faker;
import config.CredConfig;
import org.aeonbits.owner.ConfigFactory;

public class TestData {
    CredConfig config = ConfigFactory.create(CredConfig.class);
    String password = config.password();
    String email = config.email();

    Faker faker = new Faker();
    String firstName = faker.funnyName().name(),
            lastName = faker.name().lastName(),
            userEmail = faker.internet().safeEmailAddress();
}

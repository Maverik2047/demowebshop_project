package demowebshop;

import com.github.javafaker.Faker;

public class TestData {
    Faker faker = new Faker();
    String firstName = faker.funnyName().name(),
    lastName = faker.name().lastName(),
    email = faker.internet().safeEmailAddress();
}

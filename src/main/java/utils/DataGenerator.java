package utils;

import com.github.javafaker.Faker;
import models.user.CreateUserRequest;

public class DataGenerator {
    private static final Faker faker = new Faker();

    public static String randomEmail() {
        return System.currentTimeMillis() + faker.internet().emailAddress();
    }

    public static CreateUserRequest generateUser() {

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = randomEmail();

        return new CreateUserRequest(firstName, lastName, email);
    }
}

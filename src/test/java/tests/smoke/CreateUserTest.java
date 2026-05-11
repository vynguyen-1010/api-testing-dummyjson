package tests.smoke;

import core.BaseTest;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import models.user.CreateUserRequest;
import models.user.User;
import retry.RetryAnalyzer;
import org.testng.annotations.Test;
import assertions.user.UserAssertions;
import constants.APIConstants;
import services.user.UserService;
import utils.DataGenerator;

public class CreateUserTest extends BaseTest{
    UserService userService = new UserService();

    @Feature("Smoke Test - Create User")
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testCreateUser() {

        CreateUserRequest request = DataGenerator.generateUser();

        Response response = userService.createUser(request);

        response.then().statusCode(APIConstants.STATUS_CREATED);

        User user = response.as(User.class);
        UserAssertions.verifyUser(user, 209);
    }
}

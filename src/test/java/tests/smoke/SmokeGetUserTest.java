package tests.smoke;

import core.BaseTest;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import models.user.User;
import retry.RetryAnalyzer;
import org.testng.annotations.Test;
import assertions.user.UserAssertions;
import constants.APIConstants;
import services.user.UserService;

public class SmokeGetUserTest extends BaseTest{
    UserService userService = new UserService();

    @Feature("Smoke Test - Get User")
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testGetUser() {
        Response response = userService.getUser(1);

        response.then().statusCode(APIConstants.STATUS_OK);

        User user = response.as(User.class);

        UserAssertions.verifyUser(user, 1);
    }
}

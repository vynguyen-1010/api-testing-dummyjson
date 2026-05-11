package tests.user;

import core.BaseTest;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import models.user.User;
import retry.RetryAnalyzer;
import org.testng.annotations.Test;
import assertions.user.UserAssertions;
import constants.APIConstants;
import services.user.UserService;

import static org.hamcrest.Matchers.equalTo;

public class GetUserTest extends BaseTest{
    UserService userService = new UserService();

    @Feature("Get User")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "smoke")
    public void testGetUser() {
        Response response = userService.getUser(1);

        response.then().statusCode(APIConstants.STATUS_OK);

        User user = response.as(User.class);

        UserAssertions.verifyUser(user, 1);
    }

    @Feature("Get User")
    @Test(groups = "regression")
    public void testGetUser_UserNotFound() {
        Response response = userService.getUser(999999);

        response.then().statusCode(APIConstants.STATUS_NOT_FOUND);
    }

    @Feature("Get User")
    @Test(groups = "regression")
    public void testGetUser_InvalidId() {
        Response response = userService.getUser(-1);

        response.then().statusCode(APIConstants.STATUS_NOT_FOUND);
        response.then().body("message", equalTo("User with id '-1' not found"));
    }
}

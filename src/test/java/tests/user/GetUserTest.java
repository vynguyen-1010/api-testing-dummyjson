package tests.user;

import core.BaseTest;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import models.user.User;
import retry.RetryAnalyzer;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import assertions.user.UserAssertions;
import constants.APIConstants;
import services.user.UserService;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class GetUserTest extends BaseTest{
    UserService userService = new UserService();

    @Feature("Smoke Test - Get User")
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
        response.then().body("message", equalTo("User with id '-1' not found"));
    }

    @DataProvider(name = "invalidUserIds")
    public Object[][] invalidUserIds() {
        return new Object[][]{{-1}, {0}, {999999}};
    }

    @Feature("Get User")
    @Test(groups = "regression", dataProvider = "invalidUserIds")
    public void testGetUser_InvalidIds(int id) {
        Response response = userService.getUser(id);

        response.then().statusCode(APIConstants.STATUS_NOT_FOUND);
        response.then().body("message", equalTo("User with id '" + id + "' not found"));
    }

    @Feature("Get User")
    @Test(groups = "regression")
    public void testGetUser_ResponseTime() {

        Response response = userService.getUser(1);

        response.then().statusCode(APIConstants.STATUS_OK).time(lessThan(3000L));
    }

    @Feature("Get User")
    @Test(groups = "regression")
    public void testGetUser_ContentType() {

        Response response = userService.getUser(1);

        response.then().statusCode(APIConstants.STATUS_OK).contentType("application/json");
    }

    @Feature("Get User")
    @Test(groups = "regression")
    public void testGetUser_ValidEmailFormat() {

        User user = userService.getUser(1).as(User.class);

        Assert.assertTrue(user.getEmail().contains("@"));
    }
}

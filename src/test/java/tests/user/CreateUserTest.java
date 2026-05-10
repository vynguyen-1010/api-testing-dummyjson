package tests.user;

import core.BaseTest;
import io.restassured.response.Response;
import models.user.CreateUserRequest;
import models.user.User;
import retry.RetryAnalyzer;

import org.testng.Assert;
import org.testng.annotations.Test;

import assertions.user.UserAssertions;
import constants.APIConstants;
import services.user.UserService;
import utils.DataGenerator;

public class CreateUserTest extends BaseTest{
    UserService userService = new UserService();

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testCreateUser() {

        CreateUserRequest request = DataGenerator.generateUser();

        Response response = userService.createUser(request);

        response.then().statusCode(APIConstants.STATUS_CREATED);

        User user = response.as(User.class);

        UserAssertions.verifyUser(user, 209);
    }

    @Test
    public void testCreateUser_MissingField() {
        String invalidBoy = "{}";

        Response response = userService.createUser(invalidBoy);

        response.then().statusCode(APIConstants.STATUS_CREATED);

        User user = response.as(User.class);

        Assert.assertEquals(user.getId(), 209);
        Assert.assertEquals(user.getFirstName(), "");
        Assert.assertEquals(user.getLastName(), "");
    }

    @Test
    public void testCreateUser_InvalidEmail() {
        CreateUserRequest request = new CreateUserRequest("Test", "User", "invalid-email");

        Response response = userService.createUser(request);

        response.then().statusCode(APIConstants.STATUS_CREATED);

        User user = response.as(User.class);

        UserAssertions.verifyUser(user, 209);
    }
}

package tests.user;

import core.BaseTest;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import models.user.CreateUserRequest;
import models.user.User;
import retry.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.Test;
import constants.APIConstants;
import services.user.UserService;
import utils.DataGenerator;

public class CreateUserTest extends BaseTest{
    UserService userService = new UserService();

    @Feature("Smoke Test - Create User")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "smoke")
    public void testCreateUser() {

        CreateUserRequest request = DataGenerator.generateUser();

        Response response = userService.createUser(request);

        response.then().statusCode(APIConstants.STATUS_CREATED);

        User user = response.as(User.class);

        Assert.assertTrue(user.getId() > 0);
        Assert.assertEquals(user.getFirstName(), request.getFirstName());
        Assert.assertEquals(user.getLastName(), request.getLastName());
        Assert.assertEquals(user.getEmail(), request.getEmail());
    }

    @Feature("Create User")
    @Test(groups = "regression")
    public void testCreateUser_MissingField() {
        String invalidBoy = "{}";

        Response response = userService.createUser(invalidBoy);

        response.then().statusCode(APIConstants.STATUS_CREATED);

        User user = response.as(User.class);

        Assert.assertTrue(user.getId() > 0);
        Assert.assertEquals(user.getFirstName(), "");
        Assert.assertEquals(user.getLastName(), "");
    }

    @Feature("Create User")
    @Test(groups = "regression")
    public void testCreateUser_InvalidEmail() {
        CreateUserRequest request = new CreateUserRequest("Test", "User", "invalid-email");

        Response response = userService.createUser(request);

        response.then().statusCode(APIConstants.STATUS_CREATED);

        User user = response.as(User.class);

        Assert.assertTrue(user.getId() > 0);
        Assert.assertEquals(user.getFirstName(), request.getFirstName());
        Assert.assertEquals(user.getLastName(), request.getLastName());
        Assert.assertEquals(user.getEmail(), request.getEmail());
    }

    @Feature("Create User")
    @Test(groups = "regression")
    public void testCreateUser_DuplicateEmail() {
        CreateUserRequest request = new CreateUserRequest("Test", "User", "emily.johnson@x.dummyjson.com");

        Response response = userService.createUser(request);

        response.then().statusCode(APIConstants.STATUS_CREATED);

        User user = response.as(User.class);

        Assert.assertTrue(user.getId() > 0);
        Assert.assertEquals(user.getFirstName(), request.getFirstName());
        Assert.assertEquals(user.getLastName(), request.getLastName());
        Assert.assertEquals(user.getEmail(), request.getEmail());
    }

    @Feature("Create User")
    @Test(groups = "regression")
    public void testCreateUser_SpecialCharacters() {
        CreateUserRequest request = new CreateUserRequest("!@#$%^&*", "!@#$%^&*", "!@#$%^&*");

        Response response = userService.createUser(request);

        response.then().statusCode(APIConstants.STATUS_CREATED);

        User user = response.as(User.class);

        Assert.assertTrue(user.getId() > 0);
        Assert.assertEquals(user.getFirstName(), request.getFirstName());
        Assert.assertEquals(user.getLastName(), request.getLastName());
        Assert.assertEquals(user.getEmail(), request.getEmail());
    }

    @Feature("Create User")
    @Test(groups = "regression")
    public void testCreateUser_NullValues() {
        CreateUserRequest request = new CreateUserRequest(null, null, null);

        Response response = userService.createUser(request);

        response.then().statusCode(APIConstants.STATUS_CREATED);

        User user = response.as(User.class);

        Assert.assertTrue(user.getId() > 0);
        Assert.assertEquals(user.getFirstName(), request.getFirstName());
        Assert.assertEquals(user.getLastName(), request.getLastName());
        Assert.assertEquals(user.getEmail(), request.getEmail());
    }
}

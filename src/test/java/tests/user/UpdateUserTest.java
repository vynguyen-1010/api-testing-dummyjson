package tests.user;

import core.BaseTest;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import models.user.CreateUserRequest;
import models.user.User;
import retry.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import constants.APIConstants;
import services.user.UserService;
import utils.DataGenerator;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.lessThan;

public class UpdateUserTest extends BaseTest {
    UserService userService = new UserService();

    @Feature("Smoke Test - Update User")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"smoke", "regression"})
    public void testUpdateUser() {

        CreateUserRequest request = DataGenerator.generateUser();

        Response responseCreatedUser = userService.createUser(request);

        responseCreatedUser.then().statusCode(APIConstants.STATUS_CREATED);

        User createdUser = responseCreatedUser.as(User.class);

        CreateUserRequest updateRequest = new CreateUserRequest("UpdatedFirstName","UpdatedLastName", createdUser.getEmail());

        Response responseUpdatedUser = userService.updateUser(1, updateRequest);

        User updatedUser = responseUpdatedUser.as(User.class);

        responseUpdatedUser.then().statusCode(APIConstants.STATUS_OK);

        Assert.assertEquals(updatedUser.getId(), 1);
        Assert.assertEquals(updatedUser.getFirstName(), updateRequest.getFirstName());
        Assert.assertEquals(updatedUser.getLastName(), updateRequest.getLastName());
        Assert.assertEquals(updatedUser.getEmail(), createdUser.getEmail());
    }

    @Feature("Update User")
    @Test(groups = "regression")
    public void testUpdateUser_MissingField() {
        String invalidBody = "{}";

        Response response = userService.updateUser(1, invalidBody);

        response.then().statusCode(APIConstants.STATUS_OK);

        User user = response.as(User.class);

        Assert.assertEquals(user.getId(), 1);
    }

    @Feature("Update User")
    @Test(groups = "regression")
    public void testUpdateUser_InvalidEmail() {
        CreateUserRequest request = new CreateUserRequest("Updated", "User", "invalid-email");

        Response response = userService.updateUser(1, request);

        response.then().statusCode(APIConstants.STATUS_OK);

        User user = response.as(User.class);

        Assert.assertEquals(user.getId(), 1);
        Assert.assertEquals(user.getEmail(), request.getEmail());
    }

    @DataProvider(name = "invalidUserIds")
    public Object[][] invalidUserIds() {
        return new Object[][]{{-1}, {0}, {999999}};
    }

    @Feature("Update User")
    @Test(groups = "regression", dataProvider = "invalidUserIds")
    public void testUpdateUser_InvalidIds(int id) {

        CreateUserRequest request = new CreateUserRequest("Updated", "User", "updated.user@test.com");

        Response response = userService.updateUser(id, request);

        response.then().statusCode(APIConstants.STATUS_NOT_FOUND);
        response.then().body("message", containsString("not found"));
    }

    @Feature("Update User")
    @Test(groups = "regression")
    public void testUpdateUser_ResponseTime() {

        CreateUserRequest request = new CreateUserRequest("Updated", "User", "updated.user@test.com");

        Response response = userService.updateUser(1, request);

        response.then().statusCode(APIConstants.STATUS_OK).time(lessThan(3000L));
    }

    @Feature("Update User")
    @Test(groups = "regression")
    public void testUpdateUser_SpecialCharacters() {

        CreateUserRequest request = new CreateUserRequest("!@#$%^&*", "!@#$%^&*", "!@#$%^&*");

        Response response = userService.updateUser(1, request);

        response.then().statusCode(APIConstants.STATUS_OK);

        User user = response.as(User.class);

        Assert.assertEquals(user.getId(), 1);
        Assert.assertEquals(user.getFirstName(), request.getFirstName());
        Assert.assertEquals(user.getLastName(), request.getLastName());
        Assert.assertEquals(user.getEmail(), request.getEmail());
    }

    @Feature("Update User")
    @Test(groups = "regression")
    public void testUpdateUser_NullValues() {

        CreateUserRequest request = new CreateUserRequest(null, null, null);

        Response response = userService.updateUser(1, request);

        response.then().statusCode(APIConstants.STATUS_OK);

        User user = response.as(User.class);

        Assert.assertEquals(user.getId(), 1);
        Assert.assertEquals(user.getFirstName(), request.getFirstName());
        Assert.assertEquals(user.getLastName(), request.getLastName());
        Assert.assertEquals(user.getEmail(), request.getEmail());
    }
}

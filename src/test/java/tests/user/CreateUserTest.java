package tests.user;

import core.BaseTest;
import io.restassured.response.Response;
import models.user.CreateUserRequest;
import models.user.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import constants.APIConstants;
import services.user.UserService;

public class CreateUserTest extends BaseTest{
    UserService userService = new UserService();

    @Test
    public void testCreateUser() {

        CreateUserRequest request = new CreateUserRequest(
                "John",
                "Doe",
                "john@example.com"
        );

        Response response = userService.createUser(request);

        response.then().statusCode(APIConstants.STATUS_CREATED);

        User user = response.as(User.class);

        Assert.assertEquals(user.getFirstName(), "John");
    }
}

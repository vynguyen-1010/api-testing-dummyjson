package tests.user;

import core.BaseTest;
import io.restassured.response.Response;
import models.user.User;

import org.testng.Assert;
import org.testng.annotations.Test;

import constants.APIConstants;
import services.user.UserService;

public class GetUserTest extends BaseTest{
    UserService userService = new UserService();

    @Test
    public void testGetUser() {
        Response response = userService.getUser(1);

        response.then().statusCode(APIConstants.STATUS_OK);

        User user = response.as(User.class);

        Assert.assertEquals(user.getId(), 1);
        Assert.assertNotNull(user.getFirstName());
        Assert.assertNotNull(user.getEmail());
    }
}

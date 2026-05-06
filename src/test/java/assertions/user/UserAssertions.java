package assertions.user;

import models.user.User;
import org.testng.Assert;

public class UserAssertions {
    public static void verifyUser(User user, int expectedId) {

        Assert.assertNotNull(user, "User object is null");

        Assert.assertEquals(user.getId(), expectedId, "User ID mismatch");

        Assert.assertNotNull(user.getFirstName(), "First name is null");
        Assert.assertNotNull(user.getEmail(), "Email is null");
    }
}

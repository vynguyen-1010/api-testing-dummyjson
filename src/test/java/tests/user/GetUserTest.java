package tests.user;

import core.BaseTest;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class GetUserTest extends BaseTest{
    @Test
    public void testGetUser() {

        RestAssured.given().when().get("/users/1").then().statusCode(200);
    }

}

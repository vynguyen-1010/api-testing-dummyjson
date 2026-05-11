package core;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    @BeforeClass(alwaysRun = true)
    public void setup() {

        RestAssured.requestSpecification = RequestSpecFactory.getRequestSpec();

        // disable logging sensitive data (upgrade later)
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}

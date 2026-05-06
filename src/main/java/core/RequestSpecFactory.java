package core;

import config.ConfigManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import utils.LoggerUtils;

public class RequestSpecFactory {
    private static RequestSpecification requestSpec;

    public static RequestSpecification getRequestSpec() {

        if (requestSpec == null) {

            RequestSpecBuilder builder = new RequestSpecBuilder()
                .setBaseUri(ConfigManager.getBaseUrl())
                .addHeader("Content-Type", "application/json");

            if (ConfigManager.isLoggingEnabled()) {
                builder.addFilter(LoggerUtils.logRequestAndResponse());
            }

            requestSpec = builder.build();
        }

        return requestSpec;
    }
}

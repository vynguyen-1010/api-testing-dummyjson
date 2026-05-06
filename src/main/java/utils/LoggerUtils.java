package utils;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class LoggerUtils {
    public static Filter logRequestAndResponse() {

        return new Filter() {

            @Override
            public Response filter(
                    FilterableRequestSpecification requestSpec,
                    FilterableResponseSpecification responseSpec,
                    FilterContext ctx) {

                // Log request
                System.out.println("===== REQUEST =====");
                System.out.println("URI: " + requestSpec.getURI());
                System.out.println("Method: " + requestSpec.getMethod());
                System.out.println("Headers: " + requestSpec.getHeaders());

                if (requestSpec.getBody() != null) {
                    System.out.println("Body: " + MaskingUtils.mask(requestSpec.getBody().toString()));
                }

                // Execute request
                Response response = ctx.next(requestSpec, responseSpec);

                // Log response
                System.out.println("===== RESPONSE =====");
                System.out.println("Status: " + response.getStatusCode());
                System.out.println("Body: " + response.getBody().asString());

                return response;
            }
        };
    }
}

package services.user;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UserService {
    public Response getUser(int userId) {
        return RestAssured
                .given()
                .pathParam("id", userId)
                .when()
                .get(UserEndpoints.GET_USER);
    }

    public Response getAllUsers() {
        return RestAssured
                .given()
                .when()
                .get(UserEndpoints.GET_ALL_USERS);
    }

    public Response createUser(Object body) {
        return RestAssured
                .given()
                .body(body)
                .when()
                .post(UserEndpoints.CREATE_USER);
    }

    public Response updateUser(int userId, Object body) {
        return RestAssured
                .given()
                .pathParam("id", userId)
                .body(body)
                .when()
                .put(UserEndpoints.UPDATE_USER);
    }

    public Response patchUser(int userId, Object body) {
        return RestAssured
                .given()
                .pathParam("id", userId)
                .body(body)
                .when()
                .patch(UserEndpoints.PATCH_USER);
    }

    public Response deleteUser(int userId) {
        return RestAssured
                .given()
                .pathParam("id", userId)
                .when()
                .delete(UserEndpoints.DELETE_USER);
    }
}

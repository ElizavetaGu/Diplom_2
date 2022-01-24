package client;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import model.User;

import static io.restassured.RestAssured.given;

public class UserClient extends RestAssuredClient {

    private static final String USER_PATH = "/auth";

    //@Step("Send POST request to " + USER_PATH)
    public ValidatableResponse create(User user){
        return given()
                .spec(getBaseSpec())
                .body(user)
                .when()
                .post(USER_PATH + "/register")
                .then();
    }

    public ValidatableResponse login(User user){
        return given()
                .spec(getBaseSpec())
                .body(user)
                .when()
                .post(USER_PATH + "/login")
                .then();
    }

    public ValidatableResponse update(User user, String accessToken){
        return given()
                .spec(getBaseSpec())
                .auth().oauth2(accessToken)
                .body(user)
                .when()
                .patch(USER_PATH + "/user")
                .then();
    }

    public ValidatableResponse update(User user){
        return given()
                .spec(getBaseSpec())
                .body(user)
                .when()
                .patch(USER_PATH + "/user")
                .then();
    }
}

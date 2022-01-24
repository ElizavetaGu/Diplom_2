package client;

import io.restassured.response.ValidatableResponse;
import model.Order;
import model.User;

import static io.restassured.RestAssured.given;

public class OrderClient extends RestAssuredClient{
    private static final String ORDER_PATH = "/orders";

    public ValidatableResponse create(Order order){
        return given()
                .spec(getBaseSpec())
                .body(order)
                .when()
                .post(ORDER_PATH)
                .then();
    }

    public ValidatableResponse create(Order order, String accessToken){
        return given()
                .spec(getBaseSpec())
                .auth().oauth2(accessToken)
                .body(order)
                .when()
                .post(ORDER_PATH)
                .then();
    }

    public ValidatableResponse get(String accessToken){
        return given()
                .spec(getBaseSpec())
                .auth().oauth2(accessToken)
                .when()
                .get(ORDER_PATH)
                .then();
    }
}

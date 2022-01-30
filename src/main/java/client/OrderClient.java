package client;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import model.Order;

import static io.restassured.RestAssured.given;

public class OrderClient extends RestAssuredClient{
    private static final String ORDER_PATH = "/orders";

    @Step("Send POST request without authorization to " + ORDER_PATH)
    public ValidatableResponse create(Order order){
        return given()
                .spec(getBaseSpec())
                .body(order)
                .when()
                .post(ORDER_PATH)
                .then();
    }

    @Step("Send POST request with authorization to " + ORDER_PATH)
    public ValidatableResponse create(Order order, String accessToken){
        return given()
                .spec(getBaseSpec())
                .auth().oauth2(accessToken)
                .body(order)
                .when()
                .post(ORDER_PATH)
                .then();
    }

    @Step("Send GET request with authorization to " + ORDER_PATH)
    public ValidatableResponse get(String accessToken){
        return given()
                .spec(getBaseSpec())
                .auth().oauth2(accessToken)
                .when()
                .get(ORDER_PATH)
                .then();
    }
}

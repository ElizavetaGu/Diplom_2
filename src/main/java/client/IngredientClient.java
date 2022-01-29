package client;

import io.qameta.allure.Step;
import model.IngredientResponse;

import static io.restassured.RestAssured.given;

public class IngredientClient extends RestAssuredClient {

    @Step("Send GET request to /ingredients")
    public IngredientResponse get() {
        return given()
                .spec(getBaseSpec())
                .when()
                .get("/ingredients")
                .body()
                .as(IngredientResponse.class);
    }
}

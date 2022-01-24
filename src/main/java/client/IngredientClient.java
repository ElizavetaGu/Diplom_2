package client;

import io.restassured.response.ValidatableResponse;
import model.Ingredient;
import model.IngredientResponse;

import static io.restassured.RestAssured.given;

public class IngredientClient extends RestAssuredClient {


    public IngredientResponse get() {
        return given()
                .spec(getBaseSpec())
                .when()
                .get("/ingredients")
                .body()
                .as(IngredientResponse.class);
    }
}

package model;

import client.IngredientClient;

import java.util.List;
import java.util.Random;

public class IngredientGenerator {

    public static Ingredient getRandomIngredient(){
        IngredientClient ingredientClient = new IngredientClient();
        List<Ingredient> ingredients = ingredientClient.get().getData();

        Random rand = new Random();
        Ingredient randomIngredient = ingredients.get(rand.nextInt(ingredients.size()));

        return randomIngredient;
    }
}

package model;

import client.IngredientClient;

import java.util.*;

import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

public class OrderGenerator {

    public static Order generateOrder(){
        Order order = new Order();
        List<String> chosenIngredientsIds = new ArrayList<>();
        //randomly choose min 3  max 10 ingredients
        double numberOfIngredients = 3 + Math.random() * 8;

        for (int i = 0; i <= numberOfIngredients; i++){
            Ingredient ingredient = IngredientGenerator.getRandomIngredient();
            chosenIngredientsIds.add(ingredient.get_id());
        }

        return order.setIngredients(chosenIngredientsIds);
    }
}

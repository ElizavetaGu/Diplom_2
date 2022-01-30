import client.IngredientClient;
import model.Ingredient;
import org.junit.Test;
import java.util.List;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class IngredientTest {

    @Test
    public void getIngredientsTest(){
        IngredientClient ingredientClient = new IngredientClient();

        List<Ingredient> actualIngredients = ingredientClient.get().getData();

        assertThat("No available ingredients", actualIngredients.size(), is(not(0)));
    }
}

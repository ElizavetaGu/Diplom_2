package model;

import java.util.List;

public class IngredientResponse {
    private boolean success;
    private List<Ingredient> data;

    public boolean isSuccess() {
        return success;
    }

    public List<Ingredient> getData() {
        return data;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setData(List<Ingredient> data) {
        this.data = data;
    }
}

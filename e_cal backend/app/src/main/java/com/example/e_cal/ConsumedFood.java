package com.example.e_cal;

import java.io.Serializable;

public class ConsumedFood implements Serializable {
    private String foodName;
    private int calories;

    public ConsumedFood(String foodName, int calories) {
        this.foodName = foodName;
        this.calories = calories;
    }

    public String getFoodName() {
        return foodName;
    }

    public int getCalories() {
        return calories;
    }
}

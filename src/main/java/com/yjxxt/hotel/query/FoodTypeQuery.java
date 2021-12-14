package com.yjxxt.hotel.query;

import com.yjxxt.hotel.base.BaseQuery;

public class FoodTypeQuery extends BaseQuery {
    private String foodTypeName;
    private String foodIntro;

    public FoodTypeQuery() {
    }

    public String getFoodTypeName() {
        return foodTypeName;
    }

    public void setFoodTypeName(String foodTypeName) {
        this.foodTypeName = foodTypeName;
    }

    public String getFoodIntro() {
        return foodIntro;
    }

    public void setFoodIntro(String foodIntro) {
        this.foodIntro = foodIntro;
    }

    @Override
    public String toString() {
        return "FoodTypeQuery{" +
                "foodTypeName='" + foodTypeName + '\'' +
                ", foodIntro='" + foodIntro + '\'' +
                '}';
    }
}

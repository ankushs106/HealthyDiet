
package com.example.healthyme_app;

import android.util.Log;

import com.fatsecret.platform.model.CompactFood;
import com.fatsecret.platform.model.CompactRecipe;
import com.fatsecret.platform.model.Food;
import com.fatsecret.platform.model.Recipe;
import com.fatsecret.platform.services.Response;

/** 
 * Callback listener interface for delivering parsed response
 *
 * @author Saurabh Rane
 * @version 2.0
 */
public class Listener {
	
	/** 
	 * Called when a food response is received.
	 *
	 * @param food			the food item from the response
	 */
	 public void onFoodResponse(Food food) {
		System.out.println("ResponseListener onFoodResponse");
		 //Log.e("","FOOD NAME: " + food.getName());
		 //Log.e("","FOOD NAME: " + food.getServings());

	 }
	
	/**
	 * Called when a compact food list response is received.
	 * 
	 * @param response			the response for the food item list
	 */
	 public void onFoodListRespone(Response<CompactFood> response) {
		System.out.println("ResponseListener onFoodListRespone");
	}

	/** 
	 * Called when a recipe response is received.
	 *
	 * @param recipe			the recipe item from the response
	 */
	 public void onRecipeResponse(Recipe recipe) {
		System.out.println("ResponseListener onRecipeResponse");
		 Log.e("","FOOD NAME: " + recipe.getDescription());

	 }
	
	/** 
	 * Called when a compact recipe list response is received.
	 * 
	 * @param response			the response for the recipe item list 
	 */
	 public void onRecipeListRespone(Response<CompactRecipe> response) {
		System.out.println("ResponseListener onRecipeListRespone");
	}
}
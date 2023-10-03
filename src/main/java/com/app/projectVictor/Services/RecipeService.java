package com.app.projectVictor.Services;

import com.app.projectVictor.Entities.Recipe;
import com.app.projectVictor.Entities.Review;
import com.app.projectVictor.Repositories.RecipeRepository;
import com.app.projectVictor.Repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> findAllRecipes() {
        return recipeRepository.findAll();
    }

    public Optional<Recipe> findRecipeById(int id) {
        return recipeRepository.findById(id);
    }

    public Recipe createRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public Recipe updateRecipe(int id, Recipe updatedRecipe) {
        return recipeRepository.findById(id)
                .map(existingRecipe -> {
                    existingRecipe.setTitle(updatedRecipe.getTitle());
                    existingRecipe.setDescription(updatedRecipe.getDescription());


                    return recipeRepository.save(existingRecipe);
                })
                .orElse(null);
    }
    public void deleteRecipe(int id) {
        recipeRepository.deleteById(id);
    }

    //Partea asta este pentru calcularea mediei....................................

    public List<Recipe> getAllRecipesWithAverageScores() {
        List<Recipe> recipes = recipeRepository.findAll();

        for (Recipe recipe : recipes) {
            double averageScore = calculateAverageScore(recipe);
            recipe.setAverageScore(averageScore);
        }

        return recipes;
    }
    private double calculateAverageScore(Recipe recipe) {
        List<Review> reviews = reviewRepository.findByRecipeId(recipe.getId());

        if (!reviews.isEmpty()) {
            double totalScore = 0.0;
            for (Review review : reviews) {
                totalScore += review.getRating();
            }
            return totalScore / reviews.size();
        } else {

            return 0.0;
        }
    }
 }

    //.............................................................................



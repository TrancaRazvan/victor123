package com.app.projectVictor;

import com.app.projectVictor.Controller.RecipeController;
import com.app.projectVictor.Entities.Recipe;
import com.app.projectVictor.Services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class RecipeControllerTest {

    @InjectMocks
    private RecipeController recipeController;

    @Mock
    private RecipeService recipeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAllRecipes() {
        List<Recipe> sampleRecipes = new ArrayList<>();
        sampleRecipes.add(new Recipe(1, "Recipe1"));
        sampleRecipes.add(new Recipe(2, "Recipe2"));
        when(recipeService.findAllRecipes()).thenReturn(sampleRecipes);

        List<Recipe> result = recipeController.findAllRecipes();

        verify(recipeService, times(1)).findAllRecipes();
        assertEquals(sampleRecipes, result);
    }

    @Test
    public void testFindRecipeById() {
        Recipe sampleRecipe = new Recipe(1, "Recipe1");
        when(recipeService.findRecipeById(1)).thenReturn(Optional.of(sampleRecipe));

        ResponseEntity<Recipe> result = recipeController.findRecipeById(1);

        verify(recipeService, times(1)).findRecipeById(1);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(sampleRecipe, result.getBody());
    }

    @Test
    public void testCreateRecipe() {
        Recipe sampleRecipe = new Recipe(1, "Recipe1");
        when(recipeService.createRecipe(any(Recipe.class))).thenReturn(sampleRecipe);

        ResponseEntity<Recipe> result = recipeController.createRecipe(new Recipe(1, "Recipe1"));

        verify(recipeService, times(1)).createRecipe(any(Recipe.class));
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(sampleRecipe, result.getBody());
    }

    @Test
    public void testUpdateRecipe() {
        Recipe sampleRecipe = new Recipe(1, "Recipe1");
        Recipe updatedRecipe = new Recipe(1, "UpdatedRecipe");
        when(recipeService.updateRecipe(1, updatedRecipe)).thenReturn(sampleRecipe);

        ResponseEntity<Recipe> result = recipeController.updateRecipe(1, updatedRecipe);

        verify(recipeService, times(1)).updateRecipe(1, updatedRecipe);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(sampleRecipe, result.getBody());
    }

    @Test
    public void testDeleteRecipe() {
        Recipe sampleRecipe = new Recipe(1, "Recipe1");
        UserDetails userDetails = new User("1", "password", new ArrayList<>());

        when(recipeService.findRecipeById(1)).thenReturn(Optional.of(sampleRecipe));

        ResponseEntity<Void> result = recipeController.deleteRecipe(1, userDetails);

        verify(recipeService, times(1)).findRecipeById(1);
        verify(recipeService, times(1)).deleteRecipe(1);
        // Assert that the response entity has a NO_CONTENT status
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }

    @Test
    public void testGetAllRecipesWithAverageScores() {
        List<Recipe> sampleRecipes = new ArrayList<>();
        sampleRecipes.add(new Recipe(1, "Recipe1"));
        sampleRecipes.add(new Recipe(2, "Recipe2"));
        when(recipeService.getAllRecipesWithAverageScores()).thenReturn(sampleRecipes);

        ResponseEntity<List<Recipe>> result = recipeController.getAllRecipesWithAverageScores();

        verify(recipeService, times(1)).getAllRecipesWithAverageScores();
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(sampleRecipes, result.getBody());
    }
}

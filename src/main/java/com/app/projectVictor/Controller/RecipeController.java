package com.app.projectVictor.Controller;

import com.app.projectVictor.Entities.Recipe;
import com.app.projectVictor.Repositories.RecipeRepository;
import com.app.projectVictor.Services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping
    public List<Recipe> findAllRecipes() {
        return recipeService.findAllRecipes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> findRecipeById(@PathVariable int id) {
        Optional<Recipe> recipe = recipeService.findRecipeById(id);
        return recipe.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
//Aici nu are rost verificare id-ul pentru ca cine face reteta o sa fie automat propretarul. Cred
    @PostMapping
    public ResponseEntity<Recipe> createRecipe(@RequestBody Recipe recipe) {
        Recipe createdRecipe = recipeService.createRecipe(recipe);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRecipe);
    }

//Aici ar trebui sa dea un ID la reteta ca sa o poata modifica.

    public ResponseEntity<Recipe> updateRecipe(@PathVariable int id, @RequestBody Recipe updatedRecipe) {
        Recipe updated = recipeService.updateRecipe(id, updatedRecipe);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//Aici teoretic ar trebui sa dea un ID la reteta ca sa o poata modifica-ish.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable int id, @AuthenticationPrincipal UserDetails userDetails) {
        Optional<Recipe> existingRecipe = recipeService.findRecipeById(id);
        if (existingRecipe.isPresent()) {
            Recipe recipe = existingRecipe.get();

            if (!userDetails.getUsername().equals(String.valueOf(recipe.getUserId()))) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }

            recipeService.deleteRecipe(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    //Partea asta este pentru calcularea mediei

    @GetMapping("/all-with-average-scores")
    public ResponseEntity<List<Recipe>> getAllRecipesWithAverageScores() {
        List<Recipe> recipes = recipeService.getAllRecipesWithAverageScores();
        return ResponseEntity.ok(recipes);
    }
//...............................................................................
}
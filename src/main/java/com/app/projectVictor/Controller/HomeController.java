package com.app.projectVictor.Controller;

import com.app.projectVictor.Entities.Recipe;
import com.app.projectVictor.Services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "Home";
    }
    @Autowired
    private RecipeService recipeService;

    @GetMapping("/home")
    public String home(Model model) {
        List<Recipe> recipes = recipeService.getAllRecipesWithAverageScores();
        model.addAttribute("recipes", recipes);
        return "Home.html";
    }
}

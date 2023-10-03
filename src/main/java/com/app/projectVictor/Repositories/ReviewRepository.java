package com.app.projectVictor.Repositories;

import com.app.projectVictor.Entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByRecipeId(int recipeId);
    List<Review> findByUserId(int userId);
}
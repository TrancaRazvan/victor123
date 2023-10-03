package com.app.projectVictor.Entities;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    private int rating;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;
}

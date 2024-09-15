/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.RecommendationService;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor

public class WatchList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull

    private long movieId;
    @ManyToMany(mappedBy = "watchLists")
    private List<Movies> moveList = new ArrayList<>();

    public void addMovie(Movies movie) {
        if (!this.moveList.contains(movie)) {

            this.moveList.add(movie);
            System.out.println("Movie added: " + movie.getId() + " " + this.id);
        }

    }

    public Long getWatchListId() {
        return this.id;
    }

    public void removeMovie(Movies movie) {
        if (!this.moveList.contains(movie)) {
            for (Movies m : this.moveList) {
                if (movie.getId().equals(m.getId())) {
                    this.moveList.remove(movie);
                }

            }
            System.out.println("Movie removed: " + movie.getId() + " " + this.id);
        }

    }

}

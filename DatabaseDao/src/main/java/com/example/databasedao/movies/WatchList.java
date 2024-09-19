package com.example.databasedao.movies;

import com.example.databasedao.customer.Customer;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * MovieService.java
 * Author: Anmol Saru Magar
 * Date: 16/09/2024
 * Purpose:
 * Entity class representing a user's watchlist.
 * This class defines the properties and behavior of a watchlist,
 * including its relationship with Movies and Customers.
 */
@Entity
@Data
@NoArgsConstructor
public class WatchList {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany
    @JsonManagedReference
    private List<Movies> movies = new ArrayList<>();

    public void addMovie(Movies movie) {
        // Check to avoid adding duplicates
        if (!this.movies.contains(movie)) {
            this.movies.add(movie);
            movie.getWatchLists().add(this); // Maintain the bidirectional relationship
        }
    }

    @OneToOne
    @JsonManagedReference
    private Customer customer;

    public void removeMovie(Movies movie) {
        if (!this.movies.contains(movie)) {
            for(Movies m : this.movies) {
                if(movie.getId().equals( m.getId())) {
                    this.movies.remove(movie);
                }

            }
            System.out.println("Movie removed: " + movie.getId() + " " + this.id);
        }

    }
    @Override
    public String toString() {
        return "WatchList{" +
                "id=" + id +
                ", moviesCount=" + (movies != null ? movies.size() : 0) +
                '}';
    }
}
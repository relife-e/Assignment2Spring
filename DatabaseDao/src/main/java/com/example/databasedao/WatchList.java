package com.example.databasedao;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public String toString() {
        return "WatchList{" +
                "id=" + id +
                ", moviesCount=" + (movies != null ? movies.size() : 0) +
                '}';
    }
}
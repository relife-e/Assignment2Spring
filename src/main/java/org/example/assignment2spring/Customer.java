package org.example.assignment2spring;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Customer {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String name;

    private boolean blocked = false;  // Renamed field to 'blocked'


    @ManyToMany
    private List<Movies> movies = new ArrayList<>();

    public void addMovie(Movies movie) {


        this.movies.add(movie);
        if(!this.movies.contains(movie)){
            movie.addCustomer(this);

        }
    }

    public void blockUser() {
        this.blocked = true;
    }

    public void unblockUser() {
        this.blocked = false;
    }
}

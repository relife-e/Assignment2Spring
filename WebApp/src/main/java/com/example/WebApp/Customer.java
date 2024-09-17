/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.WebApp;

/**
 *
 * @author caleb
 */
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NonNull
    private String email;

    @NonNull
    private String password; //should hash this

    @NonNull
    private String name;

    private boolean blocked = false;  // Renamed field to 'blocked'

    @ManyToMany
    private List<Movie> movies = new ArrayList<>();

    public void addMovie(Movie movie) {

        this.movies.add(movie);
        if (!this.movies.contains(movie)) {
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

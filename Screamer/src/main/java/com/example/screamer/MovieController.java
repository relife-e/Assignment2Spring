package com.example.screamer;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController @RequestMapping("/movies")
public class MovieController {
    @Autowired
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Movies>> getAllMovies() {
        List<Movies> movies = movieService.getAllMovies();
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/get/watchlist/all")
    public ResponseEntity<WatchList> getAllWatchlistMovies() {
        WatchList movies = movieService.getAllWatchListMovies();
        System.out.println("Debug: " + movies);
        return ResponseEntity.ok(movies);
    }



    @PostMapping("/add/watchlist/{id}")
    public ResponseEntity<WatchList> addMovieToWatchlist(@PathVariable("id") Long movieId, @RequestParam String action) {

        System.out.println(action);
        WatchList watchList = new WatchList();
        if ("Add".equals(action)) {
            watchList =  movieService.addToWatchList(movieId);
        } else if ("Remove".equals(action)) {
            watchList =     movieService.removeFromWatchList(movieId);
        }
        return ResponseEntity.ok(watchList);
    }

}


package com.example.screamerwebapp;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 *
 * @author Anmol Saru Magar
 * File Name: MovieClient.java
 * Date :16/9/2024
 * Purpose :
 * MovieClient that takes request and sends that request to it assigned url
 * ******************************************************
 */
@FeignClient(name = "movie", url = "http://localhost:8003/movies")
public interface MovieClient {

    @GetMapping("/get/all")
    List<Movies> getAllMovies();

    @GetMapping("/get/watchlist/all")
    WatchList getAllWatchListMovies(@RequestParam("custId") Long custId);

    @PutMapping("/update/{id}")
    Movies updateMovieById(@PathVariable("id") Long id, @RequestBody Movies movie);

    @PostMapping(value = "/add/watchlist/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    void addMovieToWatchList(@PathVariable("id") Long id, @RequestParam("action") String action, @RequestBody Long customerId);

}

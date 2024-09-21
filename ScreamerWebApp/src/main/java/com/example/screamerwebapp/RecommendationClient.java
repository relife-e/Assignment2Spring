package com.example.screamerwebapp;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
/**
 *
 * @author Anmol Saru Magar & Caleb
 * File Name: RecommendationClient.java
 * Date :16/9/2024
 * Purpose :
 * RecommendationClient interface that connects with recommendation client and gets requested data
 * ******************************************************
 */
@FeignClient(name = "recommendationService", contextId = "recommendationClient")
public interface RecommendationClient {

    @GetMapping("/get")
    ResponseEntity<Movies> getRecomendation();
}

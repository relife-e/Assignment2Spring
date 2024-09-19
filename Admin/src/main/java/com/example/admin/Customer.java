package com.example.admin;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    private String email;

    private String password;

    private String roll;
    private String genre;
    @NonNull
    private boolean blocked;

    @OneToOne(mappedBy = "customer")
    
    private WatchList watchList;

}

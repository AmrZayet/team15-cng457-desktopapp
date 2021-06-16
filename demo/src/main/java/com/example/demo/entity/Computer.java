package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "computerID")
public class Computer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int computerID;
    private String brand;
    private String model;
    private float screenSize;
    private String screenResolution;
    private String processor;
    private int memory;
    private float storageCapacity;
    private float price;


    @OneToMany(targetEntity = Review.class, cascade = CascadeType.ALL)
    private List<Review> reviews;

    @OneToMany(mappedBy= "computer", cascade= CascadeType.ALL)
    private List<ComputerFeature> computerFeatures;


    public void addReview(Review r) {
        reviews.add(r);
    }
}

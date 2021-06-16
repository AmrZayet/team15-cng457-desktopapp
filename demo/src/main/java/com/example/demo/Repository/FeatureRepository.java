package com.example.demo.Repository;

import com.example.demo.entity.Feature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeatureRepository extends JpaRepository<Feature, Integer> {
    public List<Feature> findByfeatureNameContains(String featureName);
}

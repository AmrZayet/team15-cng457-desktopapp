//FeatureService

package com.example.demo.Service;

import com.example.demo.Repository.FeatureRepository;
import com.example.demo.entity.Feature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeatureService {
    @Autowired
    FeatureRepository featureRepository;

    public Feature saveFeature(Feature f) {
        return featureRepository.save(f);
    }

    public List<Feature> saveFeatures(List<Feature> features) {
        return featureRepository.saveAll(features);
    }

    public Feature getFeature(int id) {
        return featureRepository.findById(id).orElse(null);
    }

    public List<Feature> getFeatures() {
        return featureRepository.findAll();
    }

    public String deleteFeature(int featureID) {
        featureRepository.deleteById(featureID);
        return String.format("Feature %d is deleted",featureID);
    }

    public Feature getFeatureByfeatureName(String name) {
        List<Feature> featureList = featureRepository.findByfeatureNameContains(name);
        if(featureList.size() == 0) {
            return null;
        }
        return featureList.get(0);
    }

    public List<Feature> getFeatureWithNameContains(String name) {
        return featureRepository.findByfeatureNameContains(name);
    }

}

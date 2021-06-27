package com.example.demo.Controller;

import com.example.demo.Service.FeatureService;
import com.example.demo.entity.Feature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FeatureController {
    @Autowired
    FeatureService featureService;

    @PostMapping("/addFeature")
    public Feature addFeature(@RequestBody Feature f) {
        return featureService.saveFeature(f);
    }

    @PostMapping("/addFeatures")
    public List<Feature> addFeatures(@RequestBody List<Feature> features) {
        return featureService.saveFeatures(features);
    }

    @GetMapping("getFeature/{id}")
    public Feature getFeature(@PathVariable int id) {
        return featureService.getFeature(id);
    }

    @GetMapping("getFeatures")
    public List<Feature> getFeatures() {
        return featureService.getFeatures();
    }

    @DeleteMapping("/deleteFeature/{id}")
    public String deleteFeature(@PathVariable int id) {
        return featureService.deleteFeature(id);
    }

    @GetMapping("/getFeature/featureName={name}")
    public Feature getFeatureByfeatureName(@PathVariable String name) {
        return featureService.getFeatureByfeatureName(name);
    }
}

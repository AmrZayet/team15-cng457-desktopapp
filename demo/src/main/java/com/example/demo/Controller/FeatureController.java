package com.example.demo.Controller;

import com.example.demo.Service.FeatureService;
import com.example.demo.entity.Feature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/***
 * @author of this class is Mert Özçelik and Amr Zayet
 * This is Feature Controller class for the web service.
 * @version PV
 */
@RestController
public class FeatureController {
    @Autowired
    FeatureService featureService;

    /**
     * This method allows us to add a Feature
     * @param f is a Feature entity that is ready to be added to Databank
     * @return the result coming from Feature Service
     */
    @PostMapping("/addFeature")
    public Feature addFeature(@RequestBody Feature f) {
        return featureService.saveFeature(f);
    }

    /**
     * This method allows us to add more than one Features
     * @param features is a list of features that are ready to be added to Databank
     * @return the result coming from Feature Service
     */
    @PostMapping("/addFeatures")
    public List<Feature> addFeatures(@RequestBody List<Feature> features) {
        return featureService.saveFeatures(features);
    }

    /**
     * This method allows to get a spesific a feature
     * @param id is an identifier to get Feature
     * @return the result coming from Feature Service
     */
    @GetMapping("getFeature/{id}")
    public Feature getFeature(@PathVariable int id) {
        return featureService.getFeature(id);
    }

    /**
     * This method allows to get a spesific feature
     * @return the result coming from Feature Service
     */
    @GetMapping("getFeatures")
    public List<Feature> getFeatures() {
        return featureService.getFeatures();
    }

    /**
     * This method allow us to delete a spesific feature
     * @param id is an identifier to delete Feature
     * @return the result coming from Feature Service
     */
    @DeleteMapping("/deleteFeature/{id}")
    public String deleteFeature(@PathVariable int id) {
        return featureService.deleteFeature(id);
    }

    /**
     * This method allow us to get Feature by name
     * @param name is an identier variable
     * @return the result coming from Feature Service
     *
     */
    @GetMapping("/getFeature/featureName={name}")
    public Feature getFeatureByfeatureName(@PathVariable String name) {
        return featureService.getFeatureByfeatureName(name);
    }
}

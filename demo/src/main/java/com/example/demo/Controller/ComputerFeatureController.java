package com.example.demo.Controller;

import com.example.demo.Service.ComputerFeatureService;
import com.example.demo.entity.ComputerFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ComputerFeatureController {
    @Autowired
    ComputerFeatureService computerFeatureService;

    @PostMapping("/addComputerFeature")
    public ComputerFeature addComputerFeature(@RequestBody ComputerFeature cf) {
        return computerFeatureService.saveComputerFeature(cf);
    }

    @PostMapping("/addComputerFeature/com={computerId}+feat={featureId}")
    public ComputerFeature addComputerFeature(@PathVariable int computerId, @PathVariable int featureId) {
        return computerFeatureService.saveComputerFeature(computerId, featureId);
    }

    @PostMapping("/addComputerFeatures")
    public List<ComputerFeature> addComputerFeatures(@RequestBody List<ComputerFeature> computerFeatures) {
        return computerFeatureService.saveComputerFeatures(computerFeatures);
    }

    @GetMapping("/getComputerFeature/com={computerId}+feat={featureId}")
    public ComputerFeature getComputerFeature(@PathVariable int computerId, @PathVariable int featureId) {
        return computerFeatureService.getComputerFeature(computerId, featureId);
    }

    @GetMapping("/getComputerFeatures")
    public List<ComputerFeature> getComputerFeatures() {
        return computerFeatureService.getComputerFeatures();
    }

    @DeleteMapping("/deleteComputerFeature/com={computerId}+feat={featureId}")
    public String deleteComputer(@PathVariable int computerId, @PathVariable int featureId) {
        return computerFeatureService.deleteComputerFeature(computerId, featureId);
    }
}

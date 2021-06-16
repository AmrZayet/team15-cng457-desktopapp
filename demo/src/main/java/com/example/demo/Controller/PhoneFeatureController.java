package com.example.demo.Controller;

import com.example.demo.Service.PhoneFeatureService;
import com.example.demo.entity.PhoneFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PhoneFeatureController {
    @Autowired
    PhoneFeatureService phoneFeatureService;

    @PostMapping("/addPhoneFeature")
    public PhoneFeature addPhoneFeature(@RequestBody PhoneFeature pf) {
        return phoneFeatureService.savePhoneFeature(pf);
    }

    @PostMapping("/addPhoneFeature/com={phoneId}+feat={featureId}")
    public PhoneFeature addPhoneFeature(@PathVariable int phoneId, @PathVariable int featureId) {
        return phoneFeatureService.savePhoneFeature(phoneId, featureId);
    }

    @PostMapping("/addPhoneFeatures")
    public List<PhoneFeature> addPhoneFeatures(@RequestBody List<PhoneFeature> phoneFeatures) {
        return phoneFeatureService.savePhoneFeatures(phoneFeatures);
    }

    @GetMapping("/getPhoneFeature/com={phoneId}+feat={featureId}")
    public PhoneFeature getPhoneFeature(@PathVariable int phoneId, @PathVariable int featureId) {
        return phoneFeatureService.getPhoneFeature(phoneId, featureId);
    }

    @GetMapping("/getPhoneFeatures")
    public List<PhoneFeature> getPhoneFeatures() {
        return phoneFeatureService.getPhoneFeatures();
    }

    @DeleteMapping("/deletePhoneFeature/com={phoneId}+feat={featureId}")
    public String deletePhone(@PathVariable int phoneId, @PathVariable int featureId) {
        return phoneFeatureService.deletePhoneFeature(phoneId, featureId);
    }
}

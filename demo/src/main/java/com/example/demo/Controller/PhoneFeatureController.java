package com.example.demo.Controller;

import com.example.demo.Service.PhoneFeatureService;
import com.example.demo.entity.PhoneFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/***
 * @author of this class is Mert Özçelik and Amr Zayet
 * This is Phone Feature Controller class for the web service.
 * @version PV
 */
@RestController
public class PhoneFeatureController {
    @Autowired
    PhoneFeatureService phoneFeatureService;

    /**
     *This method is allow us to add a phone feature
     * @param pf is a variable from PhoneFeature entity that ready to be sent into the Databank.
     * @return the result coming from Phone Feature Service
     */
    @PostMapping("/addPhoneFeature")
    public PhoneFeature addPhoneFeature(@RequestBody PhoneFeature pf) {
        return phoneFeatureService.savePhoneFeature(pf);
    }

    /**
     * This method allows us to add Phone Feature to a Phone
     * @param phoneId is an identifer for Phones which we will add Feature
     * @param featureId is the Id of a feature that we will add
     * @return the result coming from Phone Feature Service
     */
    @PostMapping("/addPhoneFeature/com={phoneId}+feat={featureId}")
    public PhoneFeature addPhoneFeature(@PathVariable int phoneId, @PathVariable int featureId) {
        return phoneFeatureService.savePhoneFeature(phoneId, featureId);
    }

    /**
     * This method allows us to add more than one Phone Features
     * @param phoneFeatures is a list of Phone Features
     * @return the result coming from Phone Feature Service
     */
    @PostMapping("/addPhoneFeatures")
    public List<PhoneFeature> addPhoneFeatures(@RequestBody List<PhoneFeature> phoneFeatures) {
        return phoneFeatureService.savePhoneFeatures(phoneFeatures);
    }

    /**
     * This method allow us to get a Phone Feature
     * @param phoneId is the identifier for Phone
     * @param featureId is the idientifier for spesific Feature
     * @return the result coming from Phone Feature Service
     */
    @GetMapping("/getPhoneFeature/com={phoneId}+feat={featureId}")
    public PhoneFeature getPhoneFeature(@PathVariable int phoneId, @PathVariable int featureId) {
        return phoneFeatureService.getPhoneFeature(phoneId, featureId);
    }

    /**
     * This method allows us to get more than one Phone Feature
     * @return the result coming from Phone Feature Service
     */
    @GetMapping("/getPhoneFeatures")
    public List<PhoneFeature> getPhoneFeatures() {
        return phoneFeatureService.getPhoneFeatures();
    }

    /**
     * This method allows us to delete a feature in a spesific phone
     * @param phoneId is an identifer for phone
     * @param featureId is an identifier for the type of the future that we want to delete
     * @return  the result coming from Phone Feature Service
     */
    @DeleteMapping("/deletePhoneFeature/com={phoneId}+feat={featureId}")
    public String deletePhone(@PathVariable int phoneId, @PathVariable int featureId) {
        return phoneFeatureService.deletePhoneFeature(phoneId, featureId);
    }
}

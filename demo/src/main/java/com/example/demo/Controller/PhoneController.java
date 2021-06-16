package com.example.demo.Controller;

import com.example.demo.Service.PhoneFeatureService;
import com.example.demo.Service.PhoneService;
import com.example.demo.Service.FeatureService;
import com.example.demo.entity.Phone;
import com.example.demo.entity.Feature;
import com.example.demo.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class PhoneController {

    @Autowired
    PhoneService phoneService;

    @Autowired
    FeatureService featureService;

    @Autowired
    PhoneFeatureService phoneFeatureService;

    @PostMapping("addPhone")
    public Phone addPhone(@RequestBody Phone c) {
        c.setPhoneFeatures(null);
        return phoneService.savePhone(c);
    }

    @PostMapping("/addPhones")
    public List<Phone> addFeatures(@RequestBody List<Phone> phones) {
        return phoneService.savePhones(phones);
    }

    @PostMapping("/addPhoneReview/{id}")
    public Phone addPhoneReview(@PathVariable int id, @RequestBody Review r) {
        return phoneService.addReview(id, r);
    }


    @GetMapping("/updatePhone/{id}/feature={name}")
    public Phone updatePhoneFeature(@PathVariable int id, @PathVariable String name) {
        Phone phone = phoneService.getPhone(id);
        Feature feature = featureService.getFeatureByfeatureName(name); // get the first one (it should be one
        int featureId;
        if (feature == null) {
            feature = new Feature();
            feature.setFeatureName(name);
            featureService.saveFeature(feature);
            featureId = featureService.getFeatureByfeatureName(name).getFeatureID();
        }
        else {
            featureId = feature.getFeatureID();
        }
        phoneFeatureService.savePhoneFeature(id, featureId);
        return phoneService.getPhone(id);
    }

    @GetMapping("/getPhone/{id}")
    public Phone getPhone(@PathVariable int id) {
        return phoneService.getPhone(id);
    }

    @GetMapping("/getPhone/brand={brand}")
    public List<Phone> getPhoneByBrand(@PathVariable String brand) {
        return phoneService.getPhoneByBrand(brand);
    }

    @GetMapping("/getPhones")
    public List<Phone> getPhones() {
        return phoneService.getPhones();
    }


    @GetMapping("/searchPhones/{searchType}/{criteria}")
    public List<Phone> SearchPhones(@PathVariable String searchType, @PathVariable String criteria) {
        if (criteria.equals("")) {
            return null;
        }

        List<Phone> phoneList;
        List<Phone> searchResults = null;

        if (searchType.equals("base")) {
            phoneList = phoneService.getPhones();
            searchResults = phoneService.filterPhonesBase(phoneList, criteria);
        }
        else if (searchType.equals("add")) {
            searchResults = phoneService.searchPhonesAdditional(criteria);
        }
        else if (searchType.equals("both")) {
            String [] itemsArray = criteria.split("\\s*_\\s*");
            List<String> searchCriterias = Arrays.asList(itemsArray);

            String additionalCriteria = searchCriterias.get(0);
            String baseCriteria = searchCriterias.get(1);

            phoneList = phoneService.searchPhonesAdditional(additionalCriteria);
            searchResults = phoneService.filterPhonesBase(phoneList, baseCriteria);
        }

        return searchResults;
    }

    @DeleteMapping("/deletePhone/{id}")
    public String deletePhone(@PathVariable int id) {
        return phoneService.deletePhone(id);
    }

}

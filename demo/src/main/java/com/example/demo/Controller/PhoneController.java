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
/***
 * @author of this class is Mert Özçelik and Amr Zayet
 * This is Phone Controller class for the web service.
 * @version PV
 */
@RestController
public class PhoneController {

    @Autowired
    PhoneService phoneService;

    @Autowired
    FeatureService featureService;

    @Autowired
    PhoneFeatureService phoneFeatureService;

    /**
     *This method is allow us to add a phone
     * @param c is a variable from Phone entity that ready to be sent into the Databank.
     * @return the result coming from Phone Service
     */
    @PostMapping("addPhone")
    public Phone addPhone(@RequestBody Phone c) {
        c.setPhoneFeatures(null);
        return phoneService.savePhone(c);
    }

    /**
     * This method allows us to add more than one phones
     * @param phones is an ArrayList that is ready to be sent into Databank
     * @return the result coming from Phones Service
     */
    @PostMapping("/addPhones")
    public List<Phone> addFeatures(@RequestBody List<Phone> phones) {
        return phoneService.savePhones(phones);
    }

    /**
     *This method allows us to add review to a phones
     * @param id is a specifier to let the program understand in which Phones object we will add Review
     * @param r is a variable from Review Entity that ready to be sent into the Databank.
     * @return the result coming from Phones Service
     */

    @PostMapping("/addPhoneReview/{id}")
    public Phone addPhoneReview(@PathVariable int id, @RequestBody Review r) {
        return phoneService.addReview(id, r);
    }


    /**
     *  This controller method allow us to update a specified phones feature
     *  if name contains spaces it should be represented as %20
     * @param id is an Id of the phones which will be updated
     * @param name is for the feature will be set
     * @return the result coming from Phones Service
     */
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

    /**
     * This method allows us to get information regarding a phones pecified by an ID
     * @param id is identifier to get a Phones Details
     * @return the result coming from Phones Service
     */
    @GetMapping("/getPhone/{id}")
    public Phone getPhone(@PathVariable int id) {
        return phoneService.getPhone(id);
    }

    /**
     * This method allows us to get phones in a brand
     * @param brand is a brand String , it works as an identifier here
     * @return the result coming from Phones Service
     */
    @GetMapping("/getPhone/brand={brand}")
    public List<Phone> getPhoneByBrand(@PathVariable String brand) {
        return phoneService.getPhoneByBrand(brand);
    }

    /**
     * This method allow us to get more than one phones details
     * @return the result coming from Phones Service
     */
    @GetMapping("/getPhones")
    public List<Phone> getPhones() {
        return phoneService.getPhones();
    }

    /**
     * This method allows us to searh phones with different criterias
     * Example-> /getPhoness/search/brand=Apple&screenSize=eq=17.0
     * @param searchType represent three search types in the program (base,additional,Both)
     * @param criteria represents the criterias that we will use for search
     * @return the search results
     */
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
            String [] itemsArray = criteria.split("\\s*__\\s*");
            List<String> searchCriterias = Arrays.asList(itemsArray);

            String additionalCriteria = searchCriterias.get(0);
            String baseCriteria = searchCriterias.get(1);

            phoneList = phoneService.searchPhonesAdditional(additionalCriteria);
            searchResults = phoneService.filterPhonesBase(phoneList, baseCriteria);
        }

        return searchResults;
    }

    /**
     * This method allows us to delete a spesific phones     * @param id is the identifier for Phones
     * @return the result coming from Phones Service
     */
    @DeleteMapping("/deletePhone/{id}")
    public String deletePhone(@PathVariable int id) {
        return phoneService.deletePhone(id);
    }

}

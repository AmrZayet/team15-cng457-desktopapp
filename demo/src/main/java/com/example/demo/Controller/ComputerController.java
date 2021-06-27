package com.example.demo.Controller;

import com.example.demo.Service.ComputerFeatureService;
import com.example.demo.Service.ComputerService;
import com.example.demo.Service.FeatureService;
import com.example.demo.entity.Computer;
import com.example.demo.entity.Feature;
import com.example.demo.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/***
 * @author of this class is Mert Özçelik and Amr Zayet
 * This is Computer Controller class for the web service.
 * @version PV
 */
@RestController
public class ComputerController {

    @Autowired
    ComputerService computerService;

    @Autowired
    FeatureService featureService;

    @Autowired
    ComputerFeatureService computerFeatureService;

    /**
     *This method is allow us to add a computer
     * @param c is a variable from Computer entity that ready to be sent into the Databank.
     * @return the result coming from Computer Service
     */
    @PostMapping("addComputer")
    public Computer addComputer(@RequestBody Computer c) {
        c.setComputerFeatures(null);
        return computerService.saveComputer(c);
    }

    /**
     * This method allows us to add more than one computer
     * @param computers is an ArrayList that is ready to be sent into Databank
     * @return the result coming from Computer Service
     */
    @PostMapping("/addComputers")
    public List<Computer> addFeatures(@RequestBody List<Computer> computers) {
        return computerService.saveComputers(computers);
    }

    /**
     *This method allows us to add review to a computer
     * @param id is a specifier to let the program understand in which Computer object we will add Review
     * @param r is a variable from Review Entity that ready to be sent into the Databank.
     * @return the result coming from Computer Service
     */
    @PostMapping("/addComputerReview/{id}")
    public Computer addComputerReview(@PathVariable int id, @RequestBody Review r) {
        return computerService.addReview(id, r);
    }





    /**
     *  This controller method allow us to update a specified computer's feature
     *  if name contains spaces it should be represented as %20
     * @param id is an Id of the computer which will be updated
     * @param name is for the feature will be set
     * @return the result coming from Computer Service
     */
    @GetMapping("/updateComputer/{id}/feature={name}")
    public Computer updateComputerFeature(@PathVariable int id, @PathVariable String name) {
        Computer computer = computerService.getComputer(id);
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
        computerFeatureService.saveComputerFeature(id, featureId);
        return computerService.getComputer(id);
    }

    /**
     * This method allows us to get information regarding a computer specified by an ID
     * @param id is identifier to get a Computer Details
     * @return the result coming from Computer Service
     */
    @GetMapping("/getComputer/{id}")
    public Computer getComputer(@PathVariable int id) {
        return computerService.getComputer(id);
    }

    /**
     * This method allows us to get computers in a brand
     * @param brand is a brand String , it works as an identifier here
     * @return the result coming from Computer Service
     */
    @GetMapping("/getComputer/brand={brand}")
    public List<Computer> getComputerByBrand(@PathVariable String brand) {
        return computerService.getComputerByBrand(brand);
    }

    /**
     * This method allow us to get more than one computer details
     * @return the result coming from Computer Service
     */
    @GetMapping("/getComputers")
    public List<Computer> getComputers() {
        return computerService.getComputers();
    }


    /**
     * This method allows us to searh computers with different criterias
     * Example-> /getComputers/search/brand=Apple&screenSize=eq=17.0
     * @param searchType represent three search types in the program (base,additional,Both)
     * @param criteria represents the criterias that we will use for search
     * @return the search results
     */
    @GetMapping("/searchComputers/{searchType}/{criteria}")
    public List<Computer> SearchComputers(@PathVariable String searchType, @PathVariable String criteria) {
        if (criteria.equals("")) {
            return null;
        }

        List<Computer> computerList;
        List<Computer> searchResults = null;

        if (searchType.equals("base")) {
            computerList = computerService.getComputers();
            searchResults = computerService.filterComputersBase(computerList, criteria);
        }
        else if (searchType.equals("add")) {
            searchResults = computerService.searchComputersAdditional(criteria);
        }
        else if (searchType.equals("both")) {
            String [] itemsArray = criteria.split("\\s*__\\s*");
            List<String> searchCriterias = Arrays.asList(itemsArray);

            String additionalCriteria = searchCriterias.get(0);
            String baseCriteria = searchCriterias.get(1);

            computerList = computerService.searchComputersAdditional(additionalCriteria);
            searchResults = computerService.filterComputersBase(computerList, baseCriteria);
        }

        return searchResults;
    }

    /**
     * This method allows us to delete a spesific computer
     * @param id is the identifier for Computer
     * @return the result coming from Computer Service
     */
    @DeleteMapping("/deleteComputer/{id}")
    public String deleteComputer(@PathVariable int id) {
        return computerService.deleteComputer(id);
    }

}

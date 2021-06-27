package com.example.demo.Controller;

import com.example.demo.Service.ComputerFeatureService;
import com.example.demo.entity.ComputerFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/***
 * @author of this class is Mert Özçelik and Amr Zayet
 * This is Computer Feature Controller class for the web service.
 * @version PV
 */

@RestController
public class ComputerFeatureController {
    @Autowired
    ComputerFeatureService computerFeatureService;

    /**
     *This method is allow us to add a computer feature
     * @param cf is a variable from ComputerFeature entity that ready to be sent into the Databank.
     * @return the result coming from Computer Feature Service
     */
    @PostMapping("/addComputerFeature")
    public ComputerFeature addComputerFeature(@RequestBody ComputerFeature cf) {
        return computerFeatureService.saveComputerFeature(cf);
    }

    /**
     * This method allows us to add Computer Feature to a Computer
     * @param computerId is an identifer for Computers which we will add Feature
     * @param featureId is the Id of a feature that we will add
     * @return the result coming from Computer Feature Service
     */
    @PostMapping("/addComputerFeature/com={computerId}+feat={featureId}")
    public ComputerFeature addComputerFeature(@PathVariable int computerId, @PathVariable int featureId) {
        return computerFeatureService.saveComputerFeature(computerId, featureId);
    }

    /**
     * This method allows us to add more than one Computer Features
     * @param computerFeatures is a list of Computer Features
     * @return the result coming from Computer Feature Service
     */
    @PostMapping("/addComputerFeatures")
    public List<ComputerFeature> addComputerFeatures(@RequestBody List<ComputerFeature> computerFeatures) {
        return computerFeatureService.saveComputerFeatures(computerFeatures);
    }

    /**
     * This method allow us to get a Computer Feature
     * @param computerId is the identifier for Computer
     * @param featureId is the idientifier for spesific Feature
     * @return the result coming from Computer Feature Service
     */
    @GetMapping("/getComputerFeature/com={computerId}+feat={featureId}")
    public ComputerFeature getComputerFeature(@PathVariable int computerId, @PathVariable int featureId) {
        return computerFeatureService.getComputerFeature(computerId, featureId);
    }

    /**
     * This method allows us to get more than one Computer Feature
     * @return the result coming from Computer Feature Service
     */
    @GetMapping("/getComputerFeatures")
    public List<ComputerFeature> getComputerFeatures() {
        return computerFeatureService.getComputerFeatures();
    }

    /**
     * This method allows us to delete a feature in a spesific computer
     * @param computerId is an identifer for computer
     * @param featureId is an identifier for the type of the future that we want to delete
     * @return  the result coming from Computer Feature Service
     */
    @DeleteMapping("/deleteComputerFeature/com={computerId}+feat={featureId}")
    public String deleteComputer(@PathVariable int computerId, @PathVariable int featureId) {
        return computerFeatureService.deleteComputerFeature(computerId, featureId);
    }
}

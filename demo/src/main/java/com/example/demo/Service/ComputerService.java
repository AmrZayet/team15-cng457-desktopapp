package com.example.demo.Service;

import com.example.demo.Repository.ComputerRepository;
import com.example.demo.entity.Computer;
import com.example.demo.entity.ComputerFeature;
import com.example.demo.entity.Feature;
import com.example.demo.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ComputerService {
    @Autowired
    ComputerRepository computerRepository;
    @Autowired
    FeatureService featureService;
    @Autowired
    ComputerFeatureService computerFeatureService;

    public Computer saveComputer(Computer c) {
        return computerRepository.save(c);
    }

    public List<Computer> saveComputers(List<Computer> computers) {
        return computerRepository.saveAll(computers);
    }

    public Computer getComputer(int id) {
        return computerRepository.findById(id).orElse(null);
    }

    public List<Computer> getComputerByBrand(String brand) {
//        return courseRepository.getCourseByName(name);
//        return courseRepository.findBycourseNameContains(name);
        return computerRepository.findBybrandContains(brand);
    }

    public List<Computer> getComputers() {
        return computerRepository.findAll();
    }

    public String deleteComputer(int computerID) {
        computerRepository.deleteById(computerID);
        return String.format("Computer %d is deleted", computerID);
    }

    public Computer addReview(int id, Review r) {
        Computer tmp = computerRepository.findById(id).orElse(null);
        if (tmp == null) {
            return null;
        }
        tmp.addReview(r);
        return computerRepository.save(tmp);
    }

    public List<Computer> filterComputersBase(List<Computer> computers, String criteria) {
        if (criteria.equals("")) {
            return null;
        }

        Stream<Computer> computerStream = computers.stream();

        String [] itemsArray = criteria.split("\\s*&\\s*");
        List<String> searchCriterias = Arrays.asList(itemsArray);


        for(String sc: searchCriterias) {
            String [] arr = sc.split("\\s*=\\s*");
            List<String> oneCrieteria = Arrays.asList(arr);
//            System.out.println("\n\n");
//            for(String part: oneCrieteria) {
//                System.out.print(String.format("%s  -->", part));
//            }
//            System.out.println("\n\n");
            String searchAttribute = oneCrieteria.get(0);
            String AttributeDetails = oneCrieteria.get(1);


            if(searchAttribute.equals("brand")) {
                computerStream = computerStream.filter(comp -> comp.getBrand().contains(AttributeDetails));
            }
            else if(searchAttribute.equals("model")) {
                computerStream = computerStream.filter(comp -> comp.getModel().contains(AttributeDetails));
            }
            else if(searchAttribute.equals("screenResolution")) {
                computerStream = computerStream.filter(comp -> comp.getScreenResolution().contains(AttributeDetails));
            }
            else if(searchAttribute.equals("processor")) {
                computerStream = computerStream.filter(comp -> comp.getProcessor().contains(AttributeDetails));
            }
            else if(searchAttribute.equals("computerID")) {
                computerStream = computerStream.filter(comp -> comp.getComputerID() == Integer.parseInt(AttributeDetails));
            }
            else if(searchAttribute.equals("screenSize")) {
                float tmpScreenSize = Float.parseFloat(oneCrieteria.get(2));

                if (AttributeDetails.equals("eq")) {
                    computerStream = computerStream.filter(comp -> comp.getScreenSize() == tmpScreenSize);
                }
                else if (AttributeDetails.equals("ne")) {
                    computerStream = computerStream.filter(comp -> comp.getScreenSize() != tmpScreenSize);
                }
                else if (AttributeDetails.equals("lt")) {
                    computerStream = computerStream.filter(comp -> comp.getScreenSize() < tmpScreenSize);
                }
                else if (AttributeDetails.equals("le")) {
                    computerStream = computerStream.filter(comp -> comp.getScreenSize() <= tmpScreenSize);
                }
                else if (AttributeDetails.equals("gt")) {
                    computerStream = computerStream.filter(comp -> comp.getScreenSize() > tmpScreenSize);
                }
                else if (AttributeDetails.equals("ge")) {
                    computerStream = computerStream.filter(comp -> comp.getScreenSize() >= tmpScreenSize);
                }
                else if (AttributeDetails.equals("bt")) {
                    computerStream = computerStream.filter(comp -> (comp.getScreenSize() >= tmpScreenSize && comp.getScreenSize() <= Float.parseFloat(oneCrieteria.get(3))));
                }
                else {
                    return null;
                }
            }
            else if(searchAttribute.equals("memory")) {
                int tmpMemory = Integer.parseInt(oneCrieteria.get(2));

                if (AttributeDetails.equals("eq")) {
                    computerStream = computerStream.filter(comp -> comp.getMemory() == tmpMemory);
                }
                else if (AttributeDetails.equals("ne")) {
                    computerStream = computerStream.filter(comp -> comp.getMemory() != tmpMemory);
                }
                else if (AttributeDetails.equals("lt")) {
                    computerStream = computerStream.filter(comp -> comp.getMemory() < tmpMemory);
                }
                else if (AttributeDetails.equals("le")) {
                    computerStream = computerStream.filter(comp -> comp.getMemory() <= tmpMemory);
                }
                else if (AttributeDetails.equals("gt")) {
                    computerStream = computerStream.filter(comp -> comp.getMemory() > tmpMemory);
                }
                else if (AttributeDetails.equals("ge")) {
                    computerStream = computerStream.filter(comp -> comp.getMemory() >= tmpMemory);
                }
                else if (AttributeDetails.equals("bt")) {
                    computerStream = computerStream.filter(comp -> (comp.getMemory() >= tmpMemory && comp.getMemory() <= Integer.parseInt(oneCrieteria.get(3))));
                }
                else {
                    return null;
                }
            }
            else if(searchAttribute.equals("storageCapacity")) {
                float tmpStorageCapacity = Float.parseFloat(oneCrieteria.get(2));

                if (AttributeDetails.equals("eq")) {
                    computerStream = computerStream.filter(comp -> comp.getStorageCapacity() == tmpStorageCapacity);
                }
                else if (AttributeDetails.equals("ne")) {
                    computerStream = computerStream.filter(comp -> comp.getStorageCapacity() != tmpStorageCapacity);
                }
                else if (AttributeDetails.equals("lt")) {
                    computerStream = computerStream.filter(comp -> comp.getStorageCapacity() < tmpStorageCapacity);
                }
                else if (AttributeDetails.equals("le")) {
                    computerStream = computerStream.filter(comp -> comp.getStorageCapacity() <= tmpStorageCapacity);
                }
                else if (AttributeDetails.equals("gt")) {
                    computerStream = computerStream.filter(comp -> comp.getStorageCapacity() > tmpStorageCapacity);
                }
                else if (AttributeDetails.equals("ge")) {
                    computerStream = computerStream.filter(comp -> comp.getStorageCapacity() >= tmpStorageCapacity);
                }
                else if (AttributeDetails.equals("bt")) {
                    computerStream = computerStream.filter(comp -> (comp.getStorageCapacity() >= tmpStorageCapacity && comp.getStorageCapacity() <= Float.parseFloat(oneCrieteria.get(3))));
                }
                else {
                    return null;
                }
            }
            else if(searchAttribute.equals("price")) {
                float tmpPrice = Float.parseFloat(oneCrieteria.get(2));

                if (AttributeDetails.equals("eq")) {
                    computerStream = computerStream.filter(comp -> comp.getPrice() == tmpPrice);
                }
                else if (AttributeDetails.equals("ne")) {
                    computerStream = computerStream.filter(comp -> comp.getPrice() != tmpPrice);
                }
                else if (AttributeDetails.equals("lt")) {
                    computerStream = computerStream.filter(comp -> comp.getPrice() < tmpPrice);
                }
                else if (AttributeDetails.equals("le")) {
                    computerStream = computerStream.filter(comp -> comp.getPrice() <= tmpPrice);
                }
                else if (AttributeDetails.equals("gt")) {
                    computerStream = computerStream.filter(comp -> comp.getPrice() > tmpPrice);
                }
                else if (AttributeDetails.equals("ge")) {
                    computerStream = computerStream.filter(comp -> comp.getPrice() >= tmpPrice);
                }
                else if (AttributeDetails.equals("bt")) {
                    computerStream = computerStream.filter(comp -> (comp.getPrice() >= tmpPrice && comp.getPrice() <= Float.parseFloat(oneCrieteria.get(3))));
                }
                else {
                    return null;
                }
            }

//            System.out.println(String.format("\n\n\n %s  --> %s\n\n\n", searchAttribute, AttributeDetails));
        }

        List<Computer> searchResults = computerStream.collect(Collectors.toList());

        return searchResults;
    }

    public List<Computer> searchComputersAdditional(String featureNamePart) {
//        return computerRepository.getSearchAddtional("new");
//        "upper(feature.feature_name) like upper(\"%new%\") or upper(feature.feature_name) like upper(\"%nother%\")"
        List<Feature> featureList = featureService.getFeatureWithNameContains(featureNamePart);
        System.out.println(String.format("\n\nfeature list count = %d\n\n", featureList.size()));

        List<Integer> featureIds = featureList.stream().map(Feature::getFeatureID).collect(Collectors.toList());
        System.out.println(String.format("\n\nfeature ids list count = %d\n\n", featureIds.size()));

        List<ComputerFeature> computerFeatureList = computerFeatureService.getComputerFeatures();
        System.out.println(String.format("\n\n computer feature list count = %d\n\n", computerFeatureList.size()));

        List<Integer> computerIds = computerFeatureList.stream().filter(feat -> featureIds.contains(feat.getId().getFeatureID())).map(ComputerFeature::getIdComputerId).collect(Collectors.toList());
        System.out.println(String.format("\n\n computer list count = %d\n\n", computerIds.size()));

        List<Computer> computerList = getComputers();
        System.out.println(String.format("\n\n computer list count = %d\n\n", computerList.size()));


        List<Computer> searchResult = computerList.stream().filter(com -> computerIds.contains(com.getComputerID())).collect(Collectors.toList());
        System.out.println(String.format("\n\n search list count = %d\n\n", searchResult.size()));
        return searchResult;
    }
}

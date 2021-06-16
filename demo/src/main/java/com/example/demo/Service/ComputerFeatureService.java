package com.example.demo.Service;

import com.example.demo.Repository.ComputerFeatureRepository;
import com.example.demo.entity.Computer;
import com.example.demo.entity.ComputerFeature;
import com.example.demo.entity.ComputerFeatureID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComputerFeatureService {
    @Autowired
    ComputerFeatureRepository computerFeatureRepository;

    public ComputerFeature saveComputerFeature(ComputerFeature cf) {
        return computerFeatureRepository.save(cf);
    }

    public ComputerFeature saveComputerFeature(int computerId, int featureId) {
        ComputerFeature tmp = new ComputerFeature();
        tmp.setId(new ComputerFeatureID(computerId, featureId));
        return computerFeatureRepository.save(tmp);
    }

    public List<ComputerFeature> saveComputerFeatures(List<ComputerFeature> computerFeatures) {
        return computerFeatureRepository.saveAll(computerFeatures);
    }

    public ComputerFeature getComputerFeature(int computerId, int featureId) {
        return computerFeatureRepository.findById(new ComputerFeatureID(computerId, featureId)).orElse(null);
    }

    public List<ComputerFeature> getComputerFeatures() {
        return computerFeatureRepository.findAll();
    }

    // a problem
    public String deleteComputerFeature(int computerId, int featureId) {
        computerFeatureRepository.deleteById(new ComputerFeatureID(computerId, featureId));
        return String.format("ComputerFeature (com=%d, feat=%d) is deleted", computerId, featureId);
    }
}

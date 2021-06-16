package com.example.demo.Service;

import com.example.demo.Repository.PhoneFeatureRepository;
import com.example.demo.entity.Phone;
import com.example.demo.entity.PhoneFeature;
import com.example.demo.entity.PhoneFeatureID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneFeatureService {
    @Autowired
    PhoneFeatureRepository phoneFeatureRepository;

    public PhoneFeature savePhoneFeature(PhoneFeature cf) {
        return phoneFeatureRepository.save(cf);
    }

    public PhoneFeature savePhoneFeature(int phoneId, int featureId) {
        PhoneFeature tmp = new PhoneFeature();
        tmp.setId(new PhoneFeatureID(phoneId, featureId));
        return phoneFeatureRepository.save(tmp);
    }

    public List<PhoneFeature> savePhoneFeatures(List<PhoneFeature> phoneFeatures) {
        return phoneFeatureRepository.saveAll(phoneFeatures);
    }

    public PhoneFeature getPhoneFeature(int phoneId, int featureId) {
        return phoneFeatureRepository.findById(new PhoneFeatureID(phoneId, featureId)).orElse(null);
    }

    public List<PhoneFeature> getPhoneFeatures() {
        return phoneFeatureRepository.findAll();
    }

    // a problem
    public String deletePhoneFeature(int phoneId, int featureId) {
        phoneFeatureRepository.deleteById(new PhoneFeatureID(phoneId, featureId));
        return String.format("PhoneFeature (com=%d, feat=%d) is deleted", phoneId, featureId);
    }
}

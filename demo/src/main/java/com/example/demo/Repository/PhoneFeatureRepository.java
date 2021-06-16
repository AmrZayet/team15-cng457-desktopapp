package com.example.demo.Repository;

import com.example.demo.entity.PhoneFeature;
import com.example.demo.entity.PhoneFeatureID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneFeatureRepository extends JpaRepository<PhoneFeature, PhoneFeatureID> {
}

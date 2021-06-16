package com.example.demo.Repository;

import com.example.demo.entity.ComputerFeature;
import com.example.demo.entity.ComputerFeatureID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComputerFeatureRepository extends JpaRepository<ComputerFeature, ComputerFeatureID> {
}

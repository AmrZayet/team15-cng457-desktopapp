package com.example.demo.Repository;

import com.example.demo.entity.Computer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComputerRepository extends JpaRepository<Computer, Integer> {
    public List<Computer> findBybrandContains(String brand);

//    @Query(value = "select computer.* FROM computer INNER JOIN computer_feature ON computer.computerid = computer_feature.computerid INNER JOIN feature ON computer_feature.featureid = feature.featureid WHERE upper(feature.feature_name) like upper(\"%new%\") or upper(feature.feature_name) like upper(\"%nother%\")", nativeQuery = true)
//    public List<Computer> getSearchAddtional();

//    @Query(value = "select computer.* FROM computer INNER JOIN computer_feature ON computer.computerid = computer_feature.computerid INNER JOIN feature ON computer_feature.featureid = feature.featureid WHERE upper(feature.feature_name) like upper(\"%?1%\")", nativeQuery = true)
//    public List<Computer> getSearchAddtional(String feature_name);
}

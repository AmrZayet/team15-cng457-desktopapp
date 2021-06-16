package com.example.demo.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ComputerFeature {

    @EmbeddedId
    ComputerFeatureID id;

    @ManyToOne
    @JoinColumn(name = "computerID", insertable = false, updatable = false)
    private Computer computer;

    @ManyToOne@JoinColumn(name = "featureID", insertable = false, updatable = false)
    private Feature feature;

    public int getIdComputerId() {
        return id.getComputerID();
    }

    public int getIdFeatureId() {
        return id.getFeatureID();
    }



}

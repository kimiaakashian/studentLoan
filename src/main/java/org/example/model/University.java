package org.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.base.entity.BaseEntity;
import org.example.model.enums.UniversityType;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class University extends BaseEntity<Long> {
    private String name;
    @Enumerated(EnumType.STRING)
    private UniversityType universityType;
    @ManyToOne
    private City city;


}

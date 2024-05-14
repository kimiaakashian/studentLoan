package org.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.base.entity.BaseEntity;
import org.example.model.enums.CityType;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class City extends BaseEntity<Long> {
    private String cityName;
    @Enumerated(EnumType.STRING)
    private CityType cityType;



}

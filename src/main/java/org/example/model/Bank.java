package org.example.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.base.entity.BaseEntity;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Bank extends BaseEntity<Long> {
    private String name;



}

package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.base.entity.BaseEntity;
import org.example.model.enums.CityType;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoanLimit extends BaseEntity<Long> {
    @ManyToOne
    private LoanType loanType;
    @Enumerated(EnumType.STRING)
    private CityType cityType;
    @ManyToOne
    private EducationGrade educationGrade;
    private Long loanAmount;
}

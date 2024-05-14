package org.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.base.entity.BaseEntity;
import org.example.model.enums.LoanStatus;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoanType  extends BaseEntity<Long> {
    private String loanName;
    private int year;
    @Enumerated(EnumType.STRING)
    private LoanStatus loanStatus;



}

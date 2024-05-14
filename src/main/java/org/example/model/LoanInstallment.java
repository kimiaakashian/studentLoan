package org.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.base.entity.BaseEntity;
import org.example.model.enums.InstallmentStatus;

import java.time.LocalDate;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoanInstallment extends BaseEntity<Long> {
    @ManyToOne
    private LoanRequest loanRequest;
    private int month;
    private Double installmentAmount;
    private String installmentDate;
    private Double payAmount;
    private String payDate;
    @Enumerated(EnumType.STRING)
    private InstallmentStatus installmentStatus;

}

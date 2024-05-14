package org.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.example.base.entity.BaseEntity;

import java.time.LocalDate;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoanRequest extends BaseEntity<Long> {
    @ManyToOne
    private Student student;
    @ManyToOne
    @NotNull
    private LoanType loanType;
    @NotNull
    private Double loanAmount;
    private String loanDate;

}

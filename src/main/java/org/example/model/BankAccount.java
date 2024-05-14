package org.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.base.entity.BaseEntity;

import java.util.Date;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BankAccount extends BaseEntity<Long> {
    @NotNull
    private String accountNumber;
    @NotNull
    private String cvv2;
    @NotNull
    private String expireDate;
    @ManyToOne
    private Bank bank;



}

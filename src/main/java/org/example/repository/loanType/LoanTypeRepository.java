package org.example.repository.loanType;

import org.example.base.repository.BaseRepository;
import org.example.model.LoanType;

import java.util.List;

public interface LoanTypeRepository extends BaseRepository<LoanType,Long> {
    List<LoanType> findAllLoanTypes();
}

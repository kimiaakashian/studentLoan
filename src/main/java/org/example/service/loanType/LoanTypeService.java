package org.example.service.loanType;

import org.example.base.service.BaseService;
import org.example.model.LoanType;

import java.util.List;

public interface LoanTypeService extends BaseService<LoanType,Long> {
    List<LoanType> findAllLoanTypes();

}

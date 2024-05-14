package org.example.service.loanRequest;

import org.example.base.service.BaseService;
import org.example.model.LoanRequest;

import java.util.List;

public interface LoanRequestService extends BaseService<LoanRequest,Long> {
     String saveLoanWithInstallments(LoanRequest loanRequest);
     List<LoanRequest> findAllLoanRequestForStudent(Long studentId);
}

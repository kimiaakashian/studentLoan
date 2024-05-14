package org.example.service.loanInstallment;

import org.example.base.service.BaseService;
import org.example.model.LoanInstallment;

import java.util.List;

public interface LoanInstallmentService extends BaseService<LoanInstallment,Long> {
    LoanInstallment findFirstLoanInstallment(Long loanRequestId);
    List<LoanInstallment> findPaidInstallment(Long loanRequestId);
    List<LoanInstallment>findUnPaidInstallment(Long loanRequestId);
}

package org.example.repository.loanInstallment;

import org.example.base.repository.BaseRepository;
import org.example.model.LoanInstallment;

import java.util.List;

public interface LoanInstallmentRepository extends BaseRepository<LoanInstallment, Long> {

    LoanInstallment findFirstLoanInstallment(Long loanRequestId);

    List<LoanInstallment> findPaidInstallment(Long loanRequestId);

    List<LoanInstallment> findUnPaidInstallment(Long loanRequestId);
}

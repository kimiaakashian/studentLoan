package org.example.repository.loanRequest;

import org.example.base.repository.BaseRepository;
import org.example.model.LoanRequest;

import java.util.List;

public interface LoanRequestRepository extends BaseRepository<LoanRequest, Long> {
    List<LoanRequest> findAllLoanRequestForStudent(Long studentId);
    LoanRequest findHousingLoanRequestForStudent(Long studentId);
    LoanRequest findTuitionLoanRequestForStudent(Long studentId);
    LoanRequest findEducationLoanRequestForStudent(Long studentId);

}

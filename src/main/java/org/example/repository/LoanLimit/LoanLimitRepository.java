package org.example.repository.LoanLimit;

import org.example.base.repository.BaseRepository;
import org.example.model.LoanLimit;
import org.example.model.enums.CityType;

public interface LoanLimitRepository extends BaseRepository<LoanLimit, Long> {
    LoanLimit findByLoanTypeAndCityType(long loanTypeId, CityType cityType);

    LoanLimit findByLoanTypeAndEducationGrade(long loanTypeId, long educationGrade);
}

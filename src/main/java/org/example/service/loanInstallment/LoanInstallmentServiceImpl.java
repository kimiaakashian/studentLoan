package org.example.service.loanInstallment;

import org.example.base.service.BaseServiceImpl;
import org.example.model.LoanInstallment;
import org.example.repository.loanInstallment.LoanInstallmentRepository;
import org.hibernate.SessionFactory;

import java.util.List;

public class LoanInstallmentServiceImpl extends BaseServiceImpl<LoanInstallment,Long, LoanInstallmentRepository> implements LoanInstallmentService {
    public LoanInstallmentServiceImpl(LoanInstallmentRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }

    @Override
    public LoanInstallment findFirstLoanInstallment(Long loanRequestId) {
        return repository.findFirstLoanInstallment(loanRequestId);
    }
    @Override

    public List<LoanInstallment> findPaidInstallment(Long loanRequestId){
        return repository.findPaidInstallment(loanRequestId);

    }
    @Override

    public List<LoanInstallment>findUnPaidInstallment(Long loanRequestId){
        return repository.findUnPaidInstallment(loanRequestId);


    }
}

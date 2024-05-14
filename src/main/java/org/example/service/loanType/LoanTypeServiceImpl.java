package org.example.service.loanType;

import org.example.base.service.BaseServiceImpl;
import org.example.model.LoanType;
import org.example.repository.loanType.LoanTypeRepository;
import org.hibernate.SessionFactory;

import java.util.List;

public class LoanTypeServiceImpl extends BaseServiceImpl<LoanType,Long, LoanTypeRepository> implements LoanTypeService{
    public LoanTypeServiceImpl(LoanTypeRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }

    @Override
    public List<LoanType> findAllLoanTypes() {
        return repository.findAllLoanTypes();
    }
}

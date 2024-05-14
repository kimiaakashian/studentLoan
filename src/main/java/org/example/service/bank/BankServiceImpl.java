package org.example.service.bank;

import org.example.base.service.BaseServiceImpl;
import org.example.model.Bank;
import org.example.repository.bank.BankRepository;
import org.hibernate.SessionFactory;

import java.util.List;

public class BankServiceImpl extends BaseServiceImpl<Bank, Long, BankRepository> implements BankService {

    public BankServiceImpl(BankRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }

    @Override
    public List<Bank> findAllBanks() {
        return repository.findAllBanks();
    }
}

package org.example.service.bankAccount;

import org.example.base.service.BaseServiceImpl;
import org.example.model.BankAccount;
import org.example.model.date.DateConvertorNew;
import org.example.repository.bankAccount.BankAccountRepository;
import org.hibernate.SessionFactory;

public class BankAccountServiceImpl extends BaseServiceImpl<BankAccount, Long, BankAccountRepository> implements BankAccountService {

    public BankAccountServiceImpl(BankAccountRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }

    @Override
    public BankAccount findByAccountNumber(String accountNumber) {
        return repository.findByAccountNumber(accountNumber);
    }

    @Override
    public boolean isValidExpirationDate(String expireDate) {
        String[] todayDateArr = DateConvertorNew.todayDate().split("/");
        String todayDate = todayDateArr[0] + "/" + todayDateArr[1];
        return expireDate.compareTo(todayDate) >= 0;
    }
}

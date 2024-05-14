package org.example.repository.bankAccount;

import org.example.base.repository.BaseRepositoryImpl;
import org.example.model.BankAccount;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class BankAccountRepositoryImpl extends BaseRepositoryImpl<BankAccount, Long> implements BankAccountRepository {
    public BankAccountRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<BankAccount> getEntityClass() {
        return BankAccount.class;
    }

    public BankAccount findByAccountNumber(String accountNumber) {
        BankAccount bankAccount = null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            bankAccount = session.createQuery("FROM BankAccount b WHERE b.accountNumber = :accountNumber", BankAccount.class)
                    .setParameter("accountNumber", accountNumber)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bankAccount;
    }
}

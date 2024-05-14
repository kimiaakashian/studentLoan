package org.example.repository.bank;

import org.example.base.repository.BaseRepositoryImpl;
import org.example.model.Bank;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class BankRepositoryImpl extends BaseRepositoryImpl<Bank, Long> implements BankRepository {
    public BankRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<Bank> getEntityClass() {
        return Bank.class;
    }

    @Override
    public List<Bank> findAllBanks() {

        ArrayList<Bank> banks = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            banks = (ArrayList<Bank>) session.createQuery(
                            "FROM Bank b ", Bank.class)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return banks;
    }
}

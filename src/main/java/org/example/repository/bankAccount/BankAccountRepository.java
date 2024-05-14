package org.example.repository.bankAccount;

import org.example.base.repository.BaseRepository;
import org.example.model.BankAccount;

public interface BankAccountRepository extends BaseRepository<BankAccount, Long> {
    BankAccount findByAccountNumber(String accountNumber);
}

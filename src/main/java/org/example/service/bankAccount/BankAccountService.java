package org.example.service.bankAccount;

import org.example.base.service.BaseService;
import org.example.model.BankAccount;

public interface BankAccountService extends BaseService<BankAccount,Long> {
    BankAccount findByAccountNumber(String accountNumber);
    boolean isValidExpirationDate(String expireDate);
}

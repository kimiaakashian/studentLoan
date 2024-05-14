package org.example.service.bank;

import org.example.base.service.BaseService;
import org.example.model.Bank;

import java.util.List;

public interface BankService extends BaseService<Bank,Long> {
    List<Bank> findAllBanks();
}

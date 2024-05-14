package org.example.repository.bank;

import org.example.base.repository.BaseRepository;
import org.example.model.Bank;

import java.util.List;

public interface BankRepository extends BaseRepository<Bank, Long> {
    List<Bank> findAllBanks();

}

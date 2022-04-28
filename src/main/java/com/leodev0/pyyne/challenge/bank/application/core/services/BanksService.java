package com.leodev0.pyyne.challenge.bank.application.core.services;

import com.leodev0.pyyne.challenge.bank.application.core.domain.Balance;
import com.leodev0.pyyne.challenge.bank.application.core.domain.Transaction;
import com.leodev0.pyyne.challenge.bank.application.ports.in.BanksServicePort;
import com.leodev0.pyyne.challenge.bank.application.ports.out.BankAccountsOperations;

import java.util.Date;
import java.util.List;

public class BanksService implements BanksServicePort {

    private final BankAccountsOperations bankAccountsOperations;

    public BanksService(BankAccountsOperations bankAccountsOperations) {
        this.bankAccountsOperations = bankAccountsOperations;
    }

    @Override
    public List<Balance> getAllBalances(Long id) {
        return bankAccountsOperations.getBalances(id);
    }

    @Override
    public List<Transaction> getAllTransactions(Long id, Date fromDate, Date toDate) {
        return bankAccountsOperations.getTransactions(id, fromDate, toDate);
    }
}

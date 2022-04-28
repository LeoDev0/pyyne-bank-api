package com.leodev0.pyyne.challenge.bank.application.ports.out;

import com.leodev0.pyyne.challenge.bank.application.core.domain.Balance;
import com.leodev0.pyyne.challenge.bank.application.core.domain.Transaction;

import java.util.Date;
import java.util.List;

public interface BankAccountsOperations {
    List<Balance> getBalances(Long id);
    List<Transaction> getTransactions(Long id, Date fromDate, Date toDate);
}

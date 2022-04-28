package com.leodev0.pyyne.challenge.bank.application.ports.in;

import com.leodev0.pyyne.challenge.bank.application.core.domain.Balance;
import com.leodev0.pyyne.challenge.bank.application.core.domain.Transaction;

import java.util.Date;
import java.util.List;

public interface BanksServicePort {
    List<Balance> getAllBalances(Long id);
    List<Transaction> getAllTransactions(Long id, Date fromDate, Date toDate);
}

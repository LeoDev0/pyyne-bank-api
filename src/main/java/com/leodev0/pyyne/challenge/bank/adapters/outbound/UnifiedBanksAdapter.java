package com.leodev0.pyyne.challenge.bank.adapters.outbound;

import com.leodev0.pyyne.bank1.integration.Bank1AccountSource;
import com.leodev0.pyyne.bank1.integration.Bank1Transaction;
import com.leodev0.pyyne.bank2.integration.Bank2AccountBalance;
import com.leodev0.pyyne.bank2.integration.Bank2AccountSource;
import com.leodev0.pyyne.bank2.integration.Bank2AccountTransaction;
import com.leodev0.pyyne.challenge.bank.application.core.domain.Balance;
import com.leodev0.pyyne.challenge.bank.application.core.domain.Transaction;
import com.leodev0.pyyne.challenge.bank.application.core.domain.enums.CardType;
import com.leodev0.pyyne.challenge.bank.application.ports.out.BankAccountsOperations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class UnifiedBanksAdapter implements BankAccountsOperations {

    private final Bank1AccountSource bank1AccountSource;
    private final Bank2AccountSource bank2AccountSource;

    public UnifiedBanksAdapter(Bank1AccountSource bank1AccountSource, Bank2AccountSource bank2AccountSource) {
        this.bank1AccountSource = bank1AccountSource;
        this.bank2AccountSource = bank2AccountSource;
    }

    @Override
    public List<Balance> getBalances(Long id) {
        Bank2AccountBalance bank2accountData = bank2AccountSource.getBalance(id);

        Balance balanceBank1 = new Balance(bank1AccountSource.getAccountBalance(id), bank1AccountSource.getAccountCurrency(id), "Bank 1");
        Balance balanceBank2 = new Balance(bank2accountData.getBalance(), bank2accountData.getCurrency(), "Bank 2");

        List<Balance> balanceList = new ArrayList<>();
        balanceList.addAll(Arrays.asList(balanceBank1, balanceBank2));

        return balanceList;
    }

    @Override
    public List<Transaction> getTransactions(Long id, Date fromDate, Date toDate) {
        List<Bank1Transaction> bank1AccountTransactions = bank1AccountSource.getTransactions(id, fromDate, toDate);
        List<Bank2AccountTransaction> bank2AccountTransactions = bank2AccountSource.getTransactions(id, fromDate, toDate);

        List<Transaction> transactionList = new ArrayList<>();

        for (Bank1Transaction transactionData: bank1AccountTransactions) {
            CardType type = CardType.toEnum(transactionData.getType());
            Transaction transaction = new Transaction(transactionData.getAmount(), type, transactionData.getText(), "Bank 1");
            transactionList.add(transaction);
        }

        for (Bank2AccountTransaction transactionData: bank2AccountTransactions) {
            CardType type = transactionData.getType().equals(Bank2AccountTransaction.TRANSACTION_TYPES.CREDIT) ? CardType.CREDIT : CardType.DEBIT;
            Transaction transaction = new Transaction(transactionData.getAmount(), type, transactionData.getText(), "Bank 2");
            transactionList.add(transaction);
        }

        return transactionList;
    }
}

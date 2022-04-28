package com.leodev0.pyyne.challenge.bank.adapters.outbound;

import com.leodev0.pyyne.bank1.integration.Bank1AccountSource;
import com.leodev0.pyyne.bank2.integration.Bank2AccountSource;
import com.leodev0.pyyne.challenge.bank.application.core.domain.Balance;
import com.leodev0.pyyne.challenge.bank.application.core.domain.Transaction;
import mocks.Mocks;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class UnifiedBanksAdapterTest {

    private Bank1AccountSource bank1AccountSource = new Bank1AccountSource();

    private Bank2AccountSource bank2AccountSource = new Bank2AccountSource();

    private UnifiedBanksAdapter unifiedBanksAdapter = new UnifiedBanksAdapter(bank1AccountSource, bank2AccountSource);

    private List<Balance> mockedBalances = Mocks.buildBalances();

    private List<Transaction> mockedTransactions = Mocks.buildTransactions();

    @Test
    @DisplayName("should return list bank balances from 2 different resources in the same determined format")
    void convertListOfBalances() {
        List<Balance> balances = unifiedBanksAdapter.getBalances(1L);

        Assertions.assertEquals(balances, mockedBalances);
        Assertions.assertTrue(balances.size() == mockedBalances.size());
    }

    @Test
    @DisplayName("should return list bank transactions from 2 different resources in the same determined format")
    void convertListOfTransactions() {
        Date fromDate = new Date();
        Date toDate = new Date();

        List<Transaction> transactions = unifiedBanksAdapter.getTransactions(1L, fromDate, toDate);

        Assertions.assertTrue(transactions.size() == mockedTransactions.size());
        Assertions.assertEquals(transactions.get(0).getAmount(), mockedTransactions.get(0).getAmount());
        Assertions.assertEquals(transactions.get(0).getAmountInCents(), mockedTransactions.get(0).getAmountInCents());
        Assertions.assertEquals(transactions.get(0).getType(), mockedTransactions.get(0).getType());
        Assertions.assertEquals(transactions.get(0).getText(), mockedTransactions.get(0).getText());
        Assertions.assertEquals(transactions.get(0).getBankName(), mockedTransactions.get(0).getBankName());
    }

    @Test
    @DisplayName("should return valid unique UUIDs from Transaction class")
    void returnUniqueUUID() {
        Date fromDate = new Date();
        Date toDate = new Date();

        List<Transaction> transactions = unifiedBanksAdapter.getTransactions(1L, fromDate, toDate);

        Assertions.assertTrue(isValidUUID(transactions.get(0).getId().toString()));
        Assertions.assertNotSame(transactions.get(0).getId(), mockedTransactions.get(0).getId());
        Assertions.assertNotSame(transactions.get(0).getId(), transactions.get(1).getId());
    }

    private boolean isValidUUID(String string) {
        try {
            UUID.fromString(string);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}

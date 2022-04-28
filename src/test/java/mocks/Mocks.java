package mocks;

import com.leodev0.pyyne.challenge.bank.application.core.domain.Balance;
import com.leodev0.pyyne.challenge.bank.application.core.domain.Transaction;
import com.leodev0.pyyne.challenge.bank.application.core.domain.enums.CardType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Mocks {

    private static final String BANK1 = "Bank 1";
    private static final String BANK2 = "Bank 2";

    public static List<Balance> buildBalances() {
        List<Balance> balances = new ArrayList<Balance>();

        Balance balance1 = new Balance(215.5, "USD", BANK1);
        Balance balance2 = new Balance(512.5, "USD", BANK2);

        balances.addAll(Arrays.asList(balance1, balance2));

        return balances;
    }

    public static List<Transaction> buildTransactions() {
        List<Transaction> transactions = new ArrayList<Transaction>();

        Transaction transaction1 = new Transaction(100.0, CardType.CREDIT, "Check deposit", BANK1);
        Transaction transaction2 = new Transaction(25.5, CardType.DEBIT, "Debit card purchase", BANK1);
        Transaction transaction3 = new Transaction(225.0, CardType.DEBIT, "Rent payment", BANK1);
        Transaction transaction4 = new Transaction(125.0, CardType.DEBIT, "Amazon.com", BANK2);
        Transaction transaction5 = new Transaction(500.0, CardType.DEBIT, "Car insurance", BANK2);
        Transaction transaction6 = new Transaction(800.0, CardType.CREDIT, "Salary", BANK2);

        transactions.addAll(Arrays.asList(transaction1, transaction2, transaction3, transaction4, transaction5, transaction6));

        return transactions;
    }
}

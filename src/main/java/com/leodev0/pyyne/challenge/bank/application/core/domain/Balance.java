package com.leodev0.pyyne.challenge.bank.application.core.domain;

import java.util.Objects;

public class Balance {

    private Double balance;
    private String currency;
    private String bankName;

    public Balance(Double balance, String currency, String bankName) {
        this.balance = balance;
        this.currency = currency;
        this.bankName = bankName;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Balance balance1 = (Balance) o;
        return Objects.equals(balance, balance1.balance) && Objects.equals(currency, balance1.currency) && Objects.equals(bankName, balance1.bankName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(balance, currency, bankName);
    }
}

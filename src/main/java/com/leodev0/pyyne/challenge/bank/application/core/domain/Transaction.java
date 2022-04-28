package com.leodev0.pyyne.challenge.bank.application.core.domain;

import com.leodev0.pyyne.challenge.bank.application.core.domain.enums.CardType;

import java.util.Objects;
import java.util.UUID;

public class Transaction {

    private UUID id;
    private Double amount;
    private int amountInCents;
    private CardType type;
    private String text;
    private String bankName;

    public Transaction(Double amount, CardType type, String text, String bankName) {
        this.id = UUID.randomUUID();
        this.amount = amount;
        this.amountInCents = this.convertToCents(amount);
        this.type = type;
        this.text = text;
        this.bankName = bankName;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public CardType getType() {
        return type;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public int getAmountInCents() {
        return amountInCents;
    }

    public void setAmountInCents(int ammountInCents) {
        this.amountInCents = ammountInCents;
    }

    private int convertToCents(Double amount) {
        int cents = (int) Math.round(100 * amount);
        return cents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return amountInCents == that.amountInCents && Objects.equals(id, that.id) && Objects.equals(amount, that.amount) && type == that.type && Objects.equals(text, that.text) && Objects.equals(bankName, that.bankName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, amountInCents, type, text, bankName);
    }
}

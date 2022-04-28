package com.leodev0.pyyne.challenge.bank.application.core.domain.enums;

public enum CardType {

    CREDIT(1, "CREDIT"),
    DEBIT(2, "DEBIT");

    private int code;
    private String description;

    private CardType(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static CardType toEnum(Integer code) {
        if (code == null) {
            return null;
        }

        for (CardType type: CardType.values()) {
            if (code.equals(type.getCode())) {
                return type;
            }
        }

        throw new IllegalArgumentException("Invalid code: " + code);
    }
}

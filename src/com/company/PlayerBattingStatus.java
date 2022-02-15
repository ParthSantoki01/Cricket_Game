package com.company;

public enum PlayerBattingStatus {
    status1("Did Not Bat"),
    status2("Not Out"),
    status3("Out");

    private String value;

    PlayerBattingStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

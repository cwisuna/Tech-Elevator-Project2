package com.techelevator.tenmo.model;

public class Account {

    private int accountId;
    private int userid;
    private double balance;

    public Account() {}

    public Account(int accountId, int userid, double balance) {
        this.accountId = accountId;
        this.userid = userid;
        this.balance = balance;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

}

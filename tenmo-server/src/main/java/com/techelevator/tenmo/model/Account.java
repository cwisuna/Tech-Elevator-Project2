package com.techelevator.tenmo.model;

//our model class Account will represent the table 'account'.
//we added two private variables 'addBalance' and 'subBalance' to work behind the scenes to add amounts to balance and subract amounts from balance

public class Account {

    private int accountId;
    private int userid;
    private double balance;
    private double addBalance;
    private double subBalance;


    //default constructor, allows us to create an Account object without the parameters
    public Account() {}

    //constructor
    public Account(int accountId, int userid, double balance) {
        this.accountId = accountId;
        this.userid = userid;
        this.balance = balance;
    }

    //getters and setters
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

    public double getAddBalance() {
        return addBalance;
    }

    public void setAddBalance(double addBalance) {
        this.addBalance = addBalance;
    }

    public double getSubBalance() {
        return subBalance;
    }

    public void setSubBalance(double subBalance) {
        this.subBalance = subBalance;
    }
}

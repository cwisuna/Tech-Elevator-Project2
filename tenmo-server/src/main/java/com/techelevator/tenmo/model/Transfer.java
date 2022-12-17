package com.techelevator.tenmo.model;

// Our model class Transfer will represent the table 'transfer'

public class Transfer {

    private int transferId;
    private int transferTypeId;
    private int transferStatusId;
    private int accountFrom;
    private int accountTo;
    private double amount;

    // Default constructor, allows us to create a Transfer object without the parameters
    public Transfer() {}

    //constructor
    public Transfer(int transferId, int transferTypeId, int transferStatusId, int accountFrom, int accountTo, double amount) {
        this.transferId = transferId;
        this.transferTypeId = transferTypeId;
        this.transferStatusId = transferStatusId;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = amount;
    }

    //getters and setters
    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }

    public int getTransferId(){
        return transferId;
    }

    public int getTransferTypeId() {
        return transferTypeId;
    }

    public void setTransferTypeId(int transferTypeId) {
        this.transferTypeId = transferTypeId;
    }

    public int getTransferStatusId() {
        return transferStatusId;
    }

    public void setTransferStatusId(int transferStatusId) {
        this.transferStatusId = transferStatusId;
    }

    public int getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(int accountFrom) {
        this.accountFrom = accountFrom;
    }

    public int getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(int accountTo) {
        this.accountTo = accountTo;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    // toString method takes variables from 'transfer' table, presenting these objects as strings
    public String toString() {
        return "Transfer ID" + transferId + "Transfer Id by Type " + transferTypeId + "Transfer Status: " +
                transferStatusId + "Account from : " + accountFrom + "Account to: " + accountTo + "Amount: " + amount;
    }


}

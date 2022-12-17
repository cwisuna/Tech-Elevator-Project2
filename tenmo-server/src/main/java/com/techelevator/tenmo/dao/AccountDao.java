package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

public interface AccountDao {

   public Account getBalance(String user);

   public Account getAccountById(int id);


}

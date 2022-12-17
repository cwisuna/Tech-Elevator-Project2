package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
//our jdbcAccountDao connects to our database

@Component
public class JdbcAccountDao implements AccountDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate dao){
        this.jdbcTemplate = dao;
    }

    //this method will get the balance using sql
    @Override
    public Account getBalance(String user) {
        Account account = new Account();
        String sql = "Select account_id, user_id, balance " +
                "From account " +
                "WHERE user_id = (SELECT user_id " +
                " From tenmo_user " +
                " where username = ? )";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, user);
        if(result.next()){
            account =  mapRowAccount(result);
        }
        return account;
    }

    @Override
    public Account getAccountById(int id) {
        String sql = "SELECT * " +
                " FROM account " +
                " WHERE user_id = ? ";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
        return null;
    }


    private Account mapRowAccount(SqlRowSet rowSet){

        Account account = new Account();

        account.setAccountId(rowSet.getInt("account_id"));
        account.setUserid(rowSet.getInt("user_id"));
        account.setBalance(rowSet.getDouble("balance"));

        return account;

    }


}

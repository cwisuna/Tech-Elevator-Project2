package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.JdbcTransferDao;
import com.techelevator.tenmo.dao.JdbcUserDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Component
@PreAuthorize("isAuthenticated()") //ADDED THIS AS TEST
@RestController
@RequestMapping ("/tenmo")
public class TransferController {

    private JdbcTransferDao transferDao;
    private JdbcUserDao userDao;

    public TransferController (JdbcTransferDao transferDao, JdbcUserDao userDao){
        this.transferDao = transferDao;
        this.userDao = userDao;
    }

    @RequestMapping (path = "/transfer", method = RequestMethod.GET)
    public List<Transfer> getAllTransfers(){
        List<Transfer> transfers = transferDao.getAllTransfers();
        return transfers;
    }

    @RequestMapping (path = "/transfer/{id}", method = RequestMethod.GET)
    public Transfer getTransferTypeById (@PathVariable int id){
        Transfer transfer = transferDao.getTransferTypeById(id);
        return transfer;
    }


    //call our createTransfer method from our JdbcTransferDao
    @RequestMapping (path = "/transfer", method = RequestMethod.POST)
        public Transfer createTransfer (@RequestBody Transfer transfer, Principal principal){
        return transferDao.createTransfer(transfer);
    }




}



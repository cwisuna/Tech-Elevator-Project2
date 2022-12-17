package com.techelevator.tenmo;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.UserCredentials;
import com.techelevator.tenmo.services.AccountService;
import com.techelevator.tenmo.services.AuthenticationService;
import com.techelevator.tenmo.services.ConsoleService;
import com.techelevator.tenmo.services.TransferService;

import java.math.BigDecimal;
import java.util.Scanner;

public class App {

    private static final String API_BASE_URL = "http://localhost:8080/";

    private final ConsoleService consoleService = new ConsoleService();
    private final AuthenticationService authenticationService = new AuthenticationService(API_BASE_URL);
    private final TransferService transferService = new TransferService(API_BASE_URL);
    private AuthenticatedUser currentUser;


    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    private void run() {
        consoleService.printGreeting();
        loginMenu();
        if (currentUser != null) {
            mainMenu();
        }
    }
    private void loginMenu() {
        int menuSelection = -1;
        while (menuSelection != 0 && currentUser == null) {
            consoleService.printLoginMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                handleRegister();
            } else if (menuSelection == 2) {
                handleLogin();
            } else if (menuSelection != 0) {
                System.out.println("Invalid Selection");
                consoleService.pause();
            }
        }
    }

    private void handleRegister() {
        System.out.println("Please register a new user account");
        UserCredentials credentials = consoleService.promptForCredentials();
        if (authenticationService.register(credentials)) {
            System.out.println("Registration successful. You can now login.");
        } else {
            consoleService.printErrorMessage();
        }
    }

    private void handleLogin() {
        UserCredentials credentials = consoleService.promptForCredentials();
        currentUser = authenticationService.login(credentials);
        if (currentUser == null) {
            consoleService.printErrorMessage();
        }
    }

    private void mainMenu() {
        int menuSelection = -1;
        while (menuSelection != 0) {
            consoleService.printMainMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                viewCurrentBalance();
            } else if (menuSelection == 2) {
                viewTransferHistory();
            } else if (menuSelection == 3) {
                viewPendingRequests();
            } else if (menuSelection == 4) {
                sendBucks();
            } else if (menuSelection == 5) {
                requestBucks();
            } else if (menuSelection == 0) {
                continue;
            } else {
                System.out.println("Invalid Selection");
            }
            consoleService.pause();
        }
    }

    //Method to call AccountService getBalance to display on CLI
	private void viewCurrentBalance() {
		// TODO Auto-generated method stub
        AccountService accountService = new AccountService(API_BASE_URL);
        accountService.getBalance(currentUser.getToken());
        System.out.println("Your current balance is: " + accountService.getBalance(currentUser.getToken()));
	}

	private void viewTransferHistory() {
		// TODO Auto-generated method stub
        TransferService transferService = new TransferService(API_BASE_URL);
       // transferService.listAllTransfer();

        //PROMPTING USER TO VIEW DETAILS OF CERTAIN TRANSACTION BY ID
        int userInput = consoleService.promptForInt("Please enter transfer ID to view details (0 to cancel): ");
	}


	private void viewPendingRequests() {
		// TODO Auto-generated method stub
		
	}

	private void sendBucks() {
		// TODO Auto-generated method stub

        Transfer sendTransfer = new Transfer();
        // A new transfer needs to have a transfer_id (LATER), transferType, transferStatus, and accountFrom, accountTo, and amount
        sendTransfer.setTransferTypeId(2);
        sendTransfer.setAccountFrom(currentUser.getUser().getId()); //ACCOUNT FROM IS USER ID, IN THE DATABASE THAT ACCOUNT FROM ID IS THE ACCOUNT ID
        sendTransfer.setTransferStatusId(2);

        int userInput = consoleService.promptForInt("Enter ID of User you are sending to (0 to cancel): ");
        sendTransfer.setAccountTo(userInput);
        double moneySending = consoleService.promptForDouble("Enter amount: ");
        sendTransfer.setAmount(moneySending);

        transferService.createTransfer(sendTransfer, currentUser.getToken());

	}

	private void requestBucks() {
		// TODO Auto-generated method stub
		
	}

}

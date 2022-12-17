    package com.techelevator.tenmo.dao;
    import com.techelevator.tenmo.model.Account;
    import org.springframework.jdbc.core.JdbcTemplate;
    import com.techelevator.tenmo.model.Transfer;
    import org.springframework.jdbc.support.rowset.SqlRowSet;
    import org.springframework.stereotype.Component;

    import java.security.Principal;
    import java.util.ArrayList;
    import java.util.List;



    @Component
    public class JdbcTransferDao implements TransferDao{

        private JdbcTemplate jdbcTemplate;

        public JdbcTransferDao(JdbcTemplate dao){
            this.jdbcTemplate = dao;
        }

        @Override
        public List<Transfer> getAllTransfers() {
            List<Transfer> transfers = new ArrayList<>();
            String sql = " SELECT transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount " +
                         " FROM transfer ; ";
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

            while(results.next()){
                Transfer transfer = transferMapper(results);
                transfers.add(transfer);
            }
            return transfers;
        }

        public Transfer getTransfer(int id){
            String sql = " SELECT transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount " +
                         " FROM transfer " +
                         " WHERE transfer_id = ? ; ";
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);

            if (results.next()) {
                return transferMapper(results);
            }
            return null;
        }

        //this will get the transfer type by id from our database
        @Override
        public Transfer getTransferTypeById(int id) {
            Transfer transfer = new Transfer();
            String sql = " SELECT transfer_type_id " +
                         " FROM transfer ; ";
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
            if (result.next()) {
                transfer = transferMapper(result);
            } else {
                return null;
            }
                return transfer;
        }


        @Override
        public Transfer createTransfer(Transfer newTransfer) {
            String sql = " INSERT INTO transfer (transfer_type_id, transfer_status_id, account_from, account_to, amount) " +
                         " VALUES (?, ?, ?, ?, ?) RETURNING transfer_id ; ";
            int accountToId = userIdToAccountId(newTransfer.getAccountTo());
            int accountFromId = userIdToAccountId(newTransfer.getAccountFrom());
            double amount = newTransfer.getAmount();
            //DOUBLES BELOW COULD BE USEFUL ON CLIENT SIDE FOR SECURITY CHECKS
    //        double senderBalance = gettingBalance(accountFromId); //NOT SURE IF CORRECT
    //        double receiverBalance = gettingBalance(accountToId);
           Integer result = jdbcTemplate.queryForObject(sql, Integer.class, newTransfer.getTransferTypeId(), newTransfer.getTransferStatusId(), accountFromId, accountToId, amount);
            return getTransfer(result);
            //THIS IF STATEMENT IS CHECKING TO MAKE SURE (1)USER IS NOT SENDING MONEY TO THEM SELF, (2)USER CANNOT SEND MORE MONEY THAN HAVE IN ACCOUNT, (3)AND USER CANNOT SEND ZERO OR NEGATIVE MONEY
            //CHECK THIS ONE THE CLIENT SIDE!!
          /*  if (accountFromId != accountToId && amount > 0 && amount <= senderBalance && senderBalance > 0) {
                jdbcTemplate.update(sql, accountFromId, accountToId, newTransfer.getAmount());
                transferAddToBalance(accountToId, newTransfer.getAmount());
                transferSubtractFromBalance(accountFromId, newTransfer.getAmount());
            } else {
                System.out.println("Unable to complete transaction.");
            } */
        }

        //TRYING TO GET AMOUNT FROM TRANSFER TABLE TO BE ABLE TO USE IN THE IF STATEMENT ABOVE
        private int gettingAmount(int transfer_id){
            String sql = " SELECT amount " +
                         " FROM transfer " +
                         " WHERE transfer_id = ? ; "; //WOULD THIS BE TRANSFER_ID?
            return jdbcTemplate.queryForObject(sql, Integer.class, transfer_id);
        }
        //TRYING TO GET BALANCE FROM ACCOUNT TABLE TO BE ABLE TO USE IN THE IF STATEMENT ABOVE
        private int gettingBalance(int account_id){
            String sql = " SELECT balance " +
                         " FROM account " +
                         " WHERE account_id = ? ; ";
            return jdbcTemplate.queryForObject(sql, Integer.class, account_id);
        }


        private int userIdToAccountId(int userId){
            String sql = " SELECT account_id " +
                         " FROM account " +
                         " WHERE user_id = ? ; ";
            Integer result = jdbcTemplate.queryForObject(sql, Integer.class, userId);
            return (int)result;
        }

        private int accountIdFromUserId(int accountId){
            String sql = " SELECT user_id " +
                         " FROM user " +
                         " WHERE account_id = ? ; ";
            Integer result = jdbcTemplate.queryForObject(sql, Integer.class, accountId);
            return (int)result;

        }

        private void transferAddToBalance(int id, double amount) {
                String sql = " UPDATE account SET balance = balance + ?" +
                             " WHERE account_id = ? ; ";
                jdbcTemplate.update(sql, amount, id);
            }

        private void transferSubtractFromBalance(int id, double amount) {
                String sql = " UPDATE account SET balance = balance - ? " +
                             " WHERE account_id = ? ; ";
                jdbcTemplate.update(sql, amount, id);
            }

        private Transfer transferMapper(SqlRowSet result){
                Transfer transfer = new Transfer();
               // transfer.setAccount(result.getInt("account_id"));
                transfer.setTransferTypeId(result.getInt("transfer_type_id"));
                transfer.setTransferId(result.getInt("transfer_id"));
                transfer.setTransferStatusId(result.getInt("transfer_status_id"));
                transfer.setAccountFrom(result.getInt("account_from"));
                transfer.setAccountTo(result.getInt("account_to"));
                transfer.setAmount(result.getDouble("amount"));
                return transfer;
        }

    }

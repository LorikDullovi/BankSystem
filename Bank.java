import java.util.HashMap;
import java.util.Map;

public class Bank {
    private String bankName;
    private Map<String, Account> accounts;
    private double totalTransactionFeeAmount;
    private double totalTransferAmount;
    private double transactionFlatFee;
    private double transactionPercentFee;

    public Bank(String bankName, double transactionFlatFee, double transactionPercentFee) {
        this.bankName = bankName;
        this.totalTransactionFeeAmount = 0.0;
        this.totalTransferAmount = 0.0;
        this.transactionFlatFee = transactionFlatFee;
        this.transactionPercentFee = transactionPercentFee;
        this.accounts = new HashMap<>();
    }
    public String getBankName(){
        return bankName;
    }

    public void addAccount(Account account) {
        accounts.put(account.getAccountId(), account);
    }

    public Account getAccount(String accountId) {
        return accounts.get(accountId);
    }

    public void withDrawBank(String result, double amount){
        Account account = getAccount(result);
        if (account != null) {
            if (account.withdrawal(amount)) {
                Transaction t1;
                try {
                    t1 = new Transaction(amount, result, null, "Withdraw!!");
                    account.addTransaction(t1);
                } catch (AccountException e) {
                    e.printStackTrace();
                }
                System.out.println("Succsessful withdrawal. New Balance: " + account.getBalance() + " Dollar");
            } else {
                System.out.println("Failed Withdrawal. Insufficient funds.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    public void depositBank(String result, double amount) throws AccountException {
        Account account = getAccount(result);
        if (account != null) {
            account.deposit(amount);
            Transaction t2 = new Transaction(amount, null, result, "Deposit!!");
            account.addTransaction(t2);
            System.out.println("Successful deposit. New balance: "+ account.getBalance() + " Dollar");
        } else {
            System.out.println("Account not found.");
        }
    }

    public void performTransfer(String originatingId, String resultingId, double amount, boolean  useFlatFee)
            throws AccountException {
        Account initialAccount = getAccount(originatingId);
        Account resultingAccount = getAccount(resultingId);

        if (initialAccount != null && resultingAccount != null) {
            double provision = useFlatFee ? transactionFlatFee : (amount * transactionPercentFee / 100);
            double shumaTotale = amount + provision;

            if (initialAccount.withdrawal(shumaTotale)) {
                resultingAccount.deposit(amount);
                totalTransactionFeeAmount = totalTransactionFeeAmount + provision;
                totalTransferAmount = totalTransferAmount + amount;

                Transaction t3 = new Transaction(amount, originatingId, resultingId, "Transferim");
                Transaction t4 = new Transaction(amount, originatingId, resultingId, "Transferim");

                initialAccount.addTransaction(t3);
                resultingAccount.addTransaction(t4);
                System.out.println("Transfer successful. Fee: " +  provision );
            } else {
                System.out.println("Transfer failed. Insufficient funds!!");
            }
        } else {
            System.out.println("One or both accounts not found!!");
        }
    }

    public void showTransactions(String result) {
        Account account = getAccount(result);
        if (account != null) {
            System.out.println("Transactions for the account" + result + ":");
            for (Transaction transaction : account.getTransaction()) {
                System.out.println("Amount: " + transaction.getAmount() +
                        ", Novices: " + transaction.getOriginatingId() +
                        ", Resultant: " + transaction.getResultingId() +
                        ", Reason: " + transaction.getAmount());
            }
        } else {
            System.out.println("Account not found!!");
        }
    }

    public void listAccounts() {
        System.out.println("Bank accounts:");
        for (Account account : accounts.values()) {
            System.out.println("ID: " + account.getAccountId() + ", User: "
                    + account.getNameOfUser() + ", Balance: " +  account.getBalance() + " Dollar");
        }
    }

    public void showAccountBalance(String result) {
        Account account = getAccount(result);
        if (account != null) {
            System.out.println("Account balance " + result + ": " + account.getBalance()+ " Dollar");
        } else {
            System.out.println("Account not found!!");
        }
    }

    public void showTotalTransactionFeeAmount() {
        System.out.println("Total transaction fee: " + totalTransactionFeeAmount);
    }

    public void showTotalTransferAmount() {
        System.out.println("Total amount of transfers: " + totalTransferAmount);
    }
}

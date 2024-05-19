import java.util.ArrayList;

public class Account {
    private String accountId;
    private String nameOfUser;
    private double balance;
    private ArrayList<Transaction> transactions;

    public Account(String accountId, String nameOfUser, double balance) throws AccountException {
        if (accountId == null || accountId.trim().equals("")) {
            throw new AccountException("Invalid Id");
        }
        this.accountId = accountId;

        if (nameOfUser == null || nameOfUser.trim().equals("")) {
            throw new AccountException("Invalid Name");
        }
        this.nameOfUser = nameOfUser;

        if (balance <= 0) {
            throw new AccountException("The account balance doesn't go below zero");
        }
        this.balance = balance;
        transactions = new ArrayList<>();
    }

    public String getAccountId() {
        return accountId;
    }

    public String getNameOfUser() {
        return nameOfUser;
    }

    public void setNameOfUser(String nameOfUser) throws AccountException {
        if (nameOfUser == null || nameOfUser.trim().equals("")) {
            throw new AccountException("Invalid Name");
        }
        this.nameOfUser = nameOfUser;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) throws AccountException {
        if (balance <= 0) {
            throw new AccountException("The account balance doesn't go below zero");
        }
        this.balance = balance;
    }

    public ArrayList<Transaction> getTransaction() {
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void deposit(double value) {
        balance += value;
    }

    public boolean withdrawal(double value) {
        if (balance >= value) {
            balance -= value;
            return true;
        } 
        return false;
    }

    public boolean equals(Object obj){
        if(obj instanceof Account){
            Account a = (Account)obj;
            if(a.getAccountId().equals(accountId)){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return accountId + " : " + nameOfUser + " - " + balance;
    }

}
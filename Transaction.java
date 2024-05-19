public class Transaction {
    private double amount;
    private String originatingId;
    private String resultingId;
    private String reason;

    public Transaction(double amount, String originatingId, String resultingId, String reason) throws AccountException {
        if (amount <= 0) {
            throw new AccountException("Negative number");
        }
        this.amount = amount;
        if (reason == null || reason.trim().equals("")) {
            throw new AccountException("Invalid reason!!");
        }
        this.reason = reason;
        
        this.originatingId = originatingId;
        this.resultingId = resultingId;

      
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) throws AccountException {
        if (amount <= 0) {
            throw new AccountException("Negative number");
        }
        this.amount = amount;
    }

    public String getOriginatingId() {
        return originatingId;
    }

    public String getResultingId() {
        return resultingId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) throws AccountException {
        if (reason == null || reason.trim().equals("")) {
            throw new AccountException("Invalid reason!!");
        }
        this.reason = reason;
    }

    @Override
    public String toString() {
        return amount + " : " + originatingId + " - " + resultingId + " -> " + reason;
    }

}
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws AccountException {
        try {
            Scanner scan = new Scanner(System.in);

            System.out.print("Enter the name of the bank: ");
            String bankName = scan.nextLine();
            System.out.print("Enter the fixed amount of the transaction fee: ");
            double fixedRates = scan.nextDouble();
            System.out.print("Enter the fee value as a percentage of the transaction: ");
            double feePercentage = scan.nextDouble();

            Bank bank = new Bank(bankName, fixedRates, feePercentage);
            scan.nextLine();

            while (true) {
                System.out.println("1. Create a new account!!");
                System.out.println("2. Bank Withdrawal");
                System.out.println("3. Deposit");
                System.out.println("4. Transfer with fixed fee");
                System.out.println("5. Transfer with percentage fee");
                System.out.println("6. Show account transactions");
                System.out.println("7. Check account balance");
                System.out.println("8. Display the list of bank accounts");
                System.out.println("9. Check the total transaction fee amount");
                System.out.println("10. Check the total amount of bank transfers");
                System.out.println("11. Exit");
                int number = scan.nextInt();
                scan.nextLine();

                switch (number) {
                    case 1:
        
                    System.out.print("Enter account ID: ");
                    String accountId = scan.nextLine();
                    System.out.print("Enter username: ");
                    String username = scan.nextLine();
                    System.out.print("Enter the initial account balance: ");
                    double initialBalance = scan.nextDouble();
                    scan.nextLine();
                    Account a1 = new Account(accountId, username, initialBalance);
                    bank.addAccount(a1);
                    System.out.println("Account created successfully!!");
                    break;
                    case 2:
                        System.out.print("Enter account ID for withdrawal: ");
                        String withdrawalId = scan.nextLine();
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawalAmount = scan.nextDouble();
                        scan.nextLine();
                        bank.withDrawBank(withdrawalId, withdrawalAmount);
                        break;
                    case 3:
                        System.out.print("Enter account ID for deposit: ");
                        String depositId = scan.nextLine();
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = scan.nextDouble();
                        scan.nextLine();
                        bank.depositBank(depositId, depositAmount);
                        break;
                    case 4:
                        System.out.print("Enter originating account ID: ");
                        String initialId = scan.nextLine();
                        System.out.print("Enter resulting account ID: ");
                        String resultId = scan.nextLine();
                        System.out.print("Enter amount to transfer: ");
                        double fixedTransferAmount = scan.nextDouble();
                        scan.nextLine();
                        bank.performTransfer(initialId, resultId, fixedTransferAmount, true);
                        break;
                    case 5:
                        System.out.print("Enter originating account ID: ");
                        String intitalIdPercentage = scan.nextLine();
                        System.out.print("Enter resulting account ID: ");
                        String resultIdPercentage = scan.nextLine();
                        System.out.print("Enter amount to transfer: ");
                        double fixedTransferAmountrPercentage = scan.nextDouble();
                        scan.nextLine();
                        bank.performTransfer(intitalIdPercentage, resultIdPercentage, fixedTransferAmountrPercentage,
                                false);
                        break;
                    case 6:
                        System.out.print("Enter account ID to show transactions: ");
                        String transactionId = scan.nextLine();
                        bank.showTransactions(transactionId);
                        break;
                    case 7:
                        System.out.print("Enter account ID to check balance: ");
                        String conditionId = scan.nextLine();
                        bank.showAccountBalance(conditionId);
                        break;
                    case 8:
                        bank.listAccounts();
                        break;
                    case 9:
                        bank.showTotalTransactionFeeAmount();
                        break;
                    case 10:
                        bank.showTotalTransferAmount();
                        break;
                    case 11:
                        System.out.println("Exit the program.");
                        System.exit(0);
                    default:
                        System.out.println("Invalid!!.");
                }
            }

        } catch (AccountException ae) {
            System.out.println(ae.getMessage());
        } finally {
            System.out.println("End!!");
        }

    }
}

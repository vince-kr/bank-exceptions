import java.util.InputMismatchException;
import java.util.Scanner;

class BankingApp {
    private boolean userIsFinished;
    private final BankAccount account;
    private final Scanner userInput = new Scanner(System.in);

    public BankingApp(BankAccount account) {
        this.account = account;
    }

    private final String[] menu = {
            "Deposit",
            "Withdraw",
            "Check balance",
            "Exit",
    };

    public String[] getMenu() {
        return menu;
    }

    public void makeChoice() {
        String menuChoice = getMenuChoice(userInput);
        String keepBanking = "Wanna do anything else?";

        switch (menuChoice) {
            case "Deposit" -> {
                double depositAmount = getValidAmount();
                double newBalance = account.deposit(depositAmount);
                System.out.println("Deposit successful. Your new balance is: " + newBalance);
                System.out.println(keepBanking);
            }
            case "Withdraw" -> {
                double withdrawAmount = getValidAmount();
                double newBalance = validWithdrawal(withdrawAmount);
                System.out.println("Withdrawal successful. Your new balance is: " + newBalance);
                System.out.println(keepBanking);
            }
            case "Check balance" -> {
                System.out.println(getBalance());
                System.out.println(keepBanking);
            }
            case "Exit" -> {
                System.out.println("Thank you for banking!");
                userIsFinished = true;
            }
        }
    }

    private String getMenuChoice(Scanner userInput) {
        int userChoice;
        String menuChoice;
        while (true) {
            try {
                userChoice = userInput.nextInt();
                try {
                    menuChoice = menu[userChoice-1];
                    break;
                } catch (ArrayIndexOutOfBoundsException ib) {
                    System.out.println("That option does not appear in the menu.");
                    System.out.print("Your choice: ");
                    userInput.nextLine();
                }
            } catch (InputMismatchException im) {
                System.out.println("Please enter a NUMBER.");
                System.out.print("Your choice: ");
                userInput.nextLine();
            }
        }
        return menuChoice;
    }

    private double getValidAmount() {
        double amount;
        String prompt = "Please enter the amount: ";

        System.out.print(prompt);

        while (true) {
            try {
               amount = userInput.nextDouble();
               if (amount <= 0) {
                   System.out.println("Amount must be greater than zero.");
                   userInput.nextLine();
                   System.out.print(prompt);
                   continue;
               }
               break;
            }
            catch (InputMismatchException im) {
                System.out.println("That is not a valid number. Please try again.");
                userInput.nextLine();
                System.out.print(prompt);
            }
        }

        return amount;
    }

    private double validWithdrawal(double amount) {
        double newBalance;
        while (true) {
            try {
                newBalance = account.withdraw(amount);
                break;
            } catch (InsufficientFundsException inF) {
                System.out.println(inF);
                amount = getValidAmount();
            }
        }
        return newBalance;
    }

    private double getBalance() {
        return account.getBalance();
    }

    public boolean userIsFinished() {
        return userIsFinished;
    }
}
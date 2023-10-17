import java.util.InputMismatchException;
import java.util.Scanner;

class BankingApp {
    private boolean userIsFinished;
    BankAccount account;

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
        String menuChoice = getMenuChoice(new Scanner(System.in));
        String keepBanking = "Wanna do anything else?";

        switch (menuChoice) {
            case "Deposit" -> {
                double depositAmount = getValidDepositAmount(new Scanner(System.in));
                account.deposit(depositAmount);
                System.out.println(keepBanking);
            }
            case "Withdraw" -> {
                System.out.println("We're doing withdrawing, after checking you're not withdrawing too much!");
                double withdrawAmount = getValidWithdrawAmount(new Scanner(System.in));
                while (true) {
                    try {
                        account.withdraw(withdrawAmount);
                        break;
                    } catch (InsufficientFundsException inF) {
                        System.out.println(inF);
                        withdrawAmount = getValidWithdrawAmount(new Scanner(System.in));
                    }
                }
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

    private double getValidDepositAmount(Scanner userInput) {
        double depositAmount;
        String prompt = "Please enter the amount to deposit: ";

        System.out.print(prompt);

        while (true) {
            try {
               depositAmount = userInput.nextDouble();
               if (depositAmount <= 0) {
                   System.out.println("Deposit must be greater than zero.");
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

        return depositAmount;
    }

    private double getValidWithdrawAmount(Scanner userInput) {
        double withdrawAmount;
        String prompt = "Please enter the amount to withdraw: ";

        System.out.print(prompt);

        while (true) {
            try {
                withdrawAmount = userInput.nextDouble();
                if (withdrawAmount <= 0) {
                    System.out.println("Withdrawal amount must be greater than zero.");
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

        return withdrawAmount;
    }

    private double getBalance() {
        return account.getBalance();
    }



    public boolean userIsFinished() {
        return userIsFinished;
    }
}
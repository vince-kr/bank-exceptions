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
                System.out.println(keepBanking);
            }
            case "Check balance" -> {
                System.out.println("We're telling you your balance!");
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
                System.out.print(prompt);
                userInput.nextLine();
            }
        }

        return depositAmount;
    }

    public boolean userIsFinished() {
        return userIsFinished;
    }
}
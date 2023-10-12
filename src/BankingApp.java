import java.util.InputMismatchException;
import java.util.Scanner;

public class BankingApp {
    boolean userIsFinished;
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
        switch (menuChoice) {
            case "Deposit" -> {
                System.out.println("We're doing depositing!");
                System.out.println("Wanna do anything else?");
            }
            case "Withdraw" -> {
                System.out.println("We're doing withdrawing, after checking you're not withdrawing too much!");
                System.out.println("Wanna do anything else?");
            }
            case "Check balance" -> {
                System.out.println("We're telling you your balance!");
                System.out.println("Wanna do anything else?");
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
                    userInput.nextLine();
                }
            } catch (InputMismatchException im) {
                System.out.println("Please enter a NUMBER.");
                userInput.nextLine();
            }
        }
        return menuChoice;
    }

    public boolean userIsFinished() {
        return userIsFinished;
    }
}

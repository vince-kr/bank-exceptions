import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BankingApp bankApp = new BankingApp();
        Scanner userInput = new Scanner(System.in);

        System.out.println(bankApp.getMenu());
        try {
            int userChoice = userInput.nextInt();
            System.out.println(userChoice);
        }
        catch (InputMismatchException im) {
            System.out.println("Enter a number, you fool");
        }
    }
}
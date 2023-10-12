public class Main {
    public static void main(String[] args) {
        BankingApp bankApp = new BankingApp(new BankAccount());

        System.out.println("""
                ============================
                === WELCOME TO BANK APP! ===
                ============================
                
                """);

        do {
            String[] menu = bankApp.getMenu();
            for (int i=0; i < menu.length; i++) {
                System.out.println((i+1) + ".  " + menu[i]);
            }
            bankApp.makeChoice();
        } while (!bankApp.userIsFinished());
    }
}
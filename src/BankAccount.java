class BankAccount {
    private int accountNumber;
    private double balance;

    double deposit(double amount) {
        balance += amount;
        return balance;
    }

    double withdraw(double amount) throws InsufficientFundsException {
        if(amount > balance) {
            throw new InsufficientFundsException();
        }
        balance -= amount;
        return balance;
    }

    double getBalance() {
        return balance;
    }
}

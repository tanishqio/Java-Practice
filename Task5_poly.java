// Your Task — Polymorphism in Action
// Add a printStatement() override to both SavingsAccount and CurrentAccount that adds extra info:
// SavingsAccount should print:
// ---- Savings Account ----
// Owner: Tanishq
// Balance: 1000.0
// Interest Rate: 5.0%
// CurrentAccount should print:
// ---- Current Account ----
// Owner: Saksham
// Balance: 400.0
// Overdraft Limit: 1000.0
// Then in main:
// java// Store all accounts in a BankAccount array
// BankAccount[] accounts = {
//     new SavingsAccount("Tanishq", 1234, 5.0),
//     new CurrentAccount("Saksham", 5678, 1000)
// };

// // Print all with ONE loop — polymorphism handles the rest
// for (BankAccount acc : accounts) {
//     acc.printStatement();
// }
// You'll need to use super.printStatement() inside the child's override to reuse the parent's print logic. Think about where to put it.
// Post your code when done.

class BankAccount {

    private String owner;
    private double balance;
    private int accountNumber;

    BankAccount(String name, int number) {
        this.owner = name;
        this.accountNumber = number;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Cant deposit negative amount"); 
        }else {
            balance += amount;
        }
    }

    public void withdraw(double amount) {
        if (amount > balance || amount <= 0) {
            System.out.println("cant withdraw");
        } else {
            balance -= amount;
        }
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double num){
        balance=num;
    }

    public void printStatement() {
        System.out.println("Welcome to your account!");
        System.out.println(" Your account info is:");
        System.out.println("accountNumber" + accountNumber);
        System.out.println("Name: " + owner);
        System.out.println("Balance: " + balance);

    }
}

class SavingsAccount extends BankAccount {

    private double interestRate;

    SavingsAccount(String owner,int accountNumber,double interestRate){
        super(owner,accountNumber);
        this.interestRate=interestRate;
    }

    @Override
    public void printStatement(){
        System.out.println("--- Savings Account ---");
        super.printStatement();
        System.out.println("Interest Rate:" + interestRate);

    }

    public void applyInterest() {
        double interest = getBalance() * interestRate / 100;
        deposit(interest);
        System.out.println("Interest Added in account: "+ interest);
    }
}

class CurrentAccount extends BankAccount{
    private double overdraftLimit;
    CurrentAccount(String owner,int accountNumber,double overdraftLimit){
        super(owner,accountNumber);
        this.overdraftLimit=overdraftLimit;
    }
    @Override
    public void withdraw(double amount){
        double totalamount=getBalance()+overdraftLimit;
        if(amount<=0 || amount>totalamount){
            System.out.println("your current account doesnt have sufficent with overdraftLimit");
        return;
        }
        if(amount>getBalance()){
            double extra=amount-getBalance();
            setBalance(0);
            overdraftLimit-=extra;
        }
        else{
            setBalance(getBalance()-amount);
        }
    }

    @Override
    public void printStatement(){
        System.out.println("--- Current Account ---");
        super.printStatement();
        System.out.println("Overdraft Limit:" + overdraftLimit);

    }
}

public class Task5_poly{
    public static void main(String[] args){
        BankAccount[] accounts={
            new SavingsAccount("Tanishq",1234,5),
            new CurrentAccount("saksham",5678,1000)
        };

        for(BankAccount acc:accounts){
            acc.printStatement();
        }
    }
}

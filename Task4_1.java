// Your Task — Extend the BankAccount
// Build two child classes:
// SavingsAccount extends BankAccount:

// Extra field: double interestRate
// Extra method: applyInterest() — adds interest to balance
// CurrentAccount extends BankAccount:
// Extra field: double overdraftLimit
// Override withdraw() — allow withdrawal beyond balance up to overdraftLimit
// In main:
// Create one of each
// Test deposit and withdraw on both
// Apply interest on savings account
// Try to overdraw current account within limit — should work
// Try to overdraw beyond limit — should be rejected
// The keyword you'll need for override:
// java@Override
// public void withdraw(double amount) {
//     // your new logic here
// }
// @Override tells Java you're intentionally replacing the parent's method. If you spell the method name wrong Java will catch it immediately — very useful.
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
}

public class Task4_1{
    public static void main(String[] args){
        CurrentAccount c=new CurrentAccount("tanishq",12345,1000);
        SavingsAccount s= new SavingsAccount("saksham",56789,10);

        //deposit 
        s.deposit(500);
        c.deposit(500);
        // withdraw
        s.withdraw(100);
        c.withdraw(100);

        s.printStatement();
        c.printStatement();


        //apply interest
        s.applyInterest();

        s.printStatement();
        c.printStatement();
        //cuurent withdraw with limit
        c.withdraw(1400);

        // beyond limit
        c.withdraw(10);
        
        s.printStatement();
        c.printStatement();
    }
}

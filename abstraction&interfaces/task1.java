// Your Task
// Redesign the bank system using both:
// 1. Make BankAccount abstract with:

// Concrete methods: deposit(), getBalance(), getOwner()
// Abstract methods: withdraw(), printStatement()

// 2. Create interface Transferable:
// javainterface Transferable {
//     void transfer(BankAccount target, double amount);
// }
// 3. SavingsAccount:

// extends BankAccount
// implements Transferable
// implements all abstract methods
// implements transfer()

// 4. CurrentAccount:

// extends BankAccount
// implements Transferable
// implements all abstract methods with overdraft logic
// implements transfer()

// 5. In main:
// javaBankAccount[] accounts = {
//     new SavingsAccount("Tanishq", 1234, 5.0),
//     new CurrentAccount("Saksham", 5678, 1000)
// };

// // deposit into both
// // transfer from savings to current
// // print all statements with one loop
// This is exactly how real Java banking systems are designed. Post your code when done.

abstract class BankAccount implements Transferable{
private String Owner;
protected double balance;
private int accountNumber;


 BankAccount(String Owner, int accountNumber){
    this.Owner=Owner;
    this.accountNumber=accountNumber;
 }
public void deposit(double amount){
    if(amount<=0){
System.out.println("add positive amount to deposit");
return;
    }
    balance+=amount;
}
public String getOwner(){
    return Owner;
}
public double getBalance(){
    return balance;
}
public int getaccount(){
    return accountNumber;
}

public abstract void withdraw(double amount);
public abstract void printStatement();
}

interface Transferable{
    void transfer(BankAccount target, double amount);
}

class SavingsAccount extends BankAccount implements Transferable{
    private double interestRate;
    SavingsAccount(String Owner,int accountNumber,double interestRate){
        super(Owner,accountNumber);
        this.interestRate=interestRate;
    }
    @Override
    public void withdraw(double amount){
        if(amount>balance){
        System.out.println("Dont have enough balance in account");
        return;
        }
        balance-=amount;
    }

    @Override
    public void printStatement(){
        System.out.println("--- Current Account ---");
        System.out.println("Account Number:" + getaccount());
        System.out.println("Owner Name:" + getOwner());
        System.out.println("Balance:" + getBalance());
        System.out.println("Interest Rate:" + interestRate);
    }
    @Override
    public void transfer(BankAccount target, double amount){
        if(target!=null){
            if(balance>=amount){
               target.balance+=amount;
               balance-=amount;
            }
            else{
                System.out.println("No sufficinet balance to transfer");
            }
        }
        else{
            System.out.println("No such account exists");
        }
    }
}

//considerng with every withdraw we always get overdraft
class CurrentAccount extends BankAccount implements Transferable{
    private double overdraftLimit;
    CurrentAccount(String Owner,int accountNumber,double overdraftLimit){
        super(Owner,accountNumber);
        this.overdraftLimit=overdraftLimit;
    }
    @Override
    public void withdraw(double amount){
        double totalAmount=balance+overdraftLimit;
        if(amount>totalAmount){
        System.out.println("Dont have enough balance in account");
        return;
        }
        if(amount>balance){
            balance=0;
        }
        else{
        balance-=amount;
    }}

    @Override
    public void printStatement(){
        System.out.println("--- Current Account ---");
        System.out.println("Account Number:" + getaccount());
        System.out.println("Owner Name:" + getOwner());
        System.out.println("Balance:" + getBalance());
        System.out.println("OVerdraft Limit:" + overdraftLimit);
    }
    @Override
    public void transfer(BankAccount target, double amount){
        double totalAmount=balance+overdraftLimit;
        if(target!=null){
            if(totalAmount>=amount){
               target.balance+=amount;
               if(amount>balance) {balance=0;}
               else {balance-=amount;}
            }
            else{
                System.out.println("No sufficinet balance to transfer");
            }
        }
        else{
            System.out.println("No such account exists");
        }
    }
}

public class task1{
    public static void main(String[] args){
        BankAccount[] accounts = {
    new SavingsAccount("Tanishq", 1234, 5.0),
    new CurrentAccount("Saksham", 5678, 1000)
};
   for(BankAccount acc: accounts){
    acc.deposit(1000);
   }
accounts[0].transfer(accounts[1],1000);
for(BankAccount acc:accounts){
    acc.printStatement();
}
    }
}
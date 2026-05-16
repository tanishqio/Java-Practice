// Your Task — Exception Handling In BankAccount
// Upgrade your BankAccount system with proper exception handling:
// 1. Create two custom exceptions:
// javaclass InsufficientFundsException extends RuntimeException {
//     // constructor takes the attempted amount
// }

// class InvalidAmountException extends RuntimeException {
//     // constructor takes the invalid amount
// }
// 2. Update BankAccount methods to throw them:

// deposit() → throws InvalidAmountException if amount <= 0
// withdraw() → throws InvalidAmountException if amount <= 0
// withdraw() → throws InsufficientFundsException if amount > balance

// 3. In main — handle all cases:
// java// Test all three scenarios with try/catch
// // 1. deposit negative amount
// // 2. withdraw more than balance
// // 3. successful transaction
// // Print meaningful messages for each
// This is exactly how Spring Boot REST APIs handle errors — custom exceptions get caught and converted to HTTP 400/404/500 responses.


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
throw new InvalidAmountException(amount);
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
        throw new InsufficientFundsException(amount);
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
               throw new InsufficientFundsException(amount);
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
            throw new InsufficientFundsException(amount);
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
                throw new InsufficientFundsException(amount);
            }
        }
        else{
            System.out.println("No such account exists");
        }
    }
}

class InsufficientFundsException extends RuntimeException{
    private double amount;
    public InsufficientFundsException(double amount){
        super("Insufficient Funds, tried to withdraw:"+ amount);
        this.amount=amount;
    }
    public double getAmount(){
        return amount;
    }
}
class InvalidAmountException extends RuntimeException{
    private double amount;
    public InvalidAmountException(double amount){
    super("enter correct value of amount, neagtivve amount cant be processed!");
     this.amount=amount;
    }
        public double getAmount(){
        return amount;
    }
}


public class task1{
    public static void main(String[] args){
        BankAccount s= new SavingsAccount("tanishq",1234,10);
        BankAccount c=new CurrentAccount("saksham",5678,1000);
try{
        s.deposit(1000);
        c.deposit(1000);
        s.deposit(-500);
    System.out.println("Transaction done successfully!");

}
catch(InvalidAmountException e){
    System.out.println(e.getMessage());
    System.out.println("Invalid amount entered was: "+ e.getAmount());
}


try{
    s.withdraw(15000);
    System.out.println("Transaction done successfully!");

}
catch( InsufficientFundsException e){
    System.out.println("Not enough funds, enter acc to your balance");
    System.out.println("amount entered was:"+e.getAmount());
}

    }
}
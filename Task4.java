// Your Task — Build This From Scratch
// Create a class BankAccount with:
// Fields (all private):

// String owner
// double balance
// int accountNumber

// Constructor:

// Takes owner and accountNumber
// Sets balance to 0 by default

// Methods:

// deposit(double amount) — adds to balance, reject if amount ≤ 0
// withdraw(double amount) — subtracts from balance, reject if amount > balance or ≤ 0
// getBalance() — returns balance
// printStatement() — prints owner, account number, balance nicely

// In main:

// Create two different accounts
// Deposit and withdraw from both
// Try to overdraw one account and show it gets rejected
// Print statements for both

 class BankAccount{
    
   private String owner;
   private double balance;
   private int accountNumber;
    
    BankAccount(String name, int number){
        this.owner=name;
        this.accountNumber=number;
    }
  public  void deposit(double amount){
        if(amount<=0) System.out.println("Cant deposit negative amount");
        else{
            balance+=amount;
        } 
}
public void withdraw(double amount){
    if(amount>balance || amount<=0){ 
        System.out.println("cant withdraw");
        }
    else{
        balance-=amount;
    }
}
 public double getBalance(){
    return balance;
}
public void printStatement(){
    System.out.println("Welcome to your account!");
    System.out.println(" Your account info is:");
    System.out.println("accountNumber"+accountNumber);
    System.out.println("Name: "+ owner);
    System.out.println("Balance: "+balance);

}
}
public class Task4{
    public static void main(String[] args){
       BankAccount a1=new BankAccount("tanishq",1234);
       BankAccount a2=new BankAccount("tani",5678);
       //deposit
       a1.deposit(10000);
       a2.deposit(500);

       a1.withdraw(9999);
       a2.withdraw(300);

       //overdraw
         a2.withdraw(210);

         //statements
         a1.printStatement();
         a2.printStatement();


    }
}
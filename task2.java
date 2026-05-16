// Task 2 — The == trap (5 min):
// Write code that demonstrates all three cases:

// Two new String() objects with == → false
// Two string literals with == → true
// Correct comparison using .equals()
// Print what you expect and what you get, and explain in a comment why.

public class task2{
    public static void main(String[] args){
        //so new string creates new address of it and skips the pool and enters it in java heap and stack memory
        String a="tanishq";
        String b="tanishq";
        //as a and b are pointing towards same in stack pool so thet have same memory address and == compares memory 
        //address and not its content

        System.out.println("is address same:"+ (a==b));


        String c=new String("Boy");
        String d=new String("Boy");

        //diff address so will show false
        System.out.println("is addres same: "+(c==d));

        // for checking the same content we use .equals()
        System.out.println("is content same: "+ a.equals(c));

        System.out.println("is content same: "+ a.equals(b);


    }
}
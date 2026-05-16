// Task 3 — Pass by value proof (10 min):
// Write two methods:

// tryToDouble(int n) — tries to double n, returns nothing
// addElement(int[] arr) — adds 99 to index 0
// Call both from main and print before/after to prove Java's pass-by-value behavior for both cases.

public class Task3{
     
             // so in java there is no way of pass by reference so the values are always passed through pass by copy only
       static void tryToDouble(int n){
            n=n+n;
        }

     static void addElement(int[] arr){
            arr[0]+=99;
        }
    public static void main(String[] args){

        // now taking n as 10 and when i will be passing it 
        int n=10;
        System.out.println("Before passing n is "+ n);
        tryToDouble(n);
        System.out.println("After passing n is "+ n);
        //no change in value as the n doubled there is local and is only valid in that function only

        //for arr
        int[] arr=new int[1];
           System.out.println("Before passing arr is "+ arr[0]);
        addElement(arr);
           System.out.println("After passing arr is "+ arr[0]);

    }
}
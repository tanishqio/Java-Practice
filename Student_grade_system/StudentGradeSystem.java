// Build a StudentGradeSystem class that:
// Stores:

// Student names mapped to their list of marks — HashMap<String, ArrayList<Integer>>

// Methods:

// addStudent(String name) — adds new student, throws IllegalArgumentException if already exists
// addMarks(String name, int marks) — adds marks for student, throws if student doesn't exist, throws if marks < 0 or > 100
// getAverage(String name) — returns average marks as double
// getTopStudent() — returns name of student with highest average
// printReport() — prints all students, their marks, and average

// In main:
// javaStudentGradeSystem sys = new StudentGradeSystem();
// sys.addStudent("Tanishq");
// sys.addStudent("Saksham");
// sys.addStudent("Raj");

// sys.addMarks("Tanishq", 85);
// sys.addMarks("Tanishq", 92);
// sys.addMarks("Tanishq", 78);
// sys.addMarks("Saksham", 90);
// sys.addMarks("Saksham", 88);
// sys.addMarks("Raj", 70);
// sys.addMarks("Raj", 75);
// sys.addMarks("Raj", 80);

// // test exception — add duplicate student
// try {
//     sys.addStudent("Tanishq");
// } catch (IllegalArgumentException e) {
//     System.out.println("❌ " + e.getMessage());
// }

// // test exception — invalid marks
// try {
//     sys.addMarks("Tanishq", 150);
// } catch (IllegalArgumentException e) {
//     System.out.println("❌ " + e.getMessage());
// }

// sys.printReport();
// System.out.println("Top student: " + sys.getTopStudent());


import java.util.ArrayList;
import java.util.HashMap;

 class GradeSystem{
    private HashMap<String,ArrayList<Integer>> map= new HashMap<>();
    
    public void addStudent(String name){
        
         if(map.containsKey(name)){
            throw new IllegalArgumentException("student already exists!");
       
         }
       map.put(name,new ArrayList<>());

       
    }
    public void addMarks(String name,int marks){
        if(map.containsKey(name)){
             if(marks>=0 && marks<=100){
                      map.get(name).add(marks);
             }
             else{
                throw new IllegalArgumentException("Enter valid marks value");
             }
        }
        else{
            throw new IllegalArgumentException("No student exists with this name");
        }
    }
    public double getAverage(String name){
        if(map.containsKey(name)){
          ArrayList<Integer> m=map.get(name);
          double len=m.size();
          if(len==0) return 0;
          double sum=0;
          for(Integer val:m){
             sum+=val;
          }
          return sum/len;
        }
        else{
            throw new IllegalArgumentException("No such student exist with this name");
        }
    }
    public String getTopStudent(){
        double highavg=0;
          String toppername="";
        for(var it:map.entrySet()){
            String name=it.getKey();
            double avg=getAverage(name);
           if(avg>highavg){
            toppername=name;
            highavg=avg;
           }
           }
        
    return toppername;}

    public void printReport(){

        for(var it:map.entrySet()){
                String name=it.getKey();
            double avg=getAverage(name);
            ArrayList<Integer> arr=it.getValue();
        System.out.println("Name:"+name+"|| Marks are: "+arr+ "|| Average is: "+avg);
          
        }
        

    }


 }

 public class StudentGradeSystem{
    public static void main(String[] args){
        GradeSystem sys = new GradeSystem();
sys.addStudent("Tanishq");
sys.addStudent("Saksham");
sys.addStudent("Raj");

sys.addMarks("Tanishq", 85);
sys.addMarks("Tanishq", 92);
sys.addMarks("Tanishq", 78);
sys.addMarks("Saksham", 90);
sys.addMarks("Saksham", 88);
sys.addMarks("Raj", 70);
sys.addMarks("Raj", 75);
sys.addMarks("Raj", 80);

// test exception — add duplicate student
try {
    sys.addStudent("Tanishq");
} catch (IllegalArgumentException e) {
    System.out.println("❌ " + e.getMessage());
}

// test exception — invalid marks
try {
    sys.addMarks("Tanishq", 150);
} catch (IllegalArgumentException e) {
    System.out.println("❌ " + e.getMessage());
}

sys.printReport();
System.out.println("Top student: " + sys.getTopStudent());


    }
 }
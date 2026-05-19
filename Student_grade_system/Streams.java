// // Your Task — Upgrade GradeSystem With Streams
// // Rewrite these methods in GradeSystem using streams:
// // 1. getTopStudent() — use streams instead of for loop
// // 2. Add new method getPassingStudents(double minAverage) — returns List<String> 
// of students with average above minAverage using stream filter
// // 3. Add new method printSortedReport() — prints report sorted alphabetically 
// by student name using streams
// // 4. In main add:
// // javaSystem.out.println("Passing students: " + sys.getPassingStudents(80));
// // sys.printSortedReport();
// // Hint for getTopStudent with streams:
// // java// you'll need this pattern
// // map.entrySet().stream()
// //    .max(Comparator.comparingDouble(e -> getAverage(e.getKey())))
// //    .map(e -> e.getKey())
// //    .orElse("No students");


import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

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
    public String getTopStudent(){ return map.entrySet().stream()
                                       .max(Comparator.comparingDouble(e->getAverage(e.getKey())))
                                       .map(e->e.getKey())
                                       .orElse("No topper!");}
    //     double highavg=0;
    //       String toppername="";
    //     for(var it:map.entrySet()){
    //         String name=it.getKey();
    //         double avg=getAverage(name);
    //        if(avg>highavg){
    //         toppername=name;
    //         highavg=avg;
    //        }
    //        }
        
    // return toppername;
    
    

    public void printReport(){

        for(var it:map.entrySet()){
                String name=it.getKey();
            double avg=getAverage(name);
            ArrayList<Integer> arr=it.getValue();
        System.out.println("Name:"+name+"|| Marks are: "+arr+ "|| Average is: "+avg);
          
        }
        

    }
    // 2. Add new method getPassingStudents(double minAverage) — returns List<String> 
// of students with average above minAverage using stream filter
public List<String> getPassingStudents(double minAverage){
return map.entrySet().stream()
        .filter(e-> getAverage(e.getKey())>minAverage).map(e-> e.getKey())
        .toList();
}

// 3. Add new method printSortedReport() — prints report sorted alphabetically 
// by student name using streams
public void printSortedReport(){
    map.entrySet().stream().sorted(Comparator.comparing(e->e.getKey()))
    .forEach(e-> System.out.println("Name: "+ e.getKey()+ "  | Marks: "+e.getValue()+" | Average: "+
    getAverage(e.getKey())    ));
}

 }

 public class Streams{
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
 System.out.println("Passing students: " + sys.getPassingStudents(80));
sys.printSortedReport();

    }

    
 }
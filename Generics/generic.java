// Your Task
// Write a generic class Pair<K, V> that:

// Stores two values of potentially different types — a key and a value
// Constructor takes both
// getKey() and getValue() methods
// swap() method — returns a new Pair<V, K> with key and value swapped
// toString() override — prints (key, value)

// Then write a generic static method findMax that takes an ArrayList<T> where T extends Comparable<T> and returns the maximum element.
// Test in main:
// javaPair<String, Integer> p = new Pair<>("Tanishq", 95);
// System.out.println(p);                    // (Tanishq, 95)
// System.out.println(p.swap());             // (95, Tanishq)

// System.out.println(findMax(scores));      // where scores is ArrayList<Integer>
// System.out.println(findMax(names));       // where names is ArrayList<String>
// Post your code when done.
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
class Pair<K,V>{
    private K key;
    private V val;
    Pair(K key, V val){
        this.key=key;
        this.val=val;
    }
    public K getKey(){
        return key;
    }
    public V getValue(){
        return val;
    }
    public Pair<V,K> swap(){
         return new Pair<>(val,key);
    }

    @Override
    public String toString(){
      return "("+key+","+val+")";
    }
}

public class generic{
    public static <T extends Comparable<T>> T findMax(ArrayList<T> arr){
       return arr.stream().max(Comparator.naturalOrder()).orElse(null);
            }
    public static void main(String[] args) {
        Pair<String, Integer> p = new Pair<>("Tanishq", 95);
System.out.println(p);                    // (Tanishq, 95)
System.out.println(p.swap());             // (95, Tanishq)
ArrayList<Integer> scores=new ArrayList<>(List.of(122,12,232,434));
ArrayList<String> names=new ArrayList<>(List.of("tani","saksa","ravi"));

System.out.println(findMax(scores));      // where scores is ArrayList<Integer>
System.out.println(findMax(names));       // where names is ArrayList<String>
  
    }
    }

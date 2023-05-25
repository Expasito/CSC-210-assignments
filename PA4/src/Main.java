

interface Pair<K, V>
{
K getKey();
V getValue();
}
class OrderedPair<K, V> implements Pair<K, V>
{
final private K key;
final private V value;
public OrderedPair(K key, V value) {
this.key = key;
this.value = value;
}
public K getKey() { return key; }
public V getValue() { return value; }
@Override
public String toString() {
return "OrderedPair [key=" + key + ", value=" + value + "]";
}
}
class Box<T>
{
private T t;
public void set(T t) { this.t = t; }
public T get() { return t; }
}
public class Main
{
public static void main(String args[])
{
Pair<String, Integer> p1 = new OrderedPair<String, Integer>("Even", 8);
Pair<String, String> p2 = new OrderedPair<String, String>("hello", "world");

//Pair<String, Integer> p1 = new OrderedPair<>("Even", 8);
//Pair<String, String> p2 = new OrderedPair<>("hello", "world");

//var p1 = new OrderedPair<>("Even", 8);
//var p2 = new OrderedPair<>("hello", "world");
System.out.println(p1);
System.out.println(p2);
var p = new OrderedPair<>("primes", new Box<>());
System.out.println(p);
}
}
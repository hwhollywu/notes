# Basic Java / Java基础知识

Sources：
- [寒桐 - java新人入门进阶指南](https://www.atatech.org/articles/37888)
- [GitHub - Interview-Notebook](https://github.com/CyC2018/Interview-Notebook/blob/master/notes/Java%20基础.md)
- GeeksforGeeks


![Basic Java Menu](https://github.com/hwhollywu/notes/blob/master/BasicJava.jpg?raw=true)



## 1 接口 Interface & 类 class
- Interface: a group of methods with empty bodies. 
Class implements interface and defines actual methods.

```java
interface Drawable{  
	void draw();  
}  
class Rectangle implements Drawable{  
	public void draw(){System.out.println("drawing rectangle");
	}  
}  
```

## 2 抽象类 abstract class & 抽象方法 abstract method
- abstract class: a group of methods without implementations. 
```java
abstract class Demo ｛
    abstract void method1();
    abstract void method2();
｝
```
- abstract method
```java
abstract void moveTo(double deltaX, double deltaY);
```

- Abstract class & abstract method: 
abstract class may or may not contain abstract methods, but if a class has abstract methods, it MUST be declared abstract. 

- Abstract class & interface: 
interface must have all fields as public & static & final, abstract class can have them be private, protected, etc. 
Subclass implements an interface, extends an abstract class.

## 3 对象 Object vs. 类 Class
An object is an instance of a class. 

```java
public class Puppy {
   public Puppy(String name) {
      // This constructor has one parameter, name.
      System.out.println("Passed Name is :" + name );
   }

   public static void main(String []args) {
      // Following statement would create an object myPuppy
      Puppy myPuppy = new Puppy( "tommy" );
   }
}
```

## 4 基本类型初始化 types and initialization & 数据结构 data structures
8 primitive data types: 
- boolean
- char
- byte(8 bits)
- short(16)
- int(32)
- long(64)
- float(32,floating-point)
- double(64,floating-point)
```java
// instances of primitive data types
boolean one = true
char letterA = 'A'
byte a = 100
short s = 10000
int b = -200000
long a = 100000L
float f1 = 234.5f
double d1 = 123.4
```

### Data Structures
Source: [Data Structures](https://www.geeksforgeeks.org/data-structures/)
##### Array: 
O(1) to access, O(n) to add/delete  -> in Java, fixed size
```java
int[] arr = new int[10]; 
```

##### ArrayList: 
dynamic sized arrays
```java
ArrayList<Integer> arrL = new ArrayList<Integer>();
arrL.add(1);
arrL.get(0);
```

##### LinkedList : 
O(1) to add/delete, O(n) to access
```java
LinkedList<String> object = new LinkedList<String>();
object.add(“C”);
object.addLast(“Z”); 
object.addFirst(“A”);
object.add(2,“B”);
object.get(2);
object.remove(1);
object.clear();
```

##### Stack & Queue
- Stack- LIFO, push/pop 
In java, stack extends Vector class, with methods: push, pop, peek(return the top element but not remove it), empty, search
Vector class: implement list interface, extends AbstractList class, similar to ArrayList
- Queue -FIFO, enqueue, dequeue
In java, queue is an interface with methods: add, peek/element, remove, pull, can be initialized as a priority queue or a linked list. 

(to be completed)
##### Binary Tree
##### Binary Search Tree
##### Heap
##### Hashing
##### Graph
##### Matrix

## 5.集合框架 Collection interface in Java(List Map Set)
### List
List: interface that is implemented by ArrayList, LinkedList, Vector and Stack classes.
Methods: add, addAll, remove, get, set
```java
List a = new ArrayList();
General object: Obj is type of object to be stored in List.
List<Obj> list = new List<Obj> ();   
```

### Map
Map: interface that is implemented by TreeMap, LinkedHashMap, HashMap(no predictable order). 
A map cannot contain duplicated keys and each key can map to at most one value.
Methods: put, putAll, remove, get, containsKey, keySet(returns all keys), entrySet
```java
HashMap< String,Integer> hm = new HashMap< String,Integer>();
```

### Set
Set: interface that is implemented by HashSet, LinkedSet, TreeSet.
Methods: add, remove, clear, size, etc.
```java
Set<String> hash_Set = new HashSet<String>();
```

## 6. 面向对象编程OOP 三大特性: 封装 Encapsulation 继承 Inheritance 多态 Polymorphism
### Encapsulation
Encapsulation: the wrapping up of data under a single unit, to prevent the data from being accessed by the code outside this shield.
Advantages: data hiding, increased flexibility, reusability, easy for testing.

```java
public class Person {
    private String name;
    private int gender;
    private int age;

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender == 0 ? "man" : "woman";
    }

    public void setAge(int newAge) {
        age = newAge;
}
```
### Inheritance &  super-sub class
里氏替换原则 The Liskov Substitution Principle(LSP):  functions that use references to base classes must be able to use objects of the derived class without knowing it
```java
Animal animal = new Cat();
```

Subclass can inherit all public and protected methods and fields from super class, but not private ones.
```java

public class Animal{
	public int age;
	public String name;
	public  Animal(int startAge, String startName){
	age=startAge;
	speed = startName;
}
}

public class Cat extends Animal{
	public String color;
	public Cat(int startAge, String startName, String startColor){
		super(startAge, startName);
		color = startColor
}
	public void setColor(int newColor){
		color = newColor;
}
}
// keyword: super, super.method(), super.field,  in constructor super(field)
```
### Polymorphism
Polymorphism: perform a single action in different ways.   keyword: override
```java
public class Horse extends Animal{
...
    @Override 
    public void sound(){
        System.out.println("Neigh");
    }
}
```
Compile time polymorphism: method overloading
Same method name, different parameters
```java
class Overload
{
    void demo (int a)
    {
       System.out.println ("a: " + a);
    }
    void demo (int a, int b)
    {
       System.out.println ("a and b: " + a + "," + b);
    }
    double demo(double a) {
       System.out.println("double a: " + a);
       return a*a;
    }
}

    Overload Obj = new Overload();
    double result;
    Obj .demo(10);
    Obj .demo(10, 20);
    result = Obj .demo(5.5);
    System.out.println("O/P : " + result);

```

- Override: the subclass method has the same name as the super class method
- Overload: Within the same class, there are multiple methods with the same name but different parameters/orders/return values.

## 7. 反射 reflection
reflection is an API used to examine or modify the behavior of methods, classes and interface at run time. 
Methods: getClass() - get the name of the class of the object, 
getConstructors() - get the public constructor, 
getMethods() - get the public methods

Advantages: extensibility, debugging and testing tools
Disadvantages: performance overhead, exposure of internals/breaks abstractions

```java
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.lang.reflect.Constructor;

Test obj = new Test();
Class cls = obj.getClass();
Constructor constructor = cls.getConstructor();
Method[] methods = cls.getMethods();
for (Method method:methods)
            System.out.println(method.getName());

// can invoke a method
Method.invoke(Object, parameter)
// name of method, an array of class objects
Class.getDeclaredMethod(name, parametertype)

```

## 8. 泛型 Generics
generics: to allow types (integer, string, ...) to be parameter to methods, classes and interfaces. 
e.g. HashSet, ArrayList, HashMap use generics
Advantages: code reuse, type safety(make errors at run time instead of compile time)

```java
// To create an instance of generic class 
Test <Integer> obj = new Test <Integer>(15);
// multiple type parameters in Generic classes
Test <String, Integer> obj = new Test<String, Integer>("GfG", 15);
```

## 9. 序列化 Serialization
Serialization: the mechanism of converting the state of an object into a byte stream.
Deserialization: use the byte stream to recreate the actual Java object.

Advantages: to save the state of an object, to travel an object across a network.

- If a parent class has implemented Serializable interface, then child class doesn’t need to implement it.
- Only non-static data members are saved via Serialization process.
- Associated objects must be implementing Serializable interface.

```java
class Demo implements java.io.Serializable
{
    public int a;
    public String b;
 
    // Default constructor
    public Demo(int a, String b){
        this.a = a;
        this.b = b;
    }
 
}

class Test
{
  public static void main(String[] args){
      Demo object = new Demo(1, "hi");
      String filename = "file.ser";
        // Serialization 
        try{
              //Saving of object in a file
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);
             
            // Method for serialization of object
            out.writeObject(object);
             
            out.close();
            file.close();
             
            System.out.println("Object has been serialized");

        }
        catch(IOException ex){
            System.out.println("IOException is caught");
        }
  }
      Demo object1 = null;
 
        // Deserialization
        try
        {   
            // Reading the object from a file
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);
             
            // Method for deserialization of object
            object1 = (Demo)in.readObject();
             
            in.close();
            file.close();
             
            System.out.println("Object has been deserialized ");
            System.out.println("a = " + object1.a);
            System.out.println("b = " + object1.b);
        }
         
        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }
         
        catch(ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException is caught");
        }
 
}

```

## 10. 注解 Annotation
- start with @
- not equal to comments, cause it change the way a program is treated by compiler.
Categories:
- marker annotation : mark a declaration.  @Override
- single value annotaion: specifying the value of the only member. @TestAnnotation(test);
- full annotation: multiple data members/names/values.  @TestAnnotation(owner=”Rahul”, value=”Class Geeks”)
Built-in Annotations (java.lang, java.lang.annotation)
- @Retention
- @Documented
- @Target
- @Inherited
- @Deprecated: declaration is replaced by a newer form
- @Override
- @SuppressWarnings: suppress specified compiler warnings
```java
    @SuppressWarnings({"checked", "deprecation"})
```

## 11. 异常Exceptions
Exception: an unwanted or unexpected event, which disrups the normal flow of the program

Class Hierarchy: Object -> Throwable -> Exceptions(can catch) & Errors

- Checked Exception: checked at compile time, use "throws" keyword
```java
import java.io.*;
 
class Main {
    public static void main(String[] args) throws IOException {
        FileReader file = new FileReader("C:\\test\\a.txt");
        BufferedReader fileInput = new BufferedReader(file);
         
        // Print first 3 lines of file "C:\test\a.txt"
        for (int counter = 0; counter < 3; counter++) 
            System.out.println(fileInput.readLine());
         
        fileInput.close();
    }
}

```

- Unchecked Exception: not checked at compiled time
```java
class Main {
   public static void main(String args[]) {
      int x = 0;
      int y = 10;
      int z = y/x;
  }
}
// throws ArithmeticException 

```


## 12. Java函数式编程 Functional Programming
[JDK 8 函数式编程入门](https://www.cnblogs.com/snowInPluto/p/5981400.html)

- 命令式编程 Imperative Programming: uses a sequence of statements to determine how to reach a certain goal, to change the state of the program as each one is executed in turn. e.g. Java, C, C++
- 声明式编程 declarative programming: a style that expresses the logic of a computation without describing its control flow. e.g. SQL, HTML 
- 函数式编程 Functional Programming: pure functional approach to problem solving, a form of declarative programming. 
- 逻辑式编程 Logic Programming: program statements express facts and rules about problems within a system of formal logic. e.g. prolog, 

Advantage of Pure Functions: self-contained and stateless, increased readability and maintainability

### 12.1 JAVA8: lambda expressions

```java
// Use ActionListener interface & rewrite actionPerformed method
button.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent event) {
        System.out.println("button clicked");
    }
});
// Using lambda
button.addActionListener(event -> System.out.println("button clicked"));

// 5 ways to use lambda in Java
Runnable noArguments = () -> System.out.println("Hello World");

ActionListener oneArgument = event -> System.out.println("button clicked");

Runnable multiStatement = () -> {
    System.out.print("Hello");
    System.out.println(" World");
};

BinaryOperator<Long> add = (x, y) -> x + y;

BinaryOperator<Long> addExplicit = (Long x, Long y) -> x + y;


// lambda for hashmap

mappy.forEach((key,value) -> {
});

// lambda for list

listy.stream().forEach(acl -> {
});

```

### 12.2 流 Stream
```java
// collect(toList()) : make a list from the stream
List<String> collected = Stream.of("a", "b", "c")
                               .collect(Collectors.toList());
assertEquals(Arrays.asList("a", "b", "c"), collected);

// map: transfer an anonymous input of the function 
List<String> collected = Stream.of("a", "b", "hello")
                               .map(string -> string.toUpperCase())
                               .collect(toList());
assertEquals(asList("A", "B", "HELLO"), collected);

// filter: loop and select elements
List<String> beginningWithNumbers = 
        Stream.of("a", "1abc", "abc1")
              .filter(value -> isDigit(value.charAt(0)))
              .collect(toList());
assertEquals(asList("1abc"), beginningWithNumbers);

// flatMap: connect streams
List<Integer> together = Stream.of(asList(1, 2), asList(3, 4))
                               .flatMap(numbers -> numbers.stream())
                               .collect(toList());
assertEquals(asList(1, 2, 3, 4), together);

// max & min 
List<Integer> list = Lists.newArrayList(3, 5, 2, 9, 1);
int maxInt = list.stream()
                 .max(Integer::compareTo)
                 .get();
int minInt = list.stream()
                 .min(Integer::compareTo)
                 .get();
assertEquals(maxInt, 9);
assertEquals(minInt, 1);

```


## 13. Other Keywords/Methods

### 13.1 final
final: a non-access modifier applicable to a variables/methods/classes.
final variables: constant variables
final methods: prevent method overriding
final classes: prevent inheritance

### 13.2 static
- static:  a non-access modifier applicable to blocks/variables/methods/nested classes.
- static variables:  global variable, shared among class. 
- static methods: main class, can only call other static methods/access static data/ can't refer to this or super
- nested classes: static nested class + inner class(non-static)
- static nested class: access only static members of outer class.
- static block: gets executed exactly once, to initialize 
```java 
    // static variable
    static int a = 10;
    static int b;
     
    // static block
    static {
        System.out.println("Static block initialized.");
        b = a * 4;
    }
```

### 13.3 equals()
* equals(): value comparison
* == : object references 
```java
Integer x = new Integer(1);
Integer y = new Integer(1);
System.out.println(x.equals(y)); // true
System.out.println(x == y);      // false
```

### 13.4 clone()
clone(): protected method in 'java.lang.Object', instead of from Cloneable interface
* Cloneable interface
```java
public class CloneExample implements Cloneable {
    private int a;
    private int b;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
```
* Shallow Clone: same object reference
* Deep Clone: different object reference

```java
// shallow clone
e2 = e1.clone(); //shallow
e1.set(2,222); // e1[2]=222
e2.get(2); // -> return 222

// deep clone
e2.get(2); // -> 2


    @Override
    protected ShallowCloneExample clone() throws CloneNotSupportedException {
        return (ShallowCloneExample) super.clone();
    }

    @Override
    protected DeepCloneExample clone() throws CloneNotSupportedException {
        DeepCloneExample result = (DeepCloneExample) super.clone();
        result.arr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result.arr[i] = arr[i];
        }
        return result;
    }

```


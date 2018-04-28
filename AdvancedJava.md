# Java IO + Multithreading + JVM / Java进阶知识

Sources:
- [GitHub - Java IO.md](https://github.com/CyC2018/Interview-Notebook/blob/master/notes/Java%20IO.md)
- [GitHub - Java 并发.md](https://github.com/CyC2018/Interview-Notebook/blob/master/notes/Java%20%E5%B9%B6%E5%8F%91.md)
- [GitHub - Java 虚拟机.md](https://github.com/CyC2018/Interview-Notebook/blob/master/notes/Java%20%E8%99%9A%E6%8B%9F%E6%9C%BA.md)

![Advanced Java Menu](https://github.com/hwhollywu/notes/blob/master/Advanced.jpg?raw=true)

## 1. 基本文件读写操作 Java IO
### 1.1 Disk: File
### 1.2 Byte: InputStream 和 OutputStream
Hierarchy: 
InputStream(abstract) -> FileInputStream, PipedInputStream, ByteArrayInputStream, FilterInputStream(abstract)
FilterInputStream(abstract) -> DataInputStream, BufferedInputStream, PushbackInputStream
```java
BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
// 批量读入文件
byte[] buf = new byte[20*1024];
int bytes = 0;
// 最多读取 buf.length 个字节，返回的是实际读取的个数，返回 -1 的时候表示读到 eof，即文件尾
while((bytes = in.read(buf, 0 , buf.length)) != -1) {
    // ...
}
```
### 1.3 Character: Reader 和 Writer
Reader -> InputStreamReader (extends)
Writer -> OutputStreamWriter (extends)
```java
public class InputStreamReaderDemo {

   public static void main(String[] args) throws IOException {
      FileInputStream fis = null;
      InputStreamReader isr = null;
      char c;
      int i;
      
      try {
      
         // new input stream reader is created 
         fis = new FileInputStream("C:/test.txt");
         isr = new InputStreamReader(fis);
         
         // read till the end of the file
         while((i = isr.read())!=-1) {
         
            // int to character
            c = (char)i;
            
            // print char
            System.out.println("Character Read: "+c);
         }
         
      } catch (Exception e) {
      
         // print error
         e.printStackTrace();
      } finally {
         
         // closes the stream and releases resources associated
         if(fis!=null)
            fis.close();
         if(isr!=null)
            isr.close();
      }   
   }
}
```
Encoding
GBK: Chinese 2bytes, English 1 byte
UTF-8: Chinese 3 bytes, English 1 byte
UTF-16be(JAVA): both 2 bytes
```java
byte[] bytes = str.getBytes(encoding);     // 编码
String str = new String(bytes, encoding)； // 解码
```

### 1.4 Object: Serializable
Serialization: ObjectOutputStream.writeObject()
Deserialization: ObjectInputStream.readObject()

### 1.5 Network
InetAddress: IP 网络硬件资源
```java 
InetAddress.getByName(String host);
InetAddress.getByAddress(byte[] addr);
```
URL: Uniform Resource Locator
```java
URL url = new URL("http://www.baidu.com");
InputStream is = url.openStream();                           // 字节流 Byte
InputStreamReader isr = new InputStreamReader(is, "utf-8");  // 字符流 Char
BufferedReader br = new BufferedReader(isr);
String line = br.readLine();
while (line != null) {
    System.out.println(line);
    line = br.readLine();
}
br.close();
isr.close();
is.close();

```

传输控制协议 TCP: Transmission Control Protocol: most common (using Socket)

网络插座 Socket: an internal endpoint for sending or receiving data within a node on a computer network   

load web: 
- computer sends TCP packet to the web server's address(ask for the web page)
- web server sends a stream of TCP packets  
- web browser combines the packets and display the web page
```java
// open a socket
    ServerSocket MyService;
    try {
       MyClient = new Socket("Machine name", PortNumber);
       // if server: MyServerice = new ServerSocket(PortNumber);
        }
        catch (IOException e) {
           System.out.println(e);
        }

// create an input/output stream
    DataInputStream input;
    // DataOutputStream output;
    try {
       input = new DataInputStream(MyClient.getInputStream());
       // output = new DataOutputStream(MyClient.getOutputStream());
    }
    catch (IOException e) {
       System.out.println(e);
    }
```

用户数据报协议 Datagram/UDP: User Datagram Protocol
difference: fast, no error-checking
used in broadcasts & online games


## 2. IO/NIO 
[Getting started with new I/O (NIO) - IBM](https://www.ibm.com/developerworks/java/tutorials/j-nio/j-nio.html)
I/O: stream, one byte at a time. 
NIO: blocks, a block of data at a time (faster). lacks some of the elegance and simplicity. 
- Introduced in JDK 1.4 
- Channels and buffers are central objects in NIO.
- NIO channel must has configureBlocking off

### 2.1 Channels 
Channels: ~= streams in I/O package, let data pass through, bi-directional.
Types: FileChannel, DatagramChannel, SocketChannel, ServerSocketChannel

### 2.2 Buffers
Buffer: a container of data, to be written to or read from. 
Types: ByteBuffer, CharBuffer, ShortBuffer, IntBuffer, LongBuffer, FloatBuffer, DoubleBuffer
Parameters: capacity, position(bytes read/written till now), limit(more bytes can be read/written)

```java
//create channel from FileInputStream
FileInputStream fin = new FileInputStream("readandshow.txt");
FileChannel fic = fin.getChannel();

// create buffer with capacity = 1024
ByteBuffer buffer = ByteBuffer.allocate(1024);

// read data from FileChannel to Buffer
int r = fcin.read(buffer);
if (r == -1) {
     break;
}

FileOutputStream fout = new FileOutputStream("writesomebytes.txt");
FileChannel foc = fout.getChannel();

// use flip() to swtich between read/write for buffer
buffer.flip();

// buffer -> output file channel
foc.write(buffer)

buffer.clear();

```

## 3. 线程基础知识 Threads & 并发 Multithreading

Both threads and processes are independent sequences of execution.
- thread: run in a shared memory space. A thread is an entity within a process that can be scheduled for execution. Smaller, easier to switch. 
- process: run in separate memory spaces. A process ia an executing instance of a program is called a process. Larger, hard to switch. e.g. JVM is a process in OS. 

- simultaneous: both things occur at the same time 
- concurrent: things appear to be running simultaneously but in reality they're switching so fast you can't tell.

### 3.1 thread status
- new
- runnable
- blocking
- waiting : waiting for the other threads
- timed waiting : will run by system after a certain period of time
- terminated 

### 3.2 how to use threads (interface vs. inheritance)
```java
// 1. implements Runnable interface
public class MyRunnable implements Runnable {
    public void run() {
        // ...
    }
}
public static void main(String[] args) {
    MyRunnable instance = new MyRunnable();
    Thread thread = new Thread(instance);
    thread.start();
}
```

// 2. implements Callable interface (can return a value and use FutureTask to pack the value)
```java
public static void main(String[] args) throws ExecutionException, InterruptedException {
    MyCallable mc = new MyCallable();
    FutureTask<Integer> ft = new FutureTask<>(mc);
    Thread thread = new Thread(ft);
    thread.start();
}
```

// 3. extends Thread class
```java
public static void main(String[] args) {
    MyThread mt = new MyThread();
    mt.start();
}
```

* Advantage of using Runnable/Callable interface:
class - no multiple inheritance, interface - extend other classes

* Advantage of using thread class:
it has some inbuilt methods like yield(), interrupt() etc.

### 3.3 Thread Operations

#### Executor
Executor: running asynchronous tasks and typically manage a pool of threads
Types: 
- CachedTreadPool - one thread per task, 
- FixedThreadPool - fixed number of threads, 
- SingleThreadExecutor - fixed number = 1

```java
public static void main(String[] args) {
    ExecutorService executorService = Executors.newCachedThreadPool();
    for (int i = 0; i < 5; i++) {
        executorService.execute(new MyRunnable());
    }
    executorService.shutdown();
}
```
#### 后台守护线程 Deamon 
Deamon: a thread that does not prevent the JVM from exiting when the program finishes but the thread is still running
```java
Thread thread = new Thread(new MyRunnable());
thread.setDaemon(true);
```
#### sleep()
Thread.sleep(millisec)
might throw Interrupted Exception
```java
public void run() {
    try {
        Thread.sleep(3000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}
```
#### yield() 
give priority to other threads
```java
public void run() {
    Thread.yield();
}
```

#### interrupted() & InterruptedException 

thread.start() interrupt() -> run() -> sleep(2000) -> InterruptedException
Will print "Main run"， then enter an interrupted exception, not "thread run".

```java
public class InterruptExample {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new MyThread1();
        thread1.start();
        thread1.interrupt();
        System.out.println("Main run");
    }

    private static class MyThread1 extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                System.out.println("Thread run");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
```
#### shutdown()
executorService.shutdown(); -> close threads after they finish
executorService.shutdownNow(); -> call interrupt() for every thread (before finish)

### 3.4 同步 Synchronized & 互斥 Mutual Exclusion

Java has two sources: 
- JVM: synchronized function
- JDK(J.U.C = java.util.concurrent): ReentrantLock

##### synchronized

Synchronize Part
```
public void func () {
    synchronized (this) {
        // ...
    }
}
```
Synchronize Function (static & non-static)
```
public synchronized （static） void func () {
    // ...
}
```
Synchronize Class
```
public void func() {
    synchronized (SynchronizedExample.class) {
        // ...
    }
}
```

Example: 
```
public class SynchronizedExample {

    public void func1() {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                System.out.print(i + " ");
            }
        }
    }

    public static void main(String[] args) {
        SynchronizedExample e1 = new SynchronizedExample();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> e1.func1());
        executorService.execute(() -> e1.func1());
    }
}
```
This returns: 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9
Because both ask for e1.func1 (the same object and the same function)

```
    executorService.execute(() -> e1.func1());
    executorService.execute(() -> e2.func1());
```
This returns: 0 0 1 1 2 2 3 3 4 4 5 5 6 6 7 7 8 8 9 9

#### ReentrantLock
Extra functions (compared to synchronized):
- Waiting threads can stop waiting
- Can use boolean values to construct fair lock
* fair lock : when multiple threads are waiting for one lock, they get the lock in order of applying
* unfair lock: any threads have the chance to get the lock when it is released
- One ReentrantLock can bind with multiple conditions

-> Use JVM synchronized with priority (if extra traits from ReentrantLock is not necessary).

#### 3.5 协作 Operation Among Threads

- join(): wait until the other thread is done
- wait(): wait until some condition is satisfied and the other threads will call 
- will also release the lock
- notify() or notifyAll(): to awake the waiting threads to continue
- await() signal() signalAll(): from J.U.C. await() can spefify the condition

#### 3.6 线程本地储存 Thread Local Storage

- Use thread local storage in order to test if the sharing code can be executed in the same thread (?)
- java.lang.ThreadLocal
- set(): Every thread has a TreadLocal.ThreadLocalMap. When calling ThreadLocal.set(T value, first get the ThreadLocalMap object, then insert ThreadLocal->value into the Map.
- get(): similarly, get the map value

```
public class ThreadLocalExample1 {
    public static void main(String[] args) {
        ThreadLocal threadLocal1 = new ThreadLocal();
        ThreadLocal threadLocal2 = new ThreadLocal();
        Thread thread1 = new Thread(() -> {
            threadLocal1.set(1);
            threadLocal2.set(1);
        });
        Thread thread2 = new Thread(() -> {
            threadLocal1.set(2);
            threadLocal2.set(2);
        });
        thread1.start();
        thread2.start();
    }
}
```

## 4. 内存模型 JMM: Java Memory Model

In order to run Java on different platforms, JMM deals with the hardware and OS memory differences.

### 4.1 工作内存 Working Memory & 主内存 Main Memory

- CPU <-> Cache -> Protocol -> Memory
Cache: faster than CPU
Protocol: deals with differences in cache data 
- Thread <-> Working Memory - Main Memory
Each thread has its own working memory. Variables change among different threads needs to pass through the main memory. 

### 4.2 Operations: 
- read: main memory -> working memory
- store: working memory -> main memory
- load: working memory <-> working memory
after read, store the var in working memory 
- use: working memory -> thread
- assign: thread -> working memory
- write: main memory -> main memory
after store, store the var in main memory
- lock : for vars in the main memory
- unlock : for vars in the main memory

### 4.3 JMM 三大特性：原子性 Atomicity、可见性 Visibility、有序性 Ordering

Source: [并发二：原子性、可见性、有序性](https://www.jianshu.com/p/9c5a7d21c02f)

- 原子性：一个操作不会被线程调度机制打断。 no context switch
Use: volatile, lock.

- 可见性：一个线程对变量的值进行了修改，其他线程能够立即得知这个修改。
Use: final, volatile, lock.

- 有序性： 由于CPU的计算速度远远高于内存的读写速度，会使用高速缓存(Cache)，为了减少CACHE_WAIT，CPU会采用指令级并行重排序来提供执行效率/乱序执行。保障排序之后的结果正确性。
Use: volatile, happens-before, synchronized.

Happens-before Principles:
1. Single Thread rule: within one thread, the statements before get executed before the statements after
2. Monitor Lock Rule: unlock happens before the lock operation for the same lock
3. Volatile Variable Rule: write volatile variable happens before read volatile
4. Thread Start Rule: thread start() happens before any statements in the thread
5. Thread Join Rule: join()-(waiting till the other thread is done) happens before the later on statements in the original thread
6. Thread Interruption Rule: interrupt() happens before the code find the interruption. Use Thread.interrupted() to see if interruption happens.
7. Finalizer Rule: initialization before finalize()
8. Transitivity: if A happens before B, B happens before C, then A happens before C

### 4.4 volatile keyword
Sources:
[volatile-keyword-in-java](https://www.geeksforgeeks.org/volatile-keyword-in-java/)
[Java并发编程：volatile关键字解析](https://www.cnblogs.com/dolphin0520/p/3920373.html)

```
class SharedObj
{
   // volatile keyword here makes sure that
   // the changes made in one thread are 
   // immediately reflect in other thread
   static volatile int sharedVar = 6;
}
```

## 5. 虚拟机 JVM: Java Virtual Machine 

JVM (Java Virtual Machine) is an abstract machine. It is a specification that provides runtime environment in which java bytecode can be executed. It is platform dependent. 

### 5.1 Structure
Inside threads:
- 程序计数器 PC Register : record current address
- 虚拟机栈 JVM Stack : store local variable array, operand stack, reference to constant poll
- 本地方法栈 Native Method Stack

Outside threads:
- 堆 Heap : allocate memory for objects
- 方法区 Method Area : store class, variables, static variables, compiled code
- + 运行时常量池 Runtime Constant Poll (inside method area)

-> Direct Memory

* difference between stack & heap: stack has automatically allocated memories, while heap has mannually defined memories (in c/c++).

### 5.2 垃圾回收机制 Garbage Collection

Target Heap & Method area(stores permenent objects -> low collection rate), because the contents inside threads will vanish after the thread finished.

#### 5.2.1 判断对象是否可回收

- 引用计数：对象增加计数+1，计数=0回收
- 从GC Roots开始搜索，可达的=可用，不可达=回收
- 引用类型：Strong, Weak, Soft, Phantom

1.  Strong Reference=不会回收
```
Object obj = new Object();
```
2.  Weak Reference=下次回收前一定会被回收
```
Object obj = new Object();
WeakReference<Object> wf = new WeakReference<Object>(obj);
```
3.  Soft Reference: 有用，不是必须，内存不够时回收
```
Object obj = new Object();
SoftReference<Object> sf = new SoftReference<Object>(obj);
```
4.  Phantom Reference: 无法取得一个对象实例,会在这个对象被收集器回收时收到一个系统通知
```
Object obj = new Object();
PhantomReference<Object> pf = new PhantomReference<Object>(obj);
```

- finalize(): 当一个对象可被回收时，可以通过用finalize()重新引用自己

#### 5.2.2 Collection Algorithm
1. 标记-清除 Mark-Sweep： 效率不高，产生碎片，无法大量分配内存
2. 标记-整理 Mark-Organize：把存活的对象整理到一边
3. 复制 Copy：分块，只用一半，快用完时复制存活对象到另一边，清理已用的一边
商业虚拟机：Eden(大)+ 2 Survivor（小）
4. 分代收集：新生代-复制，老年代-标记清除/标记整理

#### 5.2.3. Collector 收集器
HotSpot可配合使用
1. Serial: 单线程，回收时暂停其他threads, 简单高效，有停顿
2. ParNew: Serial多线程版本  -XX:ParallelGCThreads 设置线程数
3. Parallel Scavenge: 吞吐量( CPU 用于运行用户代码的时间占总时间的比值)优先,尽可能缩短停顿时间
4. CMS (Concurrent Mark Sweep) 基于标记-清除
5. Serial Old: Serial old version
6. Parallel Old: Parallel Scavenge old version
7. G1(Garbage-First) 前沿

### 5.3 类加载机制 ClassLoader

#### 5.3.1 class stages
- 加载 Loading
- 验证 Verification
- 准备 Preparation
- 解析 Resolution
- 初始化 Initialization
- 使用 Using
- 卸载 Unloading

#### 5.3.2 Loader
- 启动类加载器 Bootstrap ClassLoader: Store <JAVA_HOME>\lib or -Xbootclasspath files which are recognizable by VM. According to file name such as rt.jar.
- 扩展类加载器 Extension ClassLoader: ExtClassLoader（sun.misc.Launcher$ExtClassLoader,  <JAVA_HOME>/lib/ext or java.ext.dir 
- 系统类加载器 Application ClassLoader: AppClassLoader（sun.misc.Launcher$AppClassLoader, ClassLoader - getSystemClassLoader() 

#### 5.3.3 双亲委派模型 Parents Delegation Model

Composition instead of inheritance

Advantages: 优先级的层次关系, 避免系统中出现多个不同的Object. 

```
protected synchronized Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException{
    // 先检查请求的类是否已经被加载过了
    Class c = findLoadedClass(name);
    if(c == null) {
        try{
            if(parent != null) {
                c = parent.loadClass(name, false);
            } else{
                c = findBootstrapClassOrNull(name);
            }
        } catch(ClassNotFoundException e) {
            // 如果父类加载器抛出 ClassNotFoundException，说明父类加载器无法完成加载请求
        }
        if(c == null) {
            // 如果父类加载器无法完成加载请求，再调用自身的 findClass() 来进行加载
            c = findClass(name);
        }
    }
    if(resolve) {
        resolveClass(c);
    }
    return c;
}
```



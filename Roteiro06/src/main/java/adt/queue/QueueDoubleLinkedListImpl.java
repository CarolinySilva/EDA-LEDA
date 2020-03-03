1   package adt.queue;
2   
3   import adt.linkedList.DoubleLinkedList;
4   import adt.linkedList.DoubleLinkedListImpl;
5   
6   public class QueueDoubleLinkedListImpl<T> implements Queue<T> {
7      
8         protected DoubleLinkedList<T> list;
9         protected int size;
10    
11       public QueueDoubleLinkedListImpl(int size) {
12          this.size = size;
13          this.list = new DoubleLinkedListImpl<T>();
14       }
15    
16       @Override
17       public void enqueue(T element) throws QueueOverflowException {
18          if (isFull()) {
19             throw new QueueOverflowException();
20          } else {
21             this.list.insert(element);
22          }
23       }
24    
25       @Override
26       public T dequeue() throws QueueUnderflowException {
27          if (isEmpty()) {
28             throw new QueueUnderflowException();
29          } else {
30             T aux = this.head();
31             this.list.removeFirst();
32             return aux;
33          }
34       }
35    
36       @Override
37       public T head() {
38          T head = null;
39          if (!isEmpty()) {
40             head = ((DoubleLinkedListImpl<T>) this.list).getHead().getData();
41          }
42          return head;
43       }
44    
45       @Override
46       public boolean isEmpty() {
47          return this.list.isEmpty();
48       }
49    
50       @Override
51       public boolean isFull() {
52          return this.list.size() == this.size;
53       }
54    
55    }

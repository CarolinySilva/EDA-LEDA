
1   package adt.stack;
2   
3   import adt.linkedList.DoubleLinkedList;
4   import adt.linkedList.DoubleLinkedListImpl;
5   
6   public class StackDoubleLinkedListImpl<T> implements Stack<T> {
7      
8         protected DoubleLinkedList<T> top;
9         protected int size;
10    
11       public StackDoubleLinkedListImpl(int size) {
12          this.size = size;
13          this.top = new DoubleLinkedListImpl<T>();
14    
15       }
16    
17       @Override
18       public void push(T element) throws StackOverflowException {
19          if (isFull()) {
20             throw new StackOverflowException();
21          } else {
22             this.top.insert(element);
23          }
24    
25       }
26    
27       @Override
28       public T pop() throws StackUnderflowException {
29          if (isEmpty()) {
30             throw new StackUnderflowException();
31          } else {
32             T aux = this.top();
33             this.top.removeLast();
34             return aux;
35          }
36       }
37    
38       @Override
39       public T top() {
40          return ((DoubleLinkedListImpl<T>) this.top).getLast().getData();
41       }
42    
43       @Override
44       public boolean isEmpty() {
45          return this.top.isEmpty();
46       }
47    
48       @Override
49       public boolean isFull() {
50          return this.top.size() == this.size;
51       }
52    
53    }

1   package adt.linkedList;
2   
3   public class RecursiveDoubleLinkedListImpl<T> extends RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {
4   
5      protected RecursiveDoubleLinkedListImpl<T> previous;
6   
7      public RecursiveDoubleLinkedListImpl() {
8   
9      }
10  
11     @Override
12     public void insert(T element) {
13        if (super.inputIsValid(element)) {
14           if (this.isEmpty()) {
15              RecursiveDoubleLinkedListImpl<T> next = new RecursiveDoubleLinkedListImpl<>();
16              next.setPrevious(this);
17  
18              this.setData(element);
19              this.setNext(next);
20  
21              if (this.getPrevious() == null) {
22                 RecursiveDoubleLinkedListImpl<T> previous = new RecursiveDoubleLinkedListImpl<>();
23                 previous.setNext(this);
24  
25                 this.setPrevious(previous);
26              }
27           } else {
28              this.getNext().insert(element);
29           }
30        }
31     }
32  
33     @Override
34     public void insertFirst(T element) {
35        if (super.inputIsValid(element)) {
36           if (this.isEmpty()) {
37              RecursiveDoubleLinkedListImpl<T> next = new RecursiveDoubleLinkedListImpl<>();
38              RecursiveDoubleLinkedListImpl<T> previous = new RecursiveDoubleLinkedListImpl<>();
39  
40              next.setPrevious(this);
41              previous.setNext(this);
42  
43              this.setData(element);
44              this.setNext(next);
45              this.setPrevious(previous);
46           } else if (this.getPrevious().isEmpty()) {
47              RecursiveDoubleLinkedListImpl<T> next = new RecursiveDoubleLinkedListImpl<>();
48  
49              next.setData(this.getData());
50              next.setNext(this.getNext());
51              next.setPrevious(this);
52  
53              this.setData(element);
54              this.setNext(next);
55              ((RecursiveDoubleLinkedListImpl) next.getNext()).setPrevious(next);
56           } else {
57              this.getPrevious().insertFirst(element);
58           }
59        }
60     }
61  
62     @Override
63     public void remove(T element) {
64        if (super.inputIsValid(element)) {
65           if (!this.isEmpty()) {
66              if (this.getData().equals(element)) {
67                 if (this.getPrevious().isEmpty() && this.getNext().isEmpty()) {
68                    this.setData(null);
69                 } else {
70                    this.setData(this.getNext().getData());
71                    this.setNext(this.getNext().getNext());
72  
73                    if (this.getNext() != null) {
74                       ((RecursiveDoubleLinkedListImpl) this.getNext()).setPrevious(this);
75                    }
76                 }
77              } else {
78                 this.getNext().remove(element);
79              }
80           }
81        }
82     }
83  
84     @Override
85     public void removeFirst() {
86        if (!this.isEmpty()) {
87           if (this.getPrevious().isEmpty() && this.getNext().isEmpty()) {
88              this.setData(null);
89           } else if (this.getPrevious().isEmpty() && !this.getNext().isEmpty()) {
90              this.setData(this.getNext().getData());
91              this.setNext(this.getNext().getNext());
92              ((RecursiveDoubleLinkedListImpl) this.getNext()).setPrevious(this.getPrevious());
93           } else {
94              this.getPrevious().removeFirst();
95           }
96        }
97     }
98  
99     @Override
100    public void removeLast() {
101       if (!this.isEmpty()) {
102          if (this.getPrevious().isEmpty() && this.getNext().isEmpty()) {
103             this.setData(null);
104          } else if (!this.getPrevious().isEmpty() && this.getNext().isEmpty()) {
105             this.setData(this.getPrevious().getData());
106             this.setPrevious(this.getPrevious().getPrevious());
107             this.getPrevious().setNext(this);
108          } else {
109             ((RecursiveDoubleLinkedListImpl) this.getNext()).removeLast();
110          }
111       }
112    }
113 
114    public RecursiveDoubleLinkedListImpl<T> getPrevious() {
115       return previous;
116    }
117 
118    public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
119       this.previous = previous;
120    }
121 }

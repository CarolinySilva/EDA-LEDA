1   package adt.linkedList.set;
2   
3   import adt.linkedList.SingleLinkedListImpl;
4   import adt.linkedList.SingleLinkedListNode;
5   
6   public class SetLinkedListImpl<T> extends SingleLinkedListImpl<T> implements SetLinkedList<T> {
7   
8      @Override
9      public void removeDuplicates() {
10        if (!this.isEmpty()) {
11           SingleLinkedListNode<T> aux = this.getHead();
12  
13           while (!aux.isNIL()) {
14              SingleLinkedListNode<T> next = aux.getNext();
15  
16              while (!next.isNIL()) {
17                 if (aux.getData().equals(next.getData())) {
18                    next.setData(next.getNext().getData());
19                    next.setNext(next.getNext().getNext());
20                 } else {
21                    next = next.getNext();
22                 }
23              }
24  
25              aux = aux.getNext();
26           }
27        }
28     }
29  
30     @Override
31     public SetLinkedList<T> union(SetLinkedList<T> otherSet) {
32        SetLinkedList<T> result = new SetLinkedListImpl<>();
33        T[] arraySet = this.toArray();
34  
35        if (arraySet.length > 0) {
36           for (int i = 0; i < arraySet.length; i++) {
37              result.insert(arraySet[i]);
38           }
39        }
40  
41        if (otherSet != null) {
42           T[] arrayOtherSet = otherSet.toArray();
43  
44           if (arrayOtherSet.length > 0) {
45              for (int i = 0; i < arrayOtherSet.length; i++) {
46                 result.insert(arrayOtherSet[i]);
47              }
48           }
49        }
50  
51        result.removeDuplicates();
52        return result;
53     }
54  
55     @Override
56     public SetLinkedList<T> intersection(SetLinkedList<T> otherSet) {
57        SetLinkedList<T> result = new SetLinkedListImpl<>();
58        T[] arraySet = this.toArray();
59        T[] arrayOtherSet = otherSet.toArray();
60  
61        for (int i = 0; i < arraySet.length; i++) {
62           for (int j = 0; j < arrayOtherSet.length; j++) {
63              if (arraySet[i].equals(arrayOtherSet[j])) {
64                 result.insert(arraySet[i]);
65              }
66           }
67        }
68  
69        result.removeDuplicates();
70        return result;
71     }
72  
73     @Override
74     public void concatenate(SetLinkedList<T> otherSet) {
75        if (otherSet != null) {
76           T[] arrayOtherSet = otherSet.toArray();
77  
78           if (arrayOtherSet.length > 0) {
79              for (int i = 0; i < arrayOtherSet.length; i++) {
80                 this.insert(arrayOtherSet[i]);
81              }
82           }
83        }
84  
85        this.removeDuplicates();
86     }
87  }


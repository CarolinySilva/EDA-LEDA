1   package adt.hashtable.closed;
2   
3   import adt.hashtable.hashfunction.HashFunction;
4   import adt.hashtable.hashfunction.HashFunctionClosedAddress;
5   import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
6   import adt.hashtable.hashfunction.HashFunctionFactory;
7   
8   import java.util.LinkedList;
9   
10  public class HashtableClosedAddressImpl<T> extends AbstractHashtableClosedAddress<T> {
11  
12     /**
13      * A hash table with closed address works with a hash function with closed
14      * address. Such a function can follow one of these methods: DIVISION or
15      * MULTIPLICATION. In the DIVISION method, it is useful to change the size
16      * of the table to an integer that is prime. This can be achieved by
17      * producing such a prime number that is bigger and close to the desired
18      * size.
19      * 
20      * For doing that, you have auxiliary methods: Util.isPrime and
21      * getPrimeAbove as documented bellow.
22      * 
23      * The length of the internal table must be the immediate prime number
24      * greater than the given size (or the given size, if it is already prime). 
25      * For example, if size=10 then the length must
26      * be 11. If size=20, the length must be 23. You must implement this idea in
27      * the auxiliary method getPrimeAbove(int size) and use it.
28      * 
29      * @param desiredSize
30      * @param method
31      */
32  
33     @SuppressWarnings({ "rawtypes", "unchecked" })
34     public HashtableClosedAddressImpl(int desiredSize, HashFunctionClosedAddressMethod method) {
35        int realSize = desiredSize;
36  
37        if (method == HashFunctionClosedAddressMethod.DIVISION) {
38           realSize = this.getPrimeAbove(desiredSize); // real size must the
39           // the immediate prime
40           // above
41        }
42        initiateInternalTable(realSize);
43        HashFunction function = HashFunctionFactory.createHashFunction(method, realSize);
44        this.hashFunction = function;
45     }
46  
47     // AUXILIARY
48     /**
49      * It returns the prime number that is closest (and greater) to the given
50      * number.
51      * If the given number is prime, it is returned. 
52      * You can use the method Util.isPrime to check if a number is
53      * prime.
54      */
55     int getPrimeAbove(int number) {
56        int result = number + 1;
57  
58        if (!util.Util.isPrime(result)) {
59           result = getPrimeAbove(result);
60        }
61  
62        return result;
63     }
64  
65     @Override
66     public void insert(T element) {
67       if (element != null && this.indexOf(element) == -1) {
68          int hash = this.getHashFunction().hash(element);
69          LinkedList<T> ll = this.getLinkedList(hash);
70  
71          if (ll == null) {
72             this.setLinkedList(hash);
73             ll = this.getLinkedList(hash);
74          } else {
75             COLLISIONS++;
76          }
77  
78  
79          ll.add(element);
80  
81          elements++;
82       }
83     }
84  
85     @Override
86     public void remove(T element) {
87        int index = this.indexOf(element);
88  
89        if (index != -1) {
90           LinkedList<T> ll = this.getLinkedList(index);
91           ll.remove(element);
92           this.elements--;
93        }
94     }
95  
96     @Override
97     public T search(T element) {
98        int index = this.indexOf(element);
99        T result = null;
100 
101       if (index != -1) {
102          result = element;
103       }
104 
105       return result;
106    }
107 
108    @Override
109    public int indexOf(T element) {
110       int hash = this.getHashFunction().hash(element);
111 
112       if (this.table[hash] == null) {
113          hash = -1;
114       } else {
115          LinkedList<T> ll = this.getLinkedList(hash);
116          int internal = ll.indexOf(element);
117 
118          if (internal == -1) {
119             hash = -1;
120          }
121       }
122 
123       return hash;
124    }
125 
126    @Override
127    public HashFunctionClosedAddress<T> getHashFunction() {
128       return (HashFunctionClosedAddress<T>) this.hashFunction;
129    }
130 
131    private LinkedList<T> getLinkedList(int hash) {
132       return (LinkedList<T>) this.table[hash];
133    }
134 
135    private void setLinkedList(int hash) {
136       this.table[hash] = new LinkedList<T>();
137    }
138 }

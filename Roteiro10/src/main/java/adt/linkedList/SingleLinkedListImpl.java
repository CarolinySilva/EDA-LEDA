1   package adt.linkedList;
2   
3   public class SingleLinkedListImpl<T> implements LinkedList<T> {
4   
5   	protected SingleLinkedListNode<T> head;
6   
7   	public SingleLinkedListImpl() {
8   		this.head = new SingleLinkedListNode<T>();
9   	}
10  
11  	private boolean inputIsValid(T element) {
12  		boolean validInput = true;
13  
14  		if (element == null) {
15  			validInput = false;
16  		}
17  
18  		return validInput;
19  	}
20  
21  	@Override
22  	public boolean isEmpty() {
23  		return this.getHead().isNIL();
24  	}
25  
26  	@Override
27  	public int size() {
28  		int size = 0;
29  		SingleLinkedListNode<T> aux = this.getHead();
30  
31  		while (!aux.isNIL()) {
32  			size++;
33  			aux = aux.getNext();
34  		}
35  
36  		return size;
37  	}
38  
39  	@Override
40  	public T search(T element) {
41  		T result = null;
42  
43  		if (this.inputIsValid(element)) {
44  			SingleLinkedListNode<T> aux = this.getHead();
45  
46  			while (!aux.isNIL()) {
47  				if (aux.getData().equals(element)) {
48  					result = aux.getData();
49  					break;
50  				}
51  
52  				aux = aux.getNext();
53  			}
54  		}
55  
56  		return result;
57  	}
58  
59  	@Override
60  	public void insert(T element) {
61  		if (this.inputIsValid(element)) {
62  			SingleLinkedListNode<T> auxHead = this.getHead();
63  
64  			if (this.getHead().isNIL()) {
65  				SingleLinkedListNode<T> newHead = new SingleLinkedListNode<T>(element, this.getHead());
66  				this.setHead(newHead);
67  			} else {
68  				while (!auxHead.getNext().isNIL()) {
69  					auxHead = auxHead.getNext();
70  				}
71  
72  				SingleLinkedListNode<T> newNode = new SingleLinkedListNode<T>(element, auxHead.getNext());
73  				auxHead.setNext(newNode);
74  			}
75  		}
76  	}
77  
78  	@Override
79  	public void remove(T element) {
80  		if (this.inputIsValid(element)) {
81  			if (this.getHead().getData().equals(element)) {
82  				this.head = this.getHead().getNext();
83  			} else {
84  				SingleLinkedListNode<T> aux = this.getHead();
85  				SingleLinkedListNode<T> previous = this.getHead();
86  
87  				while (!aux.isNIL() && !aux.getData().equals(element)) {
88  					previous = aux;
89  					aux = aux.getNext();
90  				}
91  
92  				if (!aux.isNIL()) {
93  					previous.setNext(aux.getNext());
94  				}
95  			}
96  		}
97  	}
98  
99  	@Override
100 	public T[] toArray() {
101 		T[] result = (T[]) new Object[this.size()];
102 		SingleLinkedListNode<T> aux = this.getHead();
103 		int count = 0;
104 
105 		while (!aux.isNIL()) {
106 			result[count] = aux.getData();
107 			aux = aux.getNext();
108 			count++;
109 		}
110 
111 		return result;
112 	}
113 
114 	public SingleLinkedListNode<T> getHead() {
115 		return head;
116 	}
117 
118 	public void setHead(SingleLinkedListNode<T> head) {
119 		this.head = head;
120 	}
121 
122 }


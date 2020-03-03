1   package adt.linkedList;
2   
3   public class SingleLinkedListImpl<T> implements LinkedList<T> {
4      
5      	protected SingleLinkedListNode<T> head;
6      
7      	public SingleLinkedListImpl() {
8      		this.head = new SingleLinkedListNode<T>();
9      	}
10    
11    	@Override
12    	public boolean isEmpty() {
13    		return this.getHead().isNIL();
14    	}
15    
16    	@Override
17    	public int size() {
18    		int result = 0;
19    		if(isEmpty()){
20    			return result;
21    		}else{
22    			SingleLinkedListNode<T> aux = this.getHead();
23    			while(!aux.isNIL()){
24    				result += 1;
25    				aux= aux.next;
26    				
27    			}
28    		}
29    		return result;
30    	}
31    
32    	@Override
33    	public T search(T element) {
34    		T result = null;
35    		if (element != null && !isEmpty()){
36    				if(this.head.getData().equals(element)){
37    				result = this.head.getData();
38    			}else{
39    				SingleLinkedListNode<T> aux = this.getHead();
40    				while(!(aux.isNIL())){
41    					if(aux.getData().equals(element)){
42    						result = aux.getData();
43    					}
44    					aux = aux.next;
45    				}
46    				
47    			}
48    		}
49    		return result;
50    	}
51    
52    	@Override
53    	public void insert(T element) {
54    		if(element != null){
55    			SingleLinkedListNode<T> next = new SingleLinkedListNode<>();
56    			if(isEmpty()){
57    				SingleLinkedListNode<T> newHead = new SingleLinkedListNode<T>(element, next);
58    				setHead(newHead);
59    			}else{
60    			SingleLinkedListNode<T> aux = this.getHead();
61    			while(!(aux.isNIL())){
62    				aux = aux.getNext();
63    			}
64    			aux.setData(element);
65    			aux.setNext(next);
66    		}
67    		}
68    	}
69    
70    	@Override
71    	public void remove(T element) {
72    		if (element != null && !(isEmpty())){
73    			
74    			if(this.head.getData().equals(element)){
75    				this.head = head.getNext();
76    			}else{
77    				SingleLinkedListNode<T> aux = this.getHead();
78    				SingleLinkedListNode<T> previous = new SingleLinkedListNode<T>();
79    				while(!(aux.isNIL()) && !(aux.getData().equals(element))){
80    					previous = aux;
81    					aux = aux.getNext();
82    				}
83    				
84    				if(!(aux.isNIL())){
85    					previous.setNext(aux.getNext());
86    				}
87    			}
88    		
89    		}
90    	}
91    
92    	@Override
93    	public T[] toArray() {
94    		T[] array = (T[]) new Object[this.size()];
95    		int index = 0;
96    		
97    		SingleLinkedListNode<T> aux = this.getHead();
98    		while(!(aux.isNIL())){
99    			array[index] = aux.getData();
100  			aux= aux.getNext();
101  			index++;
102  		
103  		}
104  		return array;
105  	}
106  
107  	public SingleLinkedListNode<T> getHead() {
108  		return head;
109  	}
110  
111  	public void setHead(SingleLinkedListNode<T> head) {
112  		this.head = head;
113  	}
114  
115  }
